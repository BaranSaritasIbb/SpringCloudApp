package com.SpringCloudApp.dto;



import org.springframework.core.convert.converter.Converter;
import org.springframework.http.*;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class IbbJwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static final Collection<String> WELL_KNOWN_SCOPE_ATTRIBUTE_NAMES =
        Arrays.asList("scope", "scp");

    private final RestTemplate restTemplate = new RestTemplate();

//    private  AppProperties appProperties;



    public final AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
        UserInfoRole userInfoResponse =  getUserInfo(jwt.getTokenValue());
        if (userInfoResponse != null && userInfoResponse.getRoles() != null) {
            String[] roleArray = userInfoResponse.getRoles().split(",\\s*");

            // Sonuçları yazdırın
            for (String role : roleArray) {
                authorities.add(new SimpleGrantedAuthority(role));
                System.out.println(role);
            }
        }

//        userInfoResponse.getRoles().stream().forEach(roleTest ->  {
//
//        });
//
//        userInfoResponse.getRoles().stream().forEach(role -> {
//            // Her bir rolü işleyin, burada sadece yazdırıyoruz
//            System.out.println(role);
//        });

        return new JwtAuthenticationToken(jwt, authorities);
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        Collection<String> scopes = this.getScopes(jwt);
//        if (!scopes.isEmpty()) {
//            scopes = getScopesFromTokenDetails(jwt);
//        }
        return scopes.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    private Collection<String> getScopesFromTokenDetails(Jwt jwt) {
        Object token_details = jwt.getClaims().get("token_details");
        if (token_details instanceof Map) {
            Object scope = ((Map<String, Object>) token_details).get("scope");
            if (scope instanceof String) {
                return Arrays.asList(((String) scope).split(" "));
            }
        }
        return Collections.emptyList();
    }

    private Collection<String> getScopes(Jwt jwt) {
        for (String attributeName : WELL_KNOWN_SCOPE_ATTRIBUTE_NAMES) {
            Object scopes = jwt.getClaims().get(attributeName);
            if (scopes instanceof String) {
                if (StringUtils.hasText((String) scopes)) {
                    return Arrays.asList(((String) scopes).split(" "));
                } else {
                    return Collections.emptyList();
                }
            } else if (scopes instanceof Collection) {
                return (Collection<String>) scopes;
            }
        }
        return Collections.emptyList();
    }

    private UserInfoRole getUserInfo(String token)  {
//        LdapProperties ldapProperties = appProperties.getAuth().getLdap();



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<UserInfoRole> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    "https://api2.ibb.istanbul/openid/connect/v1/userinfo",
                    HttpMethod.GET,
                    httpEntity,
                    UserInfoRole.class
            );
        } catch (HttpClientErrorException.Unauthorized unauthorized) {
            return null;
        }

        return Objects.requireNonNull(responseEntity.getBody());
    }
}

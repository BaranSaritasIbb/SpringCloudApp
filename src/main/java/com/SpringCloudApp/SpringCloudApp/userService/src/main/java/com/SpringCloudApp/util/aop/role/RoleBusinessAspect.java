package com.SpringCloudApp.util.aop.role;

/*
import com.ibb.boot.data.exception.BusinessException;
import com.ibb.boot.data.exception.reasons.BusinessExceptionReason;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
*/

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RoleBusinessAspect {

    @Before("@annotation(requiresRole)")
    public void checkRole(RoleAspect requiresRole) {
        String[] requiredRoles = requiresRole.value();
/*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            for (String role : requiredRoles) {
                if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role))) {
                    return;
                }
            }
        }

        throw new BusinessException(BusinessExceptionReason.UNAUTHORIZED_ACCESS);
        */

    }
}

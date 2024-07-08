package com.SpringCloudApp.mapper;

import com.SpringCloudApp.dto.request.UserRequest;
import com.SpringCloudApp.model.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-08T13:54:27+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserRequest UserToUserRequest(Users user) {
        if ( user == null ) {
            return null;
        }

        UserRequest userRequest = new UserRequest();

        userRequest.setId( user.getId() );
        userRequest.setFirstname( user.getFirstname() );
        userRequest.setLastname( user.getLastname() );
        userRequest.setAge( user.getAge() );
        userRequest.setAge2( user.getAge2() );
        userRequest.setDeneme( user.getDeneme() );
        userRequest.setCreated( user.getCreated() );
        userRequest.setUpdated( user.getUpdated() );

        return userRequest;
    }

    @Override
    public Users UserRequestToUser(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        Users users = new Users();

        users.setId( userRequest.getId() );
        users.setFirstname( userRequest.getFirstname() );
        users.setLastname( userRequest.getLastname() );
        users.setAge( userRequest.getAge() );
        users.setDeneme( userRequest.getDeneme() );
        users.setAge2( userRequest.getAge2() );
        users.setCreated( userRequest.getCreated() );
        users.setUpdated( userRequest.getUpdated() );

        return users;
    }

    @Override
    public void updateUserToUserRequest(UserRequest userRequest, Users user) {
        if ( userRequest == null ) {
            return;
        }

        user.setFirstname( userRequest.getFirstname() );
        user.setLastname( userRequest.getLastname() );
        user.setAge( userRequest.getAge() );
        user.setDeneme( userRequest.getDeneme() );
        user.setAge2( userRequest.getAge2() );
        user.setCreated( userRequest.getCreated() );
        user.setUpdated( userRequest.getUpdated() );
    }

    @Override
    public List<UserRequest> userListToUserRequestList(List<Users> users) {
        if ( users == null ) {
            return null;
        }

        List<UserRequest> list = new ArrayList<UserRequest>( users.size() );
        for ( Users users1 : users ) {
            list.add( UserToUserRequest( users1 ) );
        }

        return list;
    }
}

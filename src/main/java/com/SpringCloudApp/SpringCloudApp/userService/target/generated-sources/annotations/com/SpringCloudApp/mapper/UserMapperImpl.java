package com.SpringCloudApp.mapper;

import com.SpringCloudApp.dto.request.UserRequest;
import com.SpringCloudApp.model.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-04T10:34:03+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public void update(Object source, Object target) {
        if ( source == null ) {
            return;
        }
    }

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

        if ( userRequest.getFirstname() != null ) {
            if ( user.getFirstname() == null ) {
                user.setFirstname( "" );
            }
            update( userRequest.getFirstname(), user.getFirstname() );
        }
        else {
            user.setFirstname( null );
        }
        if ( userRequest.getLastname() != null ) {
            if ( user.getLastname() == null ) {
                user.setLastname( "" );
            }
            update( userRequest.getLastname(), user.getLastname() );
        }
        else {
            user.setLastname( null );
        }
        if ( userRequest.getAge() != null ) {
            if ( user.getAge() == null ) {
                user.setAge( 0 );
            }
            update( userRequest.getAge(), user.getAge() );
        }
        else {
            user.setAge( null );
        }
        if ( userRequest.getAge2() != null ) {
            if ( user.getAge2() == null ) {
                user.setAge2( new Date() );
            }
            update( userRequest.getAge2(), user.getAge2() );
        }
        else {
            user.setAge2( null );
        }
        if ( userRequest.getCreated() != null ) {
            if ( user.getCreated() == null ) {
                user.setCreated( new Date() );
            }
            update( userRequest.getCreated(), user.getCreated() );
        }
        else {
            user.setCreated( null );
        }
        if ( userRequest.getUpdated() != null ) {
            if ( user.getUpdated() == null ) {
                user.setUpdated( new Date() );
            }
            update( userRequest.getUpdated(), user.getUpdated() );
        }
        else {
            user.setUpdated( null );
        }
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

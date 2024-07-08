package com.SpringCloudApp.mapper;


import com.SpringCloudApp.dto.request.UserRequest;
import com.SpringCloudApp.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserRequest UserToUserRequest(Users user);

    Users UserRequestToUser(UserRequest userRequest);

    @Mapping(target = "id",ignore = true)
    void updateUserToUserRequest(UserRequest userRequest,@MappingTarget Users user);

    List<UserRequest> userListToUserRequestList (List<Users> users);

}

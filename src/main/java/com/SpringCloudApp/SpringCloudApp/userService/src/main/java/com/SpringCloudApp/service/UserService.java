package com.SpringCloudApp.service;

import com.SpringCloudApp.dto.request.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    UserRequest getById(Long id);

    UserRequest updateUser(UserRequest userRequest,Long id);

    UserRequest createUser(UserRequest userRequest);

    List<UserRequest> getAllUser();
}

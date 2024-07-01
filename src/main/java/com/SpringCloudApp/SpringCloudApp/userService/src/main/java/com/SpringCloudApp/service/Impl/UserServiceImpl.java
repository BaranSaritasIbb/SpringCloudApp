package com.SpringCloudApp.service.Impl;


import com.SpringCloudApp.dto.request.UserRequest;
import com.SpringCloudApp.mapper.UserMapper;
import com.SpringCloudApp.model.Users;
import com.SpringCloudApp.repository.UserRepository;
import com.SpringCloudApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserRequest getById(Long id) {
        Users user = repository.findById(id).orElseThrow(RuntimeException::new);

        UserRequest request = mapper.UserToUserRequest(user);

        return request;
    }

    @Override
    public UserRequest updateUser(UserRequest userRequest,Long id) {
        Users user = repository.findById(id).orElseThrow(RuntimeException::new);
        mapper.updateUserToUserRequest(userRequest,user);
        userRequest.setId(user.getId());
        repository.save(user);
        return userRequest;
    }

    @Override
    public UserRequest createUser(UserRequest userRequest) {
        Users user = mapper.UserRequestToUser(userRequest);
        user = repository.save(user);
        userRequest.setId(user.getId());
        return userRequest;
    }
}

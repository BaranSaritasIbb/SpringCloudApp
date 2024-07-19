package com.SpringCloudApp.service.Impl;


import com.SpringCloudApp.dto.request.UserRequest;
import com.SpringCloudApp.mapper.UserMapper;
import com.SpringCloudApp.model.Users;
import com.SpringCloudApp.repository.UserRepository;
import com.SpringCloudApp.service.UserService;
import com.ibb.boot.data.service.Redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final RedisService redisService;
    private final ModelMapper modelMapper;

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
        Users user = modelMapper.map(userRequest,Users.class);
        user = repository.save(user);
        userRequest.setId(user.getId());
        return userRequest;
    }

    @Override
    public List<UserRequest> getAllUser() {
        //List<Users> users = (List<Users>) redisService.getValue("allUsers");
    //    if(users == null){
        List<Users> users = repository.findAll();
        List<UserRequest> requests = new ArrayList<>();
        for(Users user:users){
            
            UserRequest userRequest= modelMapper.map(user,UserRequest.class);

            userRequest.getExcelSchemaList().forEach(i->i.setUserId(user.getId()));
            requests.add(userRequest);
        }
     //       redisService.setValue("allUsers",users);
      //  }
        return requests;

    }
}

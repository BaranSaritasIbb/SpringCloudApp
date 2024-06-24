package com.SpringCloudApp.service.Impl;


import com.SpringCloudApp.repository.UserRepository;
import com.SpringCloudApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    private final UserRepository repository;


}

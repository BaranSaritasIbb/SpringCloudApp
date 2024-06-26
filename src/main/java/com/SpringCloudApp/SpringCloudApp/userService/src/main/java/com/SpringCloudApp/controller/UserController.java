package com.SpringCloudApp.controller;


import com.SpringCloudApp.dto.request.UserRequest;
import com.SpringCloudApp.service.UserService;
import com.SpringCloudApp.util.aop.role.RoleAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/v1")
public class UserController {

    private final UserService service;

    @GetMapping
  //  @RoleAspect("ibb_user")
    public String getTest(){
        return "sa";
    }
 //   @RoleAspect("ibb_user")

    @GetMapping("/{id}")
    public ResponseEntity<UserRequest> getUserById (@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }
  //  @RoleAspect("ibb_user")
    @PutMapping("/{id}")
    public ResponseEntity<UserRequest> updateUser(@RequestBody UserRequest userRequest , @PathVariable Long id){
        return ResponseEntity.ok(service.updateUser(userRequest,id));
    }

//    @RoleAspect("ibb_user")
    @PostMapping
    public ResponseEntity<UserRequest> createUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(service.createUser(userRequest));
    }

}

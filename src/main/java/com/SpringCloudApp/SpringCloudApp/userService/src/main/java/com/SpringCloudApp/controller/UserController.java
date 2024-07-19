package com.SpringCloudApp.controller;


import com.SpringCloudApp.dto.request.UserRequest;
import com.SpringCloudApp.service.UserService;
import com.ibb.boot.data.util.tools.TcKimlikNoValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/v1")
public class UserController {

    private final UserService service;

    private final TcKimlikNoValidator validator;

    @GetMapping("/{id}")
    public ResponseEntity<UserRequest> getUserById (@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<UserRequest>> getAllUser (){
        return ResponseEntity.ok(service.getAllUser());
    }
  //  @RoleAspect("ibb_user")
    @PutMapping("/{id}")
    public ResponseEntity<UserRequest> updateUser(@RequestBody UserRequest userRequest , @PathVariable Long id){
        return ResponseEntity.ok(service.updateUser(userRequest,id));
    }
   // @RoleAspect("ibb_user2")
    @PostMapping
    public ResponseEntity<UserRequest> createUser(@Valid @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(service.createUser(userRequest));
    }

    @GetMapping("/tc")
    public ResponseEntity<String> validateTcKimlikNo(@RequestParam String tcKimlikNo) {
        if (validator.isValid(tcKimlikNo)) {
            return new ResponseEntity<>("Geçerli TC Kimlik Numarası", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Geçersiz TC Kimlik Numarası", HttpStatus.BAD_REQUEST);
        }
    }


    // csv to excel kismina bak import sorunlari olabilir
    /*
    @PostMapping("/import")
    public ResponseEntity<List<String[]>> importExcel(@RequestParam("file") MultipartFile file) {
        try {
            List<String[]> data = excelService.importExcel(file);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     */

}

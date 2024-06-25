package com.SpringCloudApp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class UserInfoRole {
    private String sub;
    private String firstName;
    private String lastName;
    private String displayName;
    private String roles;
    private String uid;

}

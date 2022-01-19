package com.wms.admin.controller;

import com.wms.admin.entity.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user/")
public class UserController {

    @GetMapping("/queryUser")
    public List<UserInfo> queryUser(){

        List<UserInfo> userInfoList = new ArrayList<>();

        UserInfo user1 = new UserInfo();
        user1.setUsername("TEST1");
        userInfoList.add(user1);
        UserInfo user2 = new UserInfo();
        user2.setUsername("TEST2");
        userInfoList.add(user2);

        return userInfoList;

    }
}

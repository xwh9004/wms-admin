package com.wms.admin.controller;

import com.wms.admin.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user/")
public class UserController {

    @GetMapping("/queryUser")
    public List<User> queryUser(){

        List<User> userInfoList = new ArrayList<>();

        User user1 = new User();
        user1.setUsername("TEST1");
        userInfoList.add(user1);
        User user2 = new User();
        user2.setUsername("TEST2");
        userInfoList.add(user2);

        return userInfoList;

    }
}

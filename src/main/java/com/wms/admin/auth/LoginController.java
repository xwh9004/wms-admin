package com.wms.admin.auth;

import com.alibaba.fastjson.JSONObject;
import com.wms.admin.auth.Audience;
import com.wms.admin.auth.AuthenticService;
import com.wms.admin.auth.JwtTokenUtil;
import com.wms.admin.auth.UserInfo;
import com.wms.admin.commom.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private Audience audience;

    @Autowired
    private AuthenticService authenticService;


    @PostMapping(value = "/login")
    public Result login(HttpServletResponse response, @RequestBody UserInfo req) {
        UserInfo userInfo = authenticService.getUserInfo(req.getUsername(),req.getPassword());
        // 创建token
        String token = JwtTokenUtil.createJWT(userInfo.getUserId(), req.getUsername(),
                userInfo.getRoleCode(),userInfo.getResources(), audience);
        log.info("### 登录成功, token={} ###", token);
        // 将token放在响应头
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        Map<String,String> data = new HashMap<>();
        data.put("token",token);
        return Result.success().data(data);
    }
    @GetMapping(value = "/info")
    public Result getUserInfo(){
        Map<String,Object> userInfo =new HashMap<>();
        userInfo.put("roles",new String[]{"admin"});
        userInfo.put("resources",new String[]{"BTN0001","BTN0002"});
        userInfo.put("introduction","I am a super administrator");
        userInfo.put("name","Super Admin");
        userInfo.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.success().data(userInfo);
    }
    @PostMapping(value = "/logout")
    public Result logout() {
        //清空登录信息，共享sessionId
        UserInfoContext.removeUserInfo();
        return Result.success();
    }
}

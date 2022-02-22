package com.wms.admin.auth;

import com.wms.admin.commom.Result;
import com.wms.admin.service.IMenuService;
import com.wms.admin.vo.RouteVO;
import com.wms.admin.vo.UserRoleVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private Audience audience;

    @Autowired
    private AuthenticService authenticService;

    @Autowired
    private IMenuService menuService;


    @PostMapping(value = "/login")
    public Result login(HttpServletResponse response, @RequestBody UserInfo req) {
        UserInfo userInfo = authenticService.getUserInfo(req.getUsername(), req.getPassword());
        // 创建token
        String token = JwtTokenUtil.createJWT(userInfo.getUserId(), req.getUsername(),
                userInfo.getRoles(), userInfo.getResources(), audience);
        log.info("### 登录成功, token={} ###", token);
        // 将token放在响应头
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        return Result.success().data(data);
    }

    @GetMapping(value = "/info")
    public Result getUserInfo() {
        Map<String, Object> userInfo = new HashMap<>();
        List<UserRoleVO> roles = UserInfoContext.getUserInfo().getRoles();
        List<String> roleNames = new ArrayList<>();
        roles.stream().forEach(item->{
            roleNames.add(item.getRoleName());
        });
        userInfo.put("roles", roleNames);
        userInfo.put("resources", new String[]{"BTN0001", "BTN0002"});
        userInfo.put("introduction", "I am a super administrator");
        userInfo.put("name", UserInfoContext.getUsername());
        userInfo.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.success().data(userInfo);
    }


    @ApiOperation(value = "用户资源")
    @GetMapping("/routes")
    public Result routes() {
        final List<UserRoleVO> roles = UserInfoContext.getUserInfo().getRoles();
        List<String> roleIds = roles.stream().map(UserRoleVO::getRoleId).collect(Collectors.toList());
        List<RouteVO> menuVOList = menuService.queryRoleRoutes(roleIds);
        return Result.success().data(menuVOList);
    }

    @PostMapping(value = "/logout")
    public Result logout() {
        //清空登录信息，共享sessionId
        UserInfoContext.removeUserInfo();
        return Result.success();
    }
}

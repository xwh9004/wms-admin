package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.service.IUserService;
import com.wms.admin.vo.UserQueryVO;
import com.wms.admin.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
@Api("用户管理控制器")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/list")
    public Result list(UserQueryVO userQueryVO, PageParam pageParam) {
        IPage<UserVO> rolePage = userService.userPage(userQueryVO,pageParam);
        return Result.success().data(rolePage);
    }

    @ApiOperation(value = "新增用户")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/add")
    public Result add(UserVO userVO) {
        userService.addUser(userVO);
        return Result.success();
    }

    @ApiOperation(value = "修改用户")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/update")
    public Result update(UserVO userVO) {
        userService.updateUser(userVO);
        return Result.success();
    }


    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @GetMapping("/delete/{roleId}")
    public Result delete(@PathVariable String userId) {
        userService.deleteUser(userId);
        return Result.success();
    }

}

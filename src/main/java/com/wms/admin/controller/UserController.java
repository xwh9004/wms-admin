package com.wms.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.Result;
import com.wms.admin.dto.UserDto;
import com.wms.admin.service.IUserService;
import com.wms.admin.vo.UserQueryVO;
import com.wms.admin.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Jesse
 * @since 2022-01-21 10:08:12
 */
@Api("用户管理控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/list")
    public Result list(@RequestBody UserQueryVO userQueryVO, PageParam pageParam) {
        IPage<UserVO> rolePage = userService.userPage(userQueryVO,pageParam);
        return Result.success().data(rolePage);
    }

    @ApiOperation(value = "新增用户")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated UserVO userVO) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userVO,userDto);
        userService.addUser(userDto);
        return Result.success();
    }

    @ApiOperation(value = "修改用户")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/update")
    public Result update(@RequestBody UserVO userVO) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userVO,userDto);
        userService.updateUser(userDto);
        return Result.success();
    }


    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Token")
    @PostMapping("/delete/{userId}")
    public Result delete(@PathVariable String userId) {
        userService.deleteUser(userId);
        return Result.success();
    }

}

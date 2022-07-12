package com.pccg.pccguser.interfaces;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.pccg.pccguser.application.UserApplicationService;
import com.pccg.pccguser.application.command.EditPasswordCmd;
import com.pccg.pccguser.application.command.EditUserCmd;
import com.pccg.pccguser.application.command.RegisterUserCmd;
import com.pccg.pccguser.application.vo.UserVO;
import com.pccg.pccguser.interfaces.api.MultiResponse;
import com.pccg.pccguser.interfaces.api.Response;
import com.pccg.pccguser.interfaces.api.SingleResponse;
import com.pccg.pccguser.interfaces.api.UserQuery;
import com.pccg.pccguser.interfaces.assembler.UserAssembler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 */
@RestController
@Api(tags = "用户管理")
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserApplicationService userApplicationService;

    @ApiOperation("注册用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestBody @Valid RegisterUserCmd registerUserCmd) {
        userApplicationService.registerUser(UserAssembler.to(registerUserCmd));
        return Response.buildSuccess();
    }

    @ApiOperation("查询用户")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<UserVO> get(@NotNull String userName,@NotNull String password) {
        return SingleResponse.of(userApplicationService.selectUser(userName, password));
    }

    @ApiOperation("删除用户")
    @RequestMapping(value = "/disable", method = RequestMethod.DELETE)
    @ResponseBody
    public Response disable(@RequestBody List<Long> userIds) {
        userApplicationService.disableUser(userIds);
        return Response.buildSuccess();
    }

    @ApiOperation("修改用户密码")
    @RequestMapping(value = "/editPassword", method = RequestMethod.PUT)
    @ResponseBody
    public Response editPassword(EditPasswordCmd editPasswordCmd) {
        userApplicationService.updateUserPassword(UserAssembler.to(editPasswordCmd), editPasswordCmd.getOldPassword());
        return Response.buildSuccess();
    }

    @ApiOperation("更新用户信息")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public Response edit(EditUserCmd editUserCmd) {
        userApplicationService.updateUserBasicInfo(UserAssembler.to(editUserCmd));
        return Response.buildSuccess();
    }

    @ApiOperation("用户查询")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public MultiResponse<UserVO> list(@RequestBody @Valid UserQuery userQuery) {
        return userApplicationService.selectByPage(userQuery);
    }
}

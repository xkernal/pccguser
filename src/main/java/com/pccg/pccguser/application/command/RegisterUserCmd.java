package com.pccg.pccguser.application.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="注册用户",description="注册用户")
public class RegisterUserCmd {

    /**
     * 用户名
     */
    @NotNull
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    private String mobile;

    /**
     * 密码
     */
    @NotNull
    @ApiModelProperty(value="密码, required = true")
    private String password;

    /**
     * email
     */
    @NotNull
    @Email(message = "please input correct email")
    @ApiModelProperty(value="email, required = true")
    private String email;
}

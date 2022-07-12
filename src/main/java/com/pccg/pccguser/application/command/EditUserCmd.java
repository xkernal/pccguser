package com.pccg.pccguser.application.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="编辑用户",description="编辑用户")
public class EditUserCmd {
    /**
     * 用户ID
     */
    @NotNull
    @ApiModelProperty(value = "用户ID", required = true)
    private long userId;

    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    private String mobile;


    /**
     * email
     */
    @NotNull
    @Email(message = "please input correct email")
    @ApiModelProperty(value="email")
    private String email;
}

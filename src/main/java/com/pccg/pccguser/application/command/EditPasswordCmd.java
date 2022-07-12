package com.pccg.pccguser.application.command;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="更新密码",description="更新密码")
public class EditPasswordCmd {

    /**
     * 用户ID
     */
    @NotNull
    @ApiModelProperty(value = "用户ID", required = true)
    private long userId;

    /**
     * 原密码
     */
    @NotNull
    @ApiModelProperty(value = "原密码", required = true)
    private String oldPassword;

    /**
     * 新密码
     */
    @NotNull
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}

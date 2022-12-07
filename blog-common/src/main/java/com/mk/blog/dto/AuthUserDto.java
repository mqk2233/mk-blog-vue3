package com.mk.blog.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MK
 * @describe 用户表实体类
 * @date 2021-07-29 20:12:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("auth_user")
@ApiModel(description = "AuthUser对象")
public class AuthUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("是否禁用（1-是，0-否）")
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDisable;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    public static void main(String[] args) {
    }
}
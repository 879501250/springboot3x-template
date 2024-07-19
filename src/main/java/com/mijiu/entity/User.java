package com.mijiu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 蒾酒
 * @since 2024-03-07
 */
@Data
@TableName("user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户昵称")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("账号")
    @TableField("user_account")
    private String userAccount;

    @ApiModelProperty("用户角色：user / admin")
    @TableField("user_role")
    private String userRole;

    @ApiModelProperty("头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除：1删除/0存在")
    @TableField("is_delete")
    private Boolean isDelete;

    @ApiModelProperty("性别")
    @TableField("gender")
    private Boolean gender;

    @ApiModelProperty("状态：1正常0禁用")
    @TableField("status")
    private Boolean status;


    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;
}

package com.mijiu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author 蒾酒
 * @since 2024-03-07
 */
@Getter
@Setter
@TableName("administrator")
@ApiModel(value = "Administrator对象", description = "管理员表")
public class Administrator implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("管理员账号")
    @TableField("admin_account")
    private String adminAccount;

    @ApiModelProperty("管理员密码")
    @TableField("admin_password")
    private String adminPassword;

    @ApiModelProperty("管理员昵称")
    @TableField("admin_name")
    private String adminName;

    @ApiModelProperty("社区id")
    @TableField("merchant_id")
    private Integer merchantId;
}

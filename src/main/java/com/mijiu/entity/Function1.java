package com.mijiu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统一级功能表
 * </p>
 *
 * @author 蒾酒
 * @since 2024-02-21
 */
@Getter
@Setter
@TableName("function1")
@ApiModel(value = "Function1对象", description = "系统一级功能表")
public class Function1 implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("一级功能名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("标识")
    @TableField("fid")
    private Integer fid;

    @ApiModelProperty("路由")
    @TableField("path")
    private String path;

    @ApiModelProperty("父功能id")
    @TableField("father_id")
    private Integer fatherId;

    @ApiModelProperty("组件名")
    @TableField("component_name")
    private String componentName;

    @ApiModelProperty("父功能名称")
    @TableField("father_name")
    private String fatherName;

    @ApiModelProperty("icon图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("图标颜色")
    @TableField("color")
    private String color;

    @ApiModelProperty("相对排序")
    @TableField("sort")
    private Integer sort;


    @ApiModelProperty("子功能")
    @TableField("children")
    private List<Function1> children;
}

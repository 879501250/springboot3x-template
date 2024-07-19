package com.mijiu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商户功能关系表
 * </p>
 *
 * @author 蒾酒
 * @since 2024-03-07
 */
@Getter
@Setter
@TableName("function_system")
@ApiModel(value = "FunctionSystem对象", description = "商户功能关系表")
public class FunctionSystem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商户id")
    @TableField("merchant_id")
    private Integer merchantId;

    @ApiModelProperty("一级功能id列表")
    @TableField("f1id")
    private String f1id;

    @ApiModelProperty("二级功能id列表")
    @TableField("f2id")
    private String f2id;

    @ApiModelProperty("三级功能id列表")
    @TableField("f3id")
    private String f3id;
}

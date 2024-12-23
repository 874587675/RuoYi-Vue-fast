package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "t_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_order")
public class Order implements Serializable {
    /**
     * 订单主键
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value = "订单主键")
    private String id;

    /**
     * 订单编号
     */
    @TableField(value = "order_no")
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 订单金额
     */
    @TableField(value = "amount")
    @ApiModelProperty(value = "订单金额")
    private BigDecimal amount;

    /**
     * 订单标题
     */
    @TableField(value = "subject")
    @ApiModelProperty(value = "订单标题")
    private String subject;

    /**
     * 订单创建时间
     */
    @JsonIgnore
    @TableField(value = "create_time")
    @ApiModelProperty(value = "订单创建时间")
    private Date createTime;

    /**
     * 订单更新时间
     */
    @JsonIgnore
    @TableField(value = "update_time")
    @ApiModelProperty(value = "订单更新时间")
    private Date updatetTime;

    private static final long serialVersionUID = 1L;
}

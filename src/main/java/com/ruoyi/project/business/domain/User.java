package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user")
public class User implements Serializable {
    /**
     * 用户编号
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value = "用户编号")
    private String id;

    /**
     * 微信编号openId
     */
    @TableField(value = "open_id")
    @ApiModelProperty(value = "微信编号openId")
    private String openId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
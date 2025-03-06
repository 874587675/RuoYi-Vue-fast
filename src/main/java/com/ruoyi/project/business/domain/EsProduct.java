package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @program: RuoYi-Vue-fast
* @ClassName EsProduct
* @description: 
* @author: zgc
* @date: 2025-03-06 18:16
* @Version 1.0
**/
@ApiModel(description="t_es_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_es_product")
public class EsProduct implements Serializable {
    /**
     * 测试es主键表
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="测试es主键表")
    private Integer id;

    /**
     * 名字
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="名字")
    private String name;

    /**
     * 价格
     */
    @TableField(value = "price")
    @ApiModelProperty(value="价格")
    private Double price;

    /**
     * 描述
     */
    @TableField(value = "`desc`")
    @ApiModelProperty(value="描述")
    private String desc;

    /**
     * 标签
     */
    @TableField(value = "tags")
    @ApiModelProperty(value="标签")
    private String tags;

    private static final long serialVersionUID = 1L;
}
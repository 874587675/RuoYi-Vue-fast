package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChunkStrategy implements Serializable {
    // 分段设置相关属性
    @ApiModelProperty("图片知识库的标注方式：\n" +
            "0：（默认）系统自动标注描述信息\n" +
            "1：手工标注。上传图片后需要再次调用 API 更新知识库图片描述来手动设置标注。")
    private Integer caption_type;

    @ApiModelProperty(value = "分段设置类型。0: 自动分段与清洗，1: 自定义分段设置。")
    private Integer chunk_type;

    @ApiModelProperty(value = "最大分段长度，取值范围为 100~2000。在 chunk_type=1 时必选。")
    private Long max_tokens;

    @ApiModelProperty(value = "是否自动过滤连续的空格、换行符和制表符。true: 自动过滤，false: 不自动过滤。")
    private Boolean remove_extra_spaces;

    @ApiModelProperty(value = "是否自动过滤所有 URL 和电子邮箱地址。true: 自动过滤，false: 不自动过滤。")
    private Boolean remove_urls_emails;

    @ApiModelProperty(value = "分段标识符。在 chunk_type=1 时必选。")
    private String separator;

}

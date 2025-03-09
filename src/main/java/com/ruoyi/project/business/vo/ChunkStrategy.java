package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChunkStrategy {
    // 分段设置相关属性
    @ApiModelProperty(value = "分段设置类型。0: 自动分段与清洗，1: 自定义分段设置。")
    private Integer chunkType;

    @ApiModelProperty(value = "最大分段长度，取值范围为 100~2000。在 chunk_type=1 时必选。")
    private Integer maxTokens;

    @ApiModelProperty(value = "是否自动过滤连续的空格、换行符和制表符。true: 自动过滤，false: 不自动过滤。")
    private Boolean removeExtraSpaces;

    @ApiModelProperty(value = "是否自动过滤所有 URL 和电子邮箱地址。true: 自动过滤，false: 不自动过滤。")
    private Boolean removeUrlsEmails;

    @ApiModelProperty(value = "分段标识符。在 chunk_type=1 时必选。")
    private String separator;

    @ApiModelProperty(value = "知识库类型。0: 文本类型，2: 图片类型。")
    private Integer formatType;

}

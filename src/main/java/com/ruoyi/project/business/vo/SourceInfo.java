package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceInfo implements Serializable {
    @ApiModelProperty(value = "文件的上传方式。支持设置为\n" +
            "0: 本地文件上传。\n" +
            "1: 表示上传在线网页。\n" +
            "2: 自定义类型。")
    private Integer document_source;

    @ApiModelProperty(value = "本地文件的 Base64 编码。\n" +
            "上传本地文件时必选。")
    private String file_base64;

    @ApiModelProperty(value = "本地文件格式，即文件后缀，例如 txt。格式支持 pdf、txt、doc、docx 类型。\n" +
            "上传的文件类型应与知识库类型匹配，例如 txt 文件只能上传到文档类型的知识库中。\n" +
            "上传本地文件时必选。")
    private String file_type;

    @ApiModelProperty(value = "通过上传文件接口获取的文件 ID。")
    private Integer source_file_id;

    @ApiModelProperty(value = "网页的 URL 地址。\n" +
            "上传在线网页时必选。")
    private String web_url;
}

package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.RequestBody;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDocument implements Serializable {

    @ApiModelProperty(value = "知识库文件ID")
    private String documentId;

    @ApiModelProperty(value = "知识库文件名称")
    private String documentName;

    @ApiModelProperty(value = "更新知识库文件规则")
    private UpdateRule updateRule;

}


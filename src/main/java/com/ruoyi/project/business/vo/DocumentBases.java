package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentBases {
    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "上传文件信息")
    private SourceInfo sourceInfo;

    @ApiModelProperty(value = "上传文件规则")
    private UpdateRule updateRule;
}

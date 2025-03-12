package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateKnowledge {
    @ApiModelProperty(value = "知识库名称，长度不超过 100 个字符")
    private String name;

    @ApiModelProperty(value = "知识库图标，传入上传文件接口中获取的 file_id")
    private String file_id;

    @ApiModelProperty(value = "知识库描述信息")
    private String description;
}

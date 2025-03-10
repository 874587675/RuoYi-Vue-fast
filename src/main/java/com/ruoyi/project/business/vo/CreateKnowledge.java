package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateKnowledge implements Serializable {
    @ApiModelProperty(value = "知识库名称，长度不超过 100 个字符")
    private String name;

    @ApiModelProperty(value = "知识库所在的空间的 Space ID")
    private String space_id;

    @ApiModelProperty(value = "知识库类型，0：文本类型，2：图片类型")
    private Integer format_type;

    @ApiModelProperty(value = "知识库描述信息")
    private String description;

    @ApiModelProperty(value = "知识库图标，传入上传文件接口中获取的 file_id")
    private String file_id;

}

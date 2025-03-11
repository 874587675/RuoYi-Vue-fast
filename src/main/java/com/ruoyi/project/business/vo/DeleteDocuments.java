package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDocuments implements Serializable {
    @ApiModelProperty(value = "待删除的知识库文件列表。数组最大长度为 100，即一次性最多可删除 100 个文件。")
    List<String> documentIds;
}

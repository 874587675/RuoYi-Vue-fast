package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadDocumentProgress {

    @ApiModelProperty(value = "需要获取上传进度的文件 ID。")
    List<String> document_ids;
}

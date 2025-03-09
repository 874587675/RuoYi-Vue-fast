package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDocument implements Serializable {
    @ApiModelProperty(value = "待查看文件的知识库 ID")
    private Integer datasetId;

    @ApiModelProperty(value = "分页查询时的页码，默认为 1")
    private Integer page;

    @ApiModelProperty(value = "分页大小，默认为 10")
    private Integer size;
}

package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowIdList {
    @ApiModelProperty(value = "智能体的工作流列表配置。")
    private List<WorkflowIdInfo> ids;
}

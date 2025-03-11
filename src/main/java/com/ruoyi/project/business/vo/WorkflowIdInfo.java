package com.ruoyi.project.business.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowIdInfo {
    @ApiModelProperty(value = "智能体绑定的工作流 ID。\n" +
            "进入工作流的编排页面，在页面 URL 中，workflow 参数后的数字就是工作流 ID。\n" +
            "例如 https://www.coze.com/work_flow?space_id=42463***&workflow_id=73505836754923***，工作流 ID 为 73505836754923***。" ,required = true)
    private String id;
}

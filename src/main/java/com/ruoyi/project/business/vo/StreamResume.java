package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamResume {
    @ApiModelProperty(value = "待执行的 Workflow ID，此工作流应已发布。\n" +
            "进入 Workflow 编排页面，在页面 URL 中，workflow 参数后的数字就是 Workflow ID。\n" +
            "例如 https://www.coze.com/work_flow?space_id=42463***&workflow_id=73505836754923***，Workflow ID 为 73505836754923***。", required = true)
    private String workflow_id;

    @ApiModelProperty(value = "工作流执行中断事件 ID。你可以从执行工作流（流式响应）的响应信息中获得中断事件 ID。",required = true)
    private String event_id;

    @ApiModelProperty(value = "恢复执行时，用户对智能体指定问题的回复。回复中应包含问答节点中的必选参数，否则工作流会再次中断并提问。",required = true)
    private String resume_data;

    @ApiModelProperty(value = "中断类型，你可以从执行工作流（流式响应）的响应信息中获得中断时间的中断类型。", required = true)
    private Integer interrupt_type;
}

package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatWorkFlow {
    @ApiModelProperty(value = "待执行的 Workflow ID，此工作流应已发布。\n" +
            "进入 Workflow 编排页面，在页面 URL 中，workflow 参数后的数字就是 Workflow ID。\n" +
            "例如 https://www.coze.com/work_flow?space_id=42463***&workflow_id=73505836754923***，Workflow ID 为 73505836754923***。", required = true)
    private String workflow_id;

    @ApiModelProperty(value = "对话中用户问题和历史消息。数组长度限制为 50，即最多传入 50 条消息。 \n" +
            "你需要通过此字段传入本次对话中用户的问题，也就是对话流的输入参数 USER_INPUT 的值。\n" +
            "可以同时传入多条历史消息，也就是本次对话的上下文。多条消息应按对话顺序排列，最后一条消息应为 role=user 的记录，也就是本次对话中用户的问题；其他消息为历史消息。")
    private List<AdditionalMessagesObject> messages;

    @ApiModelProperty(value = "工作流开始节点的输入参数及取值，你可以在指定工作流的编排页面查看参数列表。")
    private Map<String,Object> parameters;

    @ApiModelProperty(value = "工作流所在的应用 ID。\n" +
            "你可以通过应用的业务编排页面 URL 中获取应用 ID，也就是 URL 中 project-ide 参数后的一串字符。\n" +
            "例如 https://www.coze.cn/space/739174157340921****/project-ide/743996105122521****/workflow/744102227704147**** 中，应用的 ID 为 743996105122521****。\n" +
            "仅运行扣子应用中的工作流时，才需要设置 app_id。智能体绑定的工作流、空间资源库中的工作流无需设置 app_id。")
    private String app_id;

    @ApiModelProperty(value = "需要关联的智能体ID。 部分工作流执行时需要指定关联的 Bot，例如存在数据库节点、变量节点等节点的工作流。")
    private String bot_id;

    @ApiModelProperty(value = "对话流对应的会话 ID，对话流产生的消息会保存到此对话中。会话默认为开始节点设置的 CONVERSATION_NAME，也可以通过 conversation_id 参数指定会话。")
    private String conversation_id;

    @ApiModelProperty(value = "用于指定一些额外的字段，以 Map[String][String] 格式传入。例如某些插件 会隐式用到的经纬度等字段。\n" +
            "目前仅支持以下字段：\n" +
            "latitude：String 类型，表示经度。\n" +
            "longitude：String 类型，表示纬度。\n" +
            "user_id：String 类型，表示用户 ID。")
    private Map<String,String> ext;
}

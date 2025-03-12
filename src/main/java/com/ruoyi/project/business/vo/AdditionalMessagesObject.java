package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalMessagesObject {
    @ApiModelProperty(value = "发送这条消息的实体。取值：\n" +
            "user：代表该条消息内容是用户发送的。\n" +
            "assistant：代表该条消息内容是模型发送的", required = true)
    private String role;

    @ApiModelProperty(value = "消息类型。默认为 question。\n" +
            "question：用户输入内容。\n" +
            "answer：模型返回给用户的消息内容，支持增量返回。如果对话流绑定了消息节点，可能会存在多 answer 场景，此时可以用流式返回的结束标志来判断所有 answer 完成。\n" +
            "function_call：智能体对话过程中调用函数（function call）的中间结果。 \n" +
            "tool_response：调用工具 （function call）后返回的结果。")
    private String type;


    @ApiModelProperty(value = "消息的内容，仅支持纯文本。")
    private String content;

    @ApiModelProperty(value = "消息内容的类型。\n" +
            "content_type 固定为 text，表示普通文本。\n" +
            "注意指定 content字段时，应同时设置 content_type。 ")
    private String content_type;

    @ApiModelProperty(value = "创建消息时的附加消息，获取消息时也会返回此附加消息。\n" +
            "自定义键值对，应指定为 Map 对象格式。长度为 16 对键值对，其中键（key）的长度范围为 1～64 个字符，值（value）的长度范围为 1～512 个字符。")
    private Map<String, String> meta_data;
}

package com.ruoyi.project.business.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelInfoConfig {
    @ApiModelProperty(value = "携带上下文轮数")
    private int contextRound;

    @ApiModelProperty(value = "重复语句惩罚")
    private double frequencyPenalty;

    @ApiModelProperty(value = "最大回复长度")
    private int maxTokens;

    @ApiModelProperty(value = "智能体绑定的模型 ID", required = true)
    private String modelId;

    @ApiModelProperty(value = "重复主题惩罚")
    private double presencePenalty;

    @ApiModelProperty(value = "输出格式" +
            "text：文本。\n" +
            "markdown：Markdown格式。\n" +
            "json：JSON 格式。")
    private String responseFormat;

    @ApiModelProperty(value = "生成随机性")
    private double temperature;

    @ApiModelProperty(value = "Top K")
    private int topK;

    @ApiModelProperty(value = "Top P，即累计概率")
    private double topP;
}

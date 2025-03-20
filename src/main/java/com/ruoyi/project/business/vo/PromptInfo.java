package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromptInfo {
    @ApiModelProperty(value = "智能体的人设与回复逻辑。长度为 0~ 20,000 个字符。默认为空。")
    private String prompt;
}

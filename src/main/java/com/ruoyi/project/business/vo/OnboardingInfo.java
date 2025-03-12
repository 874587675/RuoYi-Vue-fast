package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnboardingInfo {
    @ApiModelProperty(value = "智能体的开场白，长度为 0~300 个字符")
    private String prologue;
    @ApiModelProperty(value = "智能体的开场白预置问题，问题数量不限")
    private List<String> suggested_questions;
}

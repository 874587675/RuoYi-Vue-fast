package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBot {
    @ApiModelProperty(value = "智能体所在的空间的 Space ID", required = true)
    private String space_id;

    @ApiModelProperty(value = "智能体的名称，长度为 1~20 个字符", required = true)
    private String name;

    @ApiModelProperty(value = "智能体的描述信息，长度为 0~500 个字符")
    private String description;

    @ApiModelProperty(value = "智能体头像的文件 ID")
    private String icon_file_id;

    @ApiModelProperty(value = "智能体的人设与回复逻辑，长度为 0~20000 个字符")
    private PromptInfo prompt_info;

    @ApiModelProperty(value = "智能体的开场白，长度为 0~300 个字符")
    private OnboardingInfo onboarding_info;

    @ApiModelProperty(value = "智能体的插件列表配置")
    private PluginIdList plugin_id_list;

    @ApiModelProperty(value = "智能体的工作流列表配置")
    private WorkflowIdList workflow_id_list;

    @ApiModelProperty(value = "智能体模型信息配置")
    private ModelInfoConfig model_info_config;


}

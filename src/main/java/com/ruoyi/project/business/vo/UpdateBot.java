package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBot {
    @ApiModelProperty(value = "待修改配置的智能体 ID。\n" +
            "进入智能体的 开发页面，开发页面 URL 中 bot 参数后的数字就是智能体 ID。\n" +
            "例如https://www.coze.cn/space/341****/bot/73428668*****，bot ID 为73428668*****。"
            , required = true)
    private String space_id;

    @ApiModelProperty(value = "智能体的名称，长度为 1~20 个字符")
    private String name;

    @ApiModelProperty(value = "智能体的描述信息。长度为 0~ 500 个字符。默认为空。")
    private String description;

    @ApiModelProperty(value = "作为智能体头像的文件 ID。\n" +
            "未指定文件 ID 时，扣子平台默认为智能体指定一个头像。\n" +
            "如需使用自定义头像，应先通过上传文件接口上传本地文件，从接口响应中获取文件 ID。")
    private String icon_file_id;

    @ApiModelProperty(value = "智能体的人设与回复逻辑，长度为 0~20000 个字符")
    private PromptInfo prompt_info;

    @ApiModelProperty(value = "智能体的开场白，长度为 0~300 个字符")
    private OnboardingInfo onboarding_info;

    @ApiModelProperty(value = "智能体的知识库配置")
    private Knowledge knowledge;

    @ApiModelProperty(value = "智能体的插件列表配置")
    private PluginIdList plugin_id_list;

    @ApiModelProperty(value = "智能体的工作流列表配置")
    private WorkflowIdList workflow_id_list;

    @ApiModelProperty(value = "智能体模型信息配置")
    private ModelInfoConfig model_info_config;
}

package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PluginIdInfo {
    @ApiModelProperty(value = "智能体绑定的插件工具 ID。\n" +
            "在扣子平台中打开指定插件工具页面，页面 URL 中 tool 参数后的数字就是插件工具 ID。\n" +
            "例如 https://www.coze.cn/space/731762895654132****/plugin/735057533610021****/tool/735057536617362****，插件工具 ID 为 735057536617362****。"
            , required = true)
    private String api_id;
    @ApiModelProperty(value = "智能体绑定的插件 ID。\n" +
            "在扣子平台中打开指定插件页面，页面 URL 中 plugin 参数后的数字就是插件 ID。例如\n" +
            "https://www.coze.cn/space/728826510807216****/plugin/731198934927553****，插件 ID 为 731198934927553****。"
            , required = true)
    private String plugin_id;
}

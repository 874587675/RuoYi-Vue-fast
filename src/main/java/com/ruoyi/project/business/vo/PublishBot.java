package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishBot {
    @ApiModelProperty(value = "要发布的智能体 ID。\n" +
            "进入智能体的 开发页面，开发页面 URL 中 bot 参数后的数字就是智能体 ID。\n" +
            "例如https://www.coze.com/space/341****/bot/73428668*****，bot ID 为73428668*****。")
    private String bot_id;

    @ApiModelProperty(value = "智能体的发布渠道 ID 列表。目前支持通过此 API 将智能体发布为 Agent as API，WebSDK 以及自定义渠道。\n" +
            "Agent as API: 1024\n" +
            "WebSDK: 999\n" +
            "自定义渠道：自定义渠道 ID，获取方式可以参考 渠道入驻概述。需要保证使用自定义渠道的创建者的访问令牌来进行访问。")
    private List<String> connector_ids;

}

package com.ruoyi.project.business.constant;

public class BotConstant {
    /**
     * 获取智能体配置(GET)
     */
    public static final String GET_METADATA = "https://api.coze.cn/v1/bot/get_online_info?";

    /**
     * 创建智能体(POST)
     */
    public static final String CREATE_BOT = "https://api.coze.cn/v1/bot/create";

    /**
     * 创建智能体(POST)
     */
    public static final String EDIT = "https://api.coze.cn/v1/bot/update";

    /**
     * 发布智能体(POST)
     */
    public static final String BOT_PUBLISH = "https://api.coze.cn/v1/bot/publish";

    /**
     * 查看智能体列表(GET)
     */
    public static final String GET_PUBLISHED_BOT = "https://api.coze.cn/v1/space/published_bots_list";
}

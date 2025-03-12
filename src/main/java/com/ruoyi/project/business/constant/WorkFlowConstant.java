package com.ruoyi.project.business.constant;

public class WorkFlowConstant {
    /**
     * 执行工作流(POST)
     */
    public static final String RUN = "https://api.coze.cn/v1/workflow/run";

    /**
     * 执行工作流 流式响应(POST)
     */
    public static final String STREAM_RUN = "https://api.coze.cn/v1/workflow/stream_run";

    /**
     * 恢复运行工作流(POST)
     */
    public static final String STREAM_RESUME = "https://api.coze.cn/v1/workflow/stream_resume";

    /**
     * 查询工作流异步执行结果(GET)
     */
    public static final String LIST_RUN_HISTORY = "https://api.coze.cn/v1/workflows/";

    /**
     * 执行对话流(POST)
     */
    public static final String WORKFLOWS_CHAT = "https://api.coze.cn/v1/workflows/chat";
}

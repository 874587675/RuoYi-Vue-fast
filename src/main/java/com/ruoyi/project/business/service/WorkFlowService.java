package com.ruoyi.project.business.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.vo.ChatWorkFlow;
import com.ruoyi.project.business.vo.RunWorkFlow;
import com.ruoyi.project.business.vo.StreamResume;
import com.ruoyi.project.business.vo.StreamRunWorkFlow;

import java.io.IOException;

public interface WorkFlowService {

    /**
     * 执行工作流
     *
     * @param token
     * @param runWorkFlow
     * @return
     */
    AjaxResult run(String token, RunWorkFlow runWorkFlow) throws IOException;

    /**
     * 执行工作流（流式响应）
     *
     * @param token
     * @param streamRunWorkFlow
     * @return
     */
    AjaxResult streamRun(String token, StreamRunWorkFlow streamRunWorkFlow) throws IOException;

    /**
     * 恢复运行工作流
     *
     * @param token
     * @param streamResume
     * @return
     */
    AjaxResult streamResume(String token, StreamResume streamResume) throws IOException;

    /**
     * 查询工作流异步执行结果
     *
     * @param token
     * @param workflowId
     * @param executeId
     * @return
     */
    AjaxResult listRunHistory(String token, String workflowId, String executeId) throws IOException;

    /**
     * 执行对话流
     *
     * @param token
     * @param chatWorkFlow
     * @return
     */
    AjaxResult chat(String token, ChatWorkFlow chatWorkFlow) throws IOException;
}

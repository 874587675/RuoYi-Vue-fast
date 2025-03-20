package com.ruoyi.project.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.constant.KnowledgeConstant;
import com.ruoyi.project.business.constant.WorkFlowConstant;
import com.ruoyi.project.business.service.WorkFlowService;
import com.ruoyi.project.business.vo.ChatWorkFlow;
import com.ruoyi.project.business.vo.RunWorkFlow;
import com.ruoyi.project.business.vo.StreamResume;
import com.ruoyi.project.business.vo.StreamRunWorkFlow;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WorkFlowServiceImpl implements WorkFlowService {

    @Override
    public AjaxResult run(String token, RunWorkFlow runWorkFlow) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(runWorkFlow);
        // 构建请求
        Request request = new Request.Builder()
                .url(WorkFlowConstant.RUN)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("执行工作流请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "执行工作流请求成功");
                ajaxResult.put("data", string);
            } else {
                String string = response.body().string();
                log.info("执行工作流请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "执行工作流请求失败");
                ajaxResult.put("data", string);
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult streamRun(String token, StreamRunWorkFlow streamRunWorkFlow) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(streamRunWorkFlow);
        // 构建请求
        Request request = new Request.Builder()
                .url(WorkFlowConstant.STREAM_RUN)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("执行工作流（流式响应）请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "执行工作流（流式响应）请求成功");
                ajaxResult.put("data", string);
            } else {
                String string = response.body().string();
                log.info("执行工作流（流式响应）请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "执行工作流（流式响应）请求失败");
                ajaxResult.put("data", string);
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult streamResume(String token, StreamResume streamResume) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(streamResume);
        // 构建请求
        Request request = new Request.Builder()
                .url(WorkFlowConstant.STREAM_RESUME)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("恢复运行工作流请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "恢复运行工作流请求成功");
                ajaxResult.put("data", string);
            } else {
                String string = response.body().string();
                log.info("恢复运行工作流请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "恢复运行工作流请求失败");
                ajaxResult.put("data", string);
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult listRunHistory(String token, String workflowId, String executeId) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        StringBuilder listUrl = new StringBuilder(WorkFlowConstant.LIST_RUN_HISTORY + workflowId + "/run_histories/" + executeId);
        // 构建请求
        Request request = new Request.Builder()
                .url(listUrl.toString())
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .get()
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "查询工作流异步执行结果请求成功");
                ajaxResult.put("data",response.body().string());
            } else {
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "查询工作流异步执行结果请求失败");
                ajaxResult.put("data",response.body().string());
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult chat(String token, ChatWorkFlow chatWorkFlow) throws IOException {

        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(chatWorkFlow);
        // 构建请求
        Request request = new Request.Builder()
                .url(WorkFlowConstant.WORKFLOWS_CHAT)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("执行对话流请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "执行对话流请求成功");
                ajaxResult.put("data", string);
            } else {
                String string = response.body().string();
                log.info("执行对话流请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "执行对话流请求失败");
                ajaxResult.put("data", string);
            }
        }
        return ajaxResult;
    }

    //转换为RequestBody格式
    private RequestBody transRequestBody(Object object) {
        String json = JSONObject.toJSONString(object);
        return RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
    }
}

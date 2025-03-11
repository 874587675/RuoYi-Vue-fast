package com.ruoyi.project.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.constant.BotConstant;
import com.ruoyi.project.business.constant.KnowledgeConstant;
import com.ruoyi.project.business.constant.WorkspaceConstant;
import com.ruoyi.project.business.service.BotService;
import com.ruoyi.project.business.vo.CreateBot;
import com.ruoyi.project.business.vo.PublishBot;
import com.ruoyi.project.business.vo.UpdateBot;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class BotServiceImpl implements BotService {
    @Override
    public AjaxResult getMetadata(String token, String botId) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        // 构建请求
        Request request = new Request.Builder()
                .url(BotConstant.GET_METADATA + "bot_id=" + botId + "&")
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .get()
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("查询知识库请求成功！");
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "查询知识库请求成功");
                System.out.println("响应内容: " + response.body().string());
            } else {
                System.out.println("查询知识库请求失败，状态码: " + response.code());
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "查询知识库请求失败");
                System.out.println("错误信息: " + response.body().string());
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult createBot(String token, CreateBot createBot) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(createBot);
        // 构建请求
        Request request = new Request.Builder()
                .url(BotConstant.CREATE_BOT)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("创建智能体请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "创建智能体请求成功");
                ajaxResult.put("data",string);
            } else {
                String string = response.body().string();
                log.info("创建智能体请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "创建智能体请求失败");
                ajaxResult.put("data",string);
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult editBot(String token, UpdateBot updateBot) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(updateBot);
        // 构建请求
        Request request = new Request.Builder()
                .url(BotConstant.EDIT)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("更新智能体请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "更新智能体请求成功");
                ajaxResult.put("data",string);
            } else {
                String string = response.body().string();
                log.info("更新智能体请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "更新智能体请求失败");
                ajaxResult.put("data",string);
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult botPublish(String token, PublishBot publishBot) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(publishBot);
        // 构建请求
        Request request = new Request.Builder()
                .url(BotConstant.BOT_PUBLISH)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("发布智能体请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "发布智能体请求成功");
                ajaxResult.put("data",string);
            } else {
                String string = response.body().string();
                log.info("发布智能体请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "发布智能体请求失败");
                ajaxResult.put("data",string);
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult getPublishedBot(String token, String spaceId, Integer pageIndex,Integer pageSize) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();

        StringBuilder listUrl = new StringBuilder(BotConstant.GET_PUBLISHED_BOT + "space_id=" + spaceId + "&");
        // 创建一个参数与值的映射
        Map<String, Object> params = new HashMap<>();
        if (pageIndex != null) {
            params.put("page_num", pageIndex);
        } else if (pageSize != null) {
            params.put("page_size", pageSize);
        }
        // 遍历并拼接 URL 参数
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            listUrl.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        // 构建请求
        Request request = new Request.Builder()
                .url(listUrl.toString())
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .get()
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("查看智能体列表请求成功！");
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "查看智能体列表请求成功");
                System.out.println("响应内容: " + response.body().string());
            } else {
                System.out.println("查看智能体列表请求失败，状态码: " + response.code());
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "查看智能体列表请求失败");
                System.out.println("错误信息: " + response.body().string());
            }
        }
        return ajaxResult;
    }

    //转换为RequestBody格式
    private RequestBody transRequestBody(Object object){
        String json = JSONObject.toJSONString(object);
        return RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
    }
}

package com.ruoyi.project.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.constant.KnowledgeConstant;
import com.ruoyi.project.business.service.KnowledgeService;
import com.ruoyi.project.business.vo.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class KnowledgeServiceImpl implements KnowledgeService {
    
    @Override
    public AjaxResult updateDocument(String token,UpdateDocument updateDocument) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        String json = JSONObject.toJSONString(updateDocument);
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        // 构建请求
        Request request = new Request.Builder()
                .url(KnowledgeConstant.UPDATE_DOCUMENT)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(body)  // 使用POST请求并设置请求体
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                log.info("请求成功!");
                log.info("响应内容: " + response.body().string());
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "修改知识库文件请求成功");
            } else {
                log.info("请求失败，状态码: " + response.code());
                log.info("错误信息: " + response.body().string());
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "修改知识库文件请求失败");
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult createDocument(String token, CreateDocument createDocument) {
        return null;
    }

    @Override
    public AjaxResult deleteDocument(String token, DeleteDocuments deleteDocuments) {
        return null;
    }

    @Override
    public AjaxResult listDocument(String token, ListDocument listDocument) {
        return null;
    }

    @Override
    public AjaxResult createKnowledge(String token, CreateKnowledge createKnowledge) {
        return null;
    }

    @Override
    public AjaxResult listKnowledge(String token, String spaceId, String name, String pageNum, String pageSize) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();

        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient();

//        RequestBody body = RequestBody.create(updateDocument.toString(), MediaType.get("application/json; charset=utf-8"));
        // 构建请求
        Request request = new Request.Builder()
                .url(KnowledgeConstant.CREATE_LIST_KNOWLEDGE + "space_id=" + spaceId + "&")
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .get()  // 使用 POST 请求并设置请求体
                .build();

        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("请求成功！");
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "请求成功");
                System.out.println("响应内容: " + response.body().string());
            } else {
                System.out.println("请求失败，状态码: " + response.code());
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "请求失败");
                System.out.println("错误信息: " + response.body().string());
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult updateKnowledge(String token, String datasetId, UpdateKnowledge updateKnowledge) {
        return null;
    }

    @Override
    public AjaxResult deleteKnowledge(String token, String datasetId) {
        return null;
    }

    @Override
    public AjaxResult readDocumentProgress(String token, ReadDocumentProgress readDocumentProgress) {
        return null;
    }

    @Override
    public AjaxResult listPhoto(String token, String datasetId, Integer pageNum, Integer pageSize, String keyword, Boolean hasCaption) {
        return null;
    }
}

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
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KnowledgeServiceImpl implements KnowledgeService {
    
    @Override
    public AjaxResult updateDocument(String token,UpdateDocument updateDocument) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(updateDocument);
        // 构建请求
        Request request = new Request.Builder()
                .url(KnowledgeConstant.UPDATE_DOCUMENT)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("修改知识库文件请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "修改知识库文件请求成功");
                ajaxResult.put("data",string);
            } else {
                String string = response.body().string();
                log.info("修改知识库文件请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "修改知识库文件请求失败");
                ajaxResult.put("data",string);
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult createDocument(String token, CreateDocument createDocument) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(createDocument);
        // 构建请求
        Request request = new Request.Builder()
                .url(KnowledgeConstant.CREATE_DOCUMENT)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("创建知识库文件请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "创建知识库文件请求成功");
                ajaxResult.put("data",string);
            } else {
                String string = response.body().string();
                log.info("创建知识库文件请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "创建知识库文件请求失败");
                ajaxResult.put("data",string);
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult deleteDocument(String token, DeleteDocuments deleteDocuments) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(deleteDocuments);
        // 构建请求
        Request request = new Request.Builder()
                .url(KnowledgeConstant.DELETE_DOCUMENT)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("删除知识库文件请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "删除知识库文件请求成功");
                ajaxResult.put("data",string);
            } else {
                String string = response.body().string();
                log.info("删除知识库文件请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "删除知识库文件请求失败");
                ajaxResult.put("data",string);
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult listDocument(String token, ListDocument listDocument) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(listDocument);
        // 构建请求
        Request request = new Request.Builder()
                .url(KnowledgeConstant.LIST_DOCUMENT)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("查询知识库文件列表请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "查询知识库文件列表请求成功");
                ajaxResult.put("data",string);
            } else {
                String string = response.body().string();
                log.info("查询知识库文件列表请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "查询知识库文件列表请求失败");
                ajaxResult.put("data",string);
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult createKnowledge(String token, CreateKnowledge createKnowledge) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(createKnowledge);
        // 构建请求
        Request request = new Request.Builder()
                .url(KnowledgeConstant.CREATE_LIST_KNOWLEDGE)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("创建知识库请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "创建知识库请求成功");
                ajaxResult.put("data",string);
            } else {
                String string = response.body().string();
                log.info("创建知识库请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "创建知识库请求失败");
                ajaxResult.put("data",string);
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult listKnowledge(String token, String spaceId, String name, Integer formatType ,Integer pageNum, Integer pageSize) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        StringBuilder listUrl = new StringBuilder(KnowledgeConstant.CREATE_LIST_KNOWLEDGE + "space_id=" + spaceId + "&");
        // 创建一个参数与值的映射
        Map<String, Object> params = new HashMap<>();
        if (name != null && !name.isEmpty()) {
            params.put("name", name);
        } else if (formatType != null) {
            params.put("format_type", formatType);
        } else if (pageNum != null) {
            params.put("page_num", pageNum);
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
    public AjaxResult updateKnowledge(String token, String datasetId, UpdateKnowledge updateKnowledge) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(updateKnowledge);
        // 构建请求
        Request request = new Request.Builder()
                .url(KnowledgeConstant.UPDATE_DELETE_KNOWLEDGE + datasetId +"?")
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .put(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("更新知识库请求成功！");
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "更新知识库请求成功");
                System.out.println("响应内容: " + response.body().string());
            } else {
                System.out.println("更新知识库请求失败，状态码: " + response.code());
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "更新知识库请求失败");
                System.out.println("错误信息: " + response.body().string());
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult deleteKnowledge(String token, String datasetId) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        // 构建请求
        Request request = new Request.Builder()
                .url(KnowledgeConstant.UPDATE_DELETE_KNOWLEDGE + datasetId +"?")
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .delete()
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("删除知识库请求成功！");
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "删除知识库请求成功");
                System.out.println("响应内容: " + response.body().string());
            } else {
                System.out.println("删除知识库请求失败，状态码: " + response.code());
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "删除知识库请求失败");
                System.out.println("错误信息: " + response.body().string());
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult readDocumentProgress(String token,String datasetId, ReadDocumentProgress readDocumentProgress) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(readDocumentProgress);
        // 构建请求
        Request request = new Request.Builder()
                .url(KnowledgeConstant.READ_DOCUMENT_PROGRESS + datasetId +"/process?")
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("查看知识库上传进度请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "查看知识库上传进度请求成功");
                ajaxResult.put("data",string);
            } else {
                String string = response.body().string();
                log.info("查看知识库上传进度请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "查看知识库上传进度请求失败");
                ajaxResult.put("data",string);
            }
        }
        return ajaxResult;
    }

    @Override
    public AjaxResult listPhoto(String token, String datasetId, Integer pageNum, Integer pageSize, String keyword, Boolean hasCaption) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        StringBuilder listUrl = new StringBuilder(KnowledgeConstant.LIST_PHOTO + datasetId + "/images?");
        // 创建一个参数与值的映射
        Map<String, Object> params = new HashMap<>();
        if (keyword != null && !keyword.isEmpty()) {
            params.put("keyword", keyword);
        } else if (hasCaption != null) {
            params.put("has_caption", hasCaption);
        } else if (pageNum != null) {
            params.put("page_num", pageNum);
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
                System.out.println("查询知识库图片列表请求成功！");
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "查询知识库图片列表请求成功");
                System.out.println("响应内容: " + response.body().string());
            } else {
                System.out.println("查询知识库图片列表请求失败，状态码: " + response.code());
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "查询知识库图片列表请求失败");
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

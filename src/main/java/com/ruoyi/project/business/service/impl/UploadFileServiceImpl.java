package com.ruoyi.project.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.constant.FileConstant;
import com.ruoyi.project.business.constant.KnowledgeConstant;
import com.ruoyi.project.business.service.UploadFileService;
import com.ruoyi.project.business.vo.FileUpload;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Override
    public AjaxResult uploadFile(String token, FileUpload fileUpload) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 转换成JSON字符串
        RequestBody requestBody = transRequestBody(fileUpload);
        // 构建请求
        Request request = new Request.Builder()
                .url(FileConstant.UPLOAD_FILE)
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String string = response.body().string();
                log.info("上传文件请求成功!");
                log.info("响应内容: " + string);
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "上传文件请求成功");
                ajaxResult.put("data",string);
            } else {
                String string = response.body().string();
                log.info("上传文件请求失败，状态码: " + response.code());
                log.info("错误信息: " + string);
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "上传文件请求失败");
                ajaxResult.put("data",string);
            }
        }
        return ajaxResult;
    }


    @Override
    public AjaxResult retrieveFile(String token, String fileId) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        // 构建请求
        Request request = new Request.Builder()
                .url(FileConstant.RETRIEVE_FILE + "file_id" + fileId + "&")
                .addHeader("Authorization", "Bearer " + token)  // 添加认证头部
                .get()
                .build();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("查看文件详情请求成功！");
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "查看文件详情请求成功");
                System.out.println("响应内容: " + response.body().string());
            } else {
                System.out.println("查看文件详情请求失败，状态码: " + response.code());
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "查看文件详情请求失败");
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

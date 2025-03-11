package com.ruoyi.project.business.service.impl;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.constant.KnowledgeConstant;
import com.ruoyi.project.business.constant.WorkspaceConstant;
import com.ruoyi.project.business.service.WorkSpaceService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WorkSpaceServiceImpl implements WorkSpaceService {
    @Override
    public AjaxResult listWorkspace(String token, Integer pageNum, Integer pageSize) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        StringBuilder listUrl = new StringBuilder(WorkspaceConstant.LIST_WORKSPACE);
        // 创建一个参数与值的映射
        Map<String, Object> params = new HashMap<>();
        if (pageNum != null) {
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
                System.out.println("查询工作空间请求成功！");
                ajaxResult.put("code", 200);
                ajaxResult.put("msg", "查询工作空间请求成功");
                System.out.println("响应内容: " + response.body().string());
            } else {
                System.out.println("查询工作空间请求失败，状态码: " + response.code());
                ajaxResult.put("code", 500);
                ajaxResult.put("msg", "查询工作空间请求失败");
                System.out.println("错误信息: " + response.body().string());
            }
        }
        return ajaxResult;
    }

}

package com.ruoyi.project.business.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class LargeFileToBase64 {

    /**
     * 将 MultipartFile 转换为 Base64 编码的字符串
     *
     * @param multipartFile 上传的文件
     * @return Base64 编码的字符串
     * @throws IOException 如果文件读取失败
     */
    public static String convertMultipartFileToBase64(MultipartFile multipartFile) throws IOException {
        // 将 MultipartFile 转换为字节数组
        byte[] fileBytes = multipartFile.getBytes();

        // 使用 Base64 编码器将字节数组转换为 Base64 字符串
        return Base64.getEncoder().encodeToString(fileBytes);
    }


    public static String convertLargeMultipartFileToBase64(MultipartFile multipartFile) throws IOException {
        StringBuilder base64Builder = new StringBuilder();
        byte[] buffer = new byte[1024];  // 每次读取 1KB
        int bytesRead;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] chunk = new byte[bytesRead];
                System.arraycopy(buffer, 0, chunk, 0, bytesRead);
                base64Builder.append(Base64.getEncoder().encodeToString(chunk));
            }
        }

        return base64Builder.toString();
    }

}
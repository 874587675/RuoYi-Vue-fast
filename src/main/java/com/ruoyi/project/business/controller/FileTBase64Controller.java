package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.utils.LargeFileToBase64;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@RestController
@RequestMapping("/fileTrans")
@Api(tags = "文件转换模块")
public class FileTBase64Controller {

    @ApiOperation("文件转换成Base64")
    @PostMapping("/fileTBase64")
    public R<String> fileToBase64(@RequestParam MultipartFile multipartFile) throws IOException {
        // 将 MultipartFile 转换为 Base64 字符串
        String base64String = LargeFileToBase64.convertLargeMultipartFileToBase64(multipartFile);
        return R.ok(base64String);
    }


}

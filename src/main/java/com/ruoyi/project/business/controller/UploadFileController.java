package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.service.UploadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
@Api(tags = "上传文件管理模块-上传到coze服务器")
public class UploadFileController {

    @Resource
    private UploadFileService uploadFileService;

    @ApiOperation("上传文件")
    @PostMapping("/uploadFile")
    public AjaxResult uploadFile(@RequestHeader String token, @RequestParam MultipartFile fileUpload) throws IOException {
        return uploadFileService.uploadFile(token,fileUpload);
    }

    @ApiOperation("查看文件详情")
    @GetMapping("/retrieveFile")
    public AjaxResult retrieveFile(@RequestHeader String token, String fileId) throws IOException {
        return uploadFileService.retrieveFile(token,fileId);
    }
}

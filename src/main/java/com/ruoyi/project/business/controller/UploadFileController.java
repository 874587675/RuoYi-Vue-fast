package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.service.UploadFileService;
import com.ruoyi.project.business.vo.FileUpload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
@Api(tags = "上传文件管理模块")
public class UploadFileController {

    @Resource
    private UploadFileService uploadFileService;

    @ApiOperation("上传文件")
    @PostMapping("/uploadFile")
    public AjaxResult uploadFile(@RequestHeader String token, @RequestBody FileUpload fileUpload) throws IOException {
        return uploadFileService.uploadFile(token,fileUpload);
    }

    @ApiOperation("查看文件详情")
    @PostMapping("/retrieveFile")
    public AjaxResult retrieveFile(@RequestHeader String token, String fileId) throws IOException {
        return uploadFileService.retrieveFile(token,fileId);
    }
}

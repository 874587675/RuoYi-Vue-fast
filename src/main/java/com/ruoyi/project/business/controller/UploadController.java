package com.ruoyi.project.business.controller;


import com.ruoyi.common.verify.aliyun.oss.OssUtil;
import com.ruoyi.framework.web.domain.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RequestMapping("/upload")
@RestController
@Api(tags = "上传文件模块-上传到coze服务器")
public class UploadController {
    @Resource
    private OssUtil ossUtil;
    @ApiOperation("上传图片文件")
    @PostMapping("/uploadImage")
    public R<String> uploadImage(@RequestParam MultipartFile file) {
        String url = ossUtil.uploadFileByType(file,"image").getUrl();
        return R.ok(url);
    }

    @ApiOperation("上传音频文件")
    @PostMapping("/uploadAudio")
    public R<String> uploadAudio(@RequestParam MultipartFile file) {
        String url = ossUtil.uploadFileByType(file,"audio").getUrl();
        return R.ok(url);
    }

    @ApiOperation("上传视频文件")
    @PostMapping("/uploadVideo")
    public R<String> uploadVideo(@RequestParam MultipartFile file) {
        String url = ossUtil.uploadFileByType(file,"video").getUrl();
        return R.ok(url);
    }
}

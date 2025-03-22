package com.ruoyi.project.business.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {

    /**
     *
     * @param token
     * @param fileUpload
     * @return
     * @throws IOException
     */
    AjaxResult uploadFile(String token, MultipartFile fileUpload) throws IOException;

    /**
     * 查看文件详情
     * @param token
     * @param fileId
     * @return
     */
    AjaxResult retrieveFile(String token, String fileId) throws IOException;
}

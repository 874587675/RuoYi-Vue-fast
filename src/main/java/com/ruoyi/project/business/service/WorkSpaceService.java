package com.ruoyi.project.business.service;


import com.ruoyi.framework.web.domain.AjaxResult;

import java.io.IOException;

public interface WorkSpaceService{
    public AjaxResult listWorkspace(String token, Integer pageNum, Integer pageSize) throws IOException;
}

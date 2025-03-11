package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.service.WorkSpaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/workspace")
@Api(tags = "工作空间模块")
public class WorkSpaceController {
    @Resource
    private WorkSpaceService workSpaceService;

    @ApiOperation("查看空间列表")
    @GetMapping("/listWorkspace")
    public AjaxResult listWorkspace(@RequestHeader String token,Integer pageNum,Integer pageSize) throws IOException {
        return workSpaceService.listWorkspace(token,pageNum,pageSize);
    }
}

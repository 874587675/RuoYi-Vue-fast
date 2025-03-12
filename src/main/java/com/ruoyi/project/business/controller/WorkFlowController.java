package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.service.WorkFlowService;
import com.ruoyi.project.business.vo.ChatWorkFlow;
import com.ruoyi.project.business.vo.RunWorkFlow;
import com.ruoyi.project.business.vo.StreamResume;
import com.ruoyi.project.business.vo.StreamRunWorkFlow;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/workflow")
@Api(tags = "工作流模块")
public class WorkFlowController {
    @Resource
    private WorkFlowService workFlowService;

    @ApiOperation("执行工作流")
    @PostMapping("/run")
    public AjaxResult run(@RequestHeader String token,@RequestBody RunWorkFlow runWorkFlow) throws IOException {
        return workFlowService.run(token,runWorkFlow);
    }

    @ApiOperation("执行工作流（流式响应）")
    @PostMapping("/streamRun")
    public AjaxResult streamRun(@RequestHeader String token,@RequestBody StreamRunWorkFlow streamRunWorkFlow) throws IOException {
        return workFlowService.streamRun(token,streamRunWorkFlow);
    }

    @ApiOperation("恢复运行工作流")
    @PostMapping("/streamResume")
    public AjaxResult streamResume(@RequestHeader String token,@RequestBody StreamResume streamResume) throws IOException {
        return workFlowService.streamResume(token,streamResume);
    }

    @ApiOperation("查询工作流异步执行结果")
    @GetMapping("/listRunHistory")
    public AjaxResult listRunHistory(@RequestHeader String token,String workflowId,String executeId) throws IOException {
        return workFlowService.listRunHistory(token,workflowId,executeId);
    }

    @ApiOperation("执行对话流")
    @PostMapping("/chat")
    public AjaxResult chat(@RequestHeader String token,@RequestBody ChatWorkFlow chatWorkFlow) throws IOException {
        return workFlowService.chat(token,chatWorkFlow);
    }
}

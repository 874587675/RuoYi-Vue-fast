package com.ruoyi.project.business.controller;

import com.alipay.api.domain.PublicBindAccount;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.service.BotService;
import com.ruoyi.project.business.vo.CreateBot;
import com.ruoyi.project.business.vo.PublishBot;
import com.ruoyi.project.business.vo.UpdateBot;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/knowledge")
@Api(tags = "智能体模块")
public class BotController {
    @Resource
    private BotService botService;

    @ApiOperation("获取智能体配置")
    @GetMapping("/getMetadata")
    public AjaxResult getMetadata(@RequestHeader String token, String botId) throws IOException {
        return botService.getMetadata(token,botId);
    }

    @ApiOperation("创建智能体")
    @PostMapping("/createBot")
    public AjaxResult createBot(@RequestHeader String token, @RequestBody CreateBot createBot) throws IOException {
        return botService.createBot(token,createBot);
    }

    @ApiOperation("更新智能体")
    @PostMapping("/editBot")
    public AjaxResult editBot(@RequestHeader String token, @RequestBody UpdateBot updateBot) throws IOException {
        return botService.editBot(token,updateBot);
    }

    @ApiOperation("发布智能体")
    @PostMapping("/botPublish")
    public AjaxResult botPublish(@RequestHeader String token, @RequestBody PublishBot publishBot) throws IOException {
        return botService.botPublish(token,publishBot);
    }

    @ApiOperation("查看智能体列表")
    @GetMapping("/getPublishedBot")
    public AjaxResult getPublishedBot(@RequestHeader String token, String spaceId,Integer pageIndex,Integer pageSize) throws IOException {
        return botService.getPublishedBot(token,spaceId,pageIndex,pageSize);
    }
}

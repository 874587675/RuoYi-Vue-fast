package com.ruoyi.project.business.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.vo.CreateBot;
import com.ruoyi.project.business.vo.PublishBot;
import com.ruoyi.project.business.vo.UpdateBot;

import java.io.IOException;

public interface BotService {

    AjaxResult getMetadata(String token, String botId) throws IOException;

    AjaxResult createBot(String token, CreateBot createBot) throws IOException;

    AjaxResult editBot(String token, UpdateBot updateBot) throws IOException;

    AjaxResult botPublish(String token, PublishBot publishBot) throws IOException;

    AjaxResult getPublishedBot(String token,String spaceId,Integer pageIndex,Integer pageSize) throws IOException;
}

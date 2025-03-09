package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.service.KnowledgeService;
import com.ruoyi.project.business.vo.*;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/knowledge")
@Api(tags = "知识库模块")
public class KnowledgeController {
    @Resource
    private KnowledgeService knowledgeService;

    /**
     * 修改知识库文件名称
     */
    @PostMapping("/updateDocument")
    public AjaxResult updateDocument(@RequestHeader String token,@RequestBody UpdateDocument updateDocument) throws IOException {
        return knowledgeService.updateDocument(token,updateDocument);
    }

    /**
     * 创建知识库文件
     */
    @PostMapping("/createDocument")
    public AjaxResult createDocument(@RequestHeader String token,@RequestBody CreateDocument createDocument) {
        return null;
    }

    /**
     * 删除知识库文件
     */
    @PostMapping("/deleteDocument")
    public AjaxResult deleteDocument(@RequestHeader String token,@RequestBody DeleteDocuments deleteDocuments) {
        return null;
    }

    /**
     * 查看知识库文件列表
     */
    @PostMapping("/listDocument")
    public AjaxResult listDocument(@RequestHeader String token,@RequestBody ListDocument listDocument) {
        return null;
    }

    /**
     * 创建知识库
     */
    @PostMapping("/createKnowledge")
    public AjaxResult createKnowledge(@RequestHeader String token,@RequestBody CreateKnowledge createKnowledge) {
        return null;
    }

    /**
     * 查看知识库列表
     */
    @GetMapping("/listKnowledge")
    public AjaxResult listKnowledge(@RequestHeader String token, String spaceId, String name, String pageNum, String pageSize) throws IOException {
        return knowledgeService.listKnowledge(token,spaceId,name,pageNum,pageSize);
    }

    /**
     * 修改知识库信息
     */
    @PutMapping("/updateKnowledge/{datasetId}")
    public AjaxResult updateKnowledge(@RequestHeader String token,@PathVariable("datasetId") String datasetId,@RequestBody UpdateKnowledge updateKnowledge) {
        return null;
    }

    /**
     * 删除知识库
     */
    @DeleteMapping("/deleteKnowledge/{datasetId}")
    public AjaxResult deleteKnowledge(@RequestHeader String token, @PathVariable("datasetId") String datasetId) {
        return null;
    }

    /**
     * 查看知识库文件上传进度
     */
    @PostMapping("/readDocumentProgress/{datasetId}")
    public AjaxResult readDocumentProgress(@RequestHeader String token,@PathVariable("datasetId") String datasetId,@RequestBody ReadDocumentProgress readDocumentProgress) {
        return null;
    }

    /**
     * 查看知识库图片列表
     */
    @PostMapping("/listPhoto/{datasetId}")
    public AjaxResult listPhoto(@RequestHeader String token,@PathVariable("datasetId") String datasetId, Integer pageNum, Integer pageSize, String keyword, Boolean hasCaption) {
        return null;
    }
}

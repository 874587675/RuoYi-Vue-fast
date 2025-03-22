package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.service.KnowledgeService;
import com.ruoyi.project.business.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("修改知识库文件名称")
    public AjaxResult updateDocument(@RequestHeader String token,@RequestBody UpdateDocument updateDocument) throws IOException {
        return knowledgeService.updateDocument(token,updateDocument);
    }

    /**
     * 创建知识库文件
     */
    @ApiOperation("创建知识库文件")
    @PostMapping("/createDocument")
    public AjaxResult createDocument(@RequestHeader String token,@RequestBody CreateDocument createDocument) throws IOException {
        return knowledgeService.createDocument(token,createDocument);
    }

    /**
     * 删除知识库文件
     */
    @ApiOperation("删除知识库文件")
    @PostMapping("/deleteDocument")
    public AjaxResult deleteDocument(@RequestHeader String token,@RequestBody DeleteDocuments deleteDocuments) throws IOException {
        return knowledgeService.deleteDocument(token,deleteDocuments);
    }

    /**
     * 查看知识库文件列表
     */
    @ApiOperation("查看知识库文件列表")
    @PostMapping("/listDocument")
    public AjaxResult listDocument(@RequestHeader String token,@RequestBody ListDocument listDocument) throws IOException {
        return knowledgeService.listDocument(token, listDocument);
    }

    /**
     * 创建知识库
     */
    @ApiOperation("创建知识库")
    @PostMapping("/createKnowledge")
    public AjaxResult createKnowledge(@RequestHeader String token,@RequestBody CreateKnowledge createKnowledge) throws IOException {
        return knowledgeService.createKnowledge(token,createKnowledge);
    }

    /**
     * 查看知识库列表
     */
    @ApiOperation("查看知识库列表")
    @GetMapping("/listKnowledge")
    public AjaxResult listKnowledge(@RequestHeader String token, String spaceId, String name,Integer formatType ,Integer pageNum, Integer pageSize) throws IOException {
        return knowledgeService.listKnowledge(token,spaceId,name,formatType,pageNum,pageSize);
    }

    /**
     * 修改知识库信息
     */
    @ApiOperation("修改知识库信息")
    @PutMapping("/updateKnowledge/{datasetId}")
    public AjaxResult updateKnowledge(@RequestHeader String token,@PathVariable("datasetId") String datasetId,@RequestBody UpdateKnowledge updateKnowledge) throws IOException {
        return knowledgeService.updateKnowledge(token,datasetId,updateKnowledge);
    }

    /**
     * 删除知识库
     */
    @ApiOperation("删除知识库")
    @DeleteMapping("/deleteKnowledge/{datasetId}")
    public AjaxResult deleteKnowledge(@RequestHeader String token, @PathVariable("datasetId") String datasetId) throws IOException {
        return knowledgeService.deleteKnowledge(token,datasetId);
    }

    /**
     * 查看知识库文件上传进度
     */
    @ApiOperation("查看知识库文件上传进度")
    @PostMapping("/readDocumentProgress/{datasetId}")
    public AjaxResult readDocumentProgress(@RequestHeader String token,@PathVariable("datasetId") String datasetId,@RequestBody ReadDocumentProgress readDocumentProgress) throws IOException {
        return knowledgeService.readDocumentProgress(token,datasetId,readDocumentProgress);
    }

    /**
     * 查看知识库图片列表
     */
    @ApiOperation("查看知识库图片列表")
    @PostMapping("/listPhoto/{datasetId}")
    public AjaxResult listPhoto(@RequestHeader String token,@PathVariable("datasetId") String datasetId, Integer pageNum, Integer pageSize, String keyword, Boolean hasCaption) throws IOException {
        return knowledgeService.listPhoto(token,datasetId,pageNum,pageSize,keyword,hasCaption);
    }
}

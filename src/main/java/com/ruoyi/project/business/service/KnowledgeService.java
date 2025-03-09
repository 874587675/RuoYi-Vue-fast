package com.ruoyi.project.business.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.vo.*;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;

public interface KnowledgeService {
    /**
     * 修改知识库文件名称
     */
    AjaxResult updateDocument(UpdateDocument updateDocument) throws IOException;

    /**
     * 创建知识库文件
     */
    AjaxResult createDocument(String token, CreateDocument createDocument);

    /**
     * 删除知识库文件
     */
    AjaxResult deleteDocument(String token, DeleteDocuments deleteDocuments);

    /**
     *查看知识库文件列表
     */
    AjaxResult listDocument(String token, ListDocument listDocument);

    /**
     * 创建知识库
     */
    AjaxResult createKnowledge(String token, CreateKnowledge createKnowledge);

    /**
     * 查看知识库列表
     */
    AjaxResult listKnowledge(String token,String spaceId,String name, String pageNum,String pageSize) throws IOException;

    /**
     * 修改知识库信息
     */
    AjaxResult updateKnowledge(String token,String datasetId,UpdateKnowledge updateKnowledge);

    /**
     * 删除知识库
     */
    AjaxResult deleteKnowledge(String token,String datasetId);

    /**
     * 查看知识库文件上传进度
     */
    AjaxResult readDocumentProgress(String token,ReadDocumentProgress readDocumentProgress);

    /**
     * 查看知识库图片列表
     */
    AjaxResult listPhoto(String token,String datasetId,Integer pageNum,Integer pageSize,String keyword,Boolean hasCaption);
}

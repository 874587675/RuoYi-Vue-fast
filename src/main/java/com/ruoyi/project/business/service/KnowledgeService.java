package com.ruoyi.project.business.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.vo.*;

import java.io.IOException;

public interface KnowledgeService {
    /**
     * 修改知识库文件名称
     */
    AjaxResult updateDocument(String token,UpdateDocument updateDocument) throws IOException;

    /**
     * 创建知识库文件
     */
    AjaxResult createDocument(String token, CreateDocument createDocument) throws IOException;

    /**
     * 删除知识库文件
     */
    AjaxResult deleteDocument(String token, DeleteDocuments deleteDocuments) throws IOException;

    /**
     *查看知识库文件列表
     */
    AjaxResult listDocument(String token, ListDocument listDocument) throws IOException;

    /**
     * 创建知识库
     */
    AjaxResult createKnowledge(String token, CreateKnowledge createKnowledge) throws IOException;

    /**
     * 查看知识库列表
     */
    AjaxResult listKnowledge(String token,String spaceId,String name, Integer formatType ,Integer pageNum, Integer pageSize) throws IOException;

    /**
     * 修改知识库信息
     */
    AjaxResult updateKnowledge(String token,String datasetId,UpdateKnowledge updateKnowledge) throws IOException;

    /**
     * 删除知识库
     */
    AjaxResult deleteKnowledge(String token,String datasetId) throws IOException;

    /**
     * 查看知识库文件上传进度
     */
    AjaxResult readDocumentProgress(String token,String datasetId,ReadDocumentProgress readDocumentProgress) throws IOException;

    /**
     * 查看知识库图片列表
     */
    AjaxResult listPhoto(String token,String datasetId,Integer pageNum,Integer pageSize,String keyword,Boolean hasCaption) throws IOException;
}

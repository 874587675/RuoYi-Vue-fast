package com.ruoyi.project.business.constant;

public class KnowledgeConstant {
    /**
     * 修改知识库文件(POST)
     */
    public static final String UPDATE_DOCUMENT = "https://api.coze.cn/open_api/knowledge/document/update";
    /**
     * 创建知识库文件(POST)
     */
    public static final String CREATE_DOCUMENT = "https://api.coze.cn/open_api/knowledge/document/create";
    /**
     * 删除知识库文件(POST)
     */
    public static final String DELETE_DOCUMENT = "https://api.coze.cn/open_api/knowledge/document/delete";
    /**
     *查看知识库文件列表(POST)
     */
    public static final String LIST_DOCUMENT = "https://api.coze.cn/open_api/knowledge/document/list";
    /**
     * 创建和查看知识库((POST)和(GET))
     */
    public static final String CREATE_LIST_KNOWLEDGE = "https://api.coze.cn/v1/datasets?";

    /**
     *  修改和删除知识库信息((PUT)和(DELETE))
     */
    public static final String UPDATE_DELETE_KNOWLEDGE = "https://api.coze.cn/v1/datasets/";

    /**
     * 查看知识库文件上传进度(POST)
     */
    public static final String READ_DOCUMENT_PROGRESS = "https://api.coze.cn/v1/datasets/";

    /**
     * 查看知识库图片列表(GET)
     */
    public static final String LIST_PHOTO = "https://api.coze.cn/v1/datasets/{dataset_id}/images";
}

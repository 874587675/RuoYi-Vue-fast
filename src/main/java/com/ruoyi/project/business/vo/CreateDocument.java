package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDocument implements Serializable {

    @ApiModelProperty(value = "知识库 ID。\n" +
            "在扣子平台中打开指定知识库页面，页面 URL 中 knowledge 参数后的数字就是知识库 ID。\n" +
            "例如 https://www.coze.cn/space/736142423532160****/knowledge/738509371792341****，\n" +
            "知识库 ID 为 738509371792341****。")
    private String datasetId;
    @ApiModelProperty(value = "待上传文件的元数据信息。数组最大长度为 10，即每次最多上传 10 个文件。\n" +
            "支持的上传方式如下：\n" +
            "文本知识库：\n" +
            "通过 Base64 上传本地文件。\n" +
            "上传在线网页。\n" +
            "图片知识库：通过上传文件 API 获取的 file_id 上传图片。")
    private DocumentBases documentBases;
    @ApiModelProperty(value = "分段设置")
    private ChunkStrategy chunkStrategy;

}

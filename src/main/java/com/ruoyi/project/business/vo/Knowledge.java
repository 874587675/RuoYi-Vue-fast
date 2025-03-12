package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Knowledge {

    @ApiModelProperty(value ="是否自动调用知识库。取值包括：\n" +
            "true：（默认）自动调用。每一轮对话都会调用知识库，使用召回的内容辅助生成回复。\n" +
            "false：按需调用。根据实际需要来调用知识库，使用召回内容辅助生成回复。此时，需要在左侧的人设与回复逻辑区域明确写清楚在什么情况下调用哪个知识库进行回复。")
    private Boolean auto_call;

    @ApiModelProperty(value ="智能体绑定的知识库 ID。\n" +
            "在扣子平台中打开指定知识库页面，页面 URL 中 knowledge 参数后的数字就是知识库 ID。\n" +
            "例如 https://bots.bytedance.net/space/736142423532160****/knowledge/738509371792341****，知识库 ID 为 738509371792341****。")
    private List<String> dataset_ids;

    @ApiModelProperty(value ="知识库搜索策略。取值包括：\n" +
            "0:（默认）语义搜索。像人类一样去理解词与词，句与句之间的关系。\n" +
            "1: 混合搜索。结合全文检索和语义检索的优势，并对结果进行综合排序召回相关的内容片段。\n" +
            "20: 全文搜索。基于关键词进行全文检索。")
    private Integer search_strategy;
}


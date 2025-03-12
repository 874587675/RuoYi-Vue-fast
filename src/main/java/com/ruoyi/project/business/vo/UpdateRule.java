package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRule {
    @ApiModelProperty(value = "在线网页自动更新的频率。单位为小时，最小值为 24")
    private Integer update_interval;

    @ApiModelProperty(value = "在线网页是否自动更新。默认不自动更新。取值包括：\n" +
            "0: 不自动更新\n" +
            "1: 自动覆盖更新\n" +
            "2: 自动追加更新")
    private Integer update_type;
}

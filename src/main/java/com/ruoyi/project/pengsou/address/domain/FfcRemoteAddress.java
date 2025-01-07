package com.ruoyi.project.pengsou.address.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

@Data
public class FfcRemoteAddress extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    // 快递公司
    private String express;

    // 国家
    private String country;

    // 公司代码
    private String companyCode;

    // 邮编下限
    private String postalCodeLow;

    // 邮编上限
    private String postalCodeHigh;

    // 邮编
    private String postalCode;

    // 城市
    private String city;

    // 起始附加费
    private String originSurcharge;

    // 目的附加费
    private String destinationSurcharge;

    // 搜索内容
    @JsonIgnore
    private String searchContent;


}

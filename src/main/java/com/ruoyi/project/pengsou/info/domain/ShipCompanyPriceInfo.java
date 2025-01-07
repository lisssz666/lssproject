package com.ruoyi.project.pengsou.info.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ShipCompanyPriceInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long pId;

    private String companyCode;

    private String postcodes;

    private String effectiveness;

    private List<LogisticsCompanyInfo> haikaCompany;

    private List<LogisticsCompanyInfo> haipaiCompany;

    private List<LogisticsCompanyInfo> airliftCompany;

    private List<LogisticsCompanyInfo> railwayCompany;

    @JSONField(serialize = false)
    private String whichCompany;

    @JSONField(serialize = false)
    private String shipCompanyName;

    @JSONField(serialize = false)
    private BigDecimal volumePrice;

    @JSONField(serialize = false)
    private BigDecimal weightPrice;

}


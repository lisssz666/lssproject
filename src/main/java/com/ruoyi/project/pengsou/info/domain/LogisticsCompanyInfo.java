package com.ruoyi.project.pengsou.info.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LogisticsCompanyInfo {
    private String shipCompanyName;

    private BigDecimal volumePrice;

    private BigDecimal weightPrice;

    private String effectiveness;
}


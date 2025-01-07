package com.ruoyi.project.pengsou.fba.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.pengsou.info.domain.ShipCompanyPriceInfo;
import lombok.Data;

import java.io.Serializable;

@Data
public class FfcFbaWarehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // 公司名称
    private String company;

    // 公司代码
    private String companyCode;

    // 国家
    private String country;

    // 省份
    private String province;

    // 城市
    private String city;

    // 地址
    private String address;

    // 模块类型
    private String moduleType;

    // 邮编
    private String postalCode;

    // 远程标识
    private String remote;

    // 船运公司价格信息
    private ShipCompanyPriceInfo shipPriceInfo;

    @JsonIgnore
    private Long userId;

    // 搜索内容（用于搜索功能）
    private String searchContent;
}
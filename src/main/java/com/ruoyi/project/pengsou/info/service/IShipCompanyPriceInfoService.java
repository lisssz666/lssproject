package com.ruoyi.project.pengsou.info.service;

import com.ruoyi.project.pengsou.info.domain.ShipCompanyPriceInfo;

import java.util.List;

public interface IShipCompanyPriceInfoService {
    ShipCompanyPriceInfo selectShipCompanyPriceInfoById(Long paramLong);

    List<ShipCompanyPriceInfo> selectShipCompanyPriceInfoList(ShipCompanyPriceInfo paramShipCompanyPriceInfo);

    int insertShipCompanyPriceInfo(ShipCompanyPriceInfo paramShipCompanyPriceInfo);

    int updateShipCompanyPriceInfo(ShipCompanyPriceInfo paramShipCompanyPriceInfo);

    int deleteShipCompanyPriceInfoByIds(Long[] paramArrayOfLong);

    int deleteShipCompanyPriceInfoById(Long paramLong);
}


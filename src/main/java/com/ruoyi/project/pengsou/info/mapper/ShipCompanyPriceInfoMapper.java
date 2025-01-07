package com.ruoyi.project.pengsou.info.mapper;

import com.ruoyi.project.pengsou.info.domain.ShipCompanyPriceInfo;

import java.util.List;

public interface ShipCompanyPriceInfoMapper {
    ShipCompanyPriceInfo selectShipCompanyPriceInfoById(Long paramLong);

    List<ShipCompanyPriceInfo> selectShipCompanyPriceInfoList(ShipCompanyPriceInfo paramShipCompanyPriceInfo);

    int insertShipCompanyPriceInfo(ShipCompanyPriceInfo paramShipCompanyPriceInfo);

    int updateShipCompanyPriceInfo(ShipCompanyPriceInfo paramShipCompanyPriceInfo);

    int deleteShipCompanyPriceInfoById(Long paramLong);

    int deleteShipCompanyPriceInfoByIds(Long[] paramArrayOfLong);

    List<ShipCompanyPriceInfo> selectShipCompanyPriceInfoByPId(Long paramLong);
}

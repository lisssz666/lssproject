package com.ruoyi.project.pengsou.ships.mapper;

import com.ruoyi.project.pengsou.ships.domain.FfcShipCompany;
import java.util.List;

public interface FfcShipCompanyMapper {
    List<FfcShipCompany> selectFfcShipCompanyList(FfcShipCompany paramFfcShipCompany);

    int insertFfcShipCompany(FfcShipCompany paramFfcShipCompany);

    int deleteFfcShipCompanyById(Long paramLong);

    int deleteFfcShipCompanyByIds(Long[] paramArrayOfLong);
}

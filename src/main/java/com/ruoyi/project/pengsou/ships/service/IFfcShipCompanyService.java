package com.ruoyi.project.pengsou.ships.service;

import com.ruoyi.project.pengsou.ships.domain.FfcShipCompany;
import java.util.List;

public interface IFfcShipCompanyService {
    List<FfcShipCompany> selectFfcShipCompanyList(FfcShipCompany paramFfcShipCompany);

    int insertFfcShipCompany(FfcShipCompany paramFfcShipCompany);

    int deleteFfcShipCompanyByIds(Long[] paramArrayOfLong);

    int deleteFfcShipCompanyById(Long paramLong);
}
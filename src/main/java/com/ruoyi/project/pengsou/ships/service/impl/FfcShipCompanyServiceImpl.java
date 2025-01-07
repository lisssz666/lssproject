package com.ruoyi.project.pengsou.ships.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.pengsou.ships.domain.FfcShipCompany;
import com.ruoyi.project.pengsou.ships.mapper.FfcShipCompanyMapper;
import com.ruoyi.project.pengsou.ships.service.IFfcShipCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FfcShipCompanyServiceImpl implements IFfcShipCompanyService {
    @Autowired
    private FfcShipCompanyMapper ffcShipCompanyMapper;

    public List<FfcShipCompany> selectFfcShipCompanyList(FfcShipCompany ffcShipCompany) {
        return this.ffcShipCompanyMapper.selectFfcShipCompanyList(ffcShipCompany);
    }

    public int insertFfcShipCompany(FfcShipCompany ffcShipCompany) {
        ffcShipCompany.setCreateTime(DateUtils.getNowDate());
        return this.ffcShipCompanyMapper.insertFfcShipCompany(ffcShipCompany);
    }

    public int deleteFfcShipCompanyByIds(Long[] ids) {
        return this.ffcShipCompanyMapper.deleteFfcShipCompanyByIds(ids);
    }

    public int deleteFfcShipCompanyById(Long id) {
        return this.ffcShipCompanyMapper.deleteFfcShipCompanyById(id);
    }
}


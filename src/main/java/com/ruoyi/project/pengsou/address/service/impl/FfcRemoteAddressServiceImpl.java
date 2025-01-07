package com.ruoyi.project.pengsou.address.service.impl;

import com.ruoyi.common.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.pengsou.address.domain.FfcRemoteAddress;
import com.ruoyi.project.pengsou.address.mapper.FfcRemoteAddressMapper;
import com.ruoyi.project.pengsou.address.service.IFfcRemoteAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FfcRemoteAddressServiceImpl implements IFfcRemoteAddressService {
    @Autowired
    private FfcRemoteAddressMapper ffcRemoteAddressMapper;

    public FfcRemoteAddress selectFfcRemoteAddressById(Long id) {
        return this.ffcRemoteAddressMapper.selectFfcRemoteAddressById(id);
    }

    @Override
    public List<FfcRemoteAddress> selectFfcRemoteAddressList(FfcRemoteAddress paramFfcRemoteAddress) {
        return null;
    }


    public Map<String,String> selectIfRemote(FfcRemoteAddress ffcRemoteAddress) {
        Map<String, String> map = new HashMap<>();
        String[] expresses = {"UPS", "FedEx", "DHL", "TNT"}; //4个固定的快递公司
        for (String express : expresses) {
            ffcRemoteAddress.setExpress(express);
            Integer res = ffcRemoteAddressMapper.selectIfRemote(ffcRemoteAddress);
            map.put(express, (res != null && res == 1) ? "偏远" : "非偏远");
        }
        return map;
    }

    public Long insertFfcRemoteAddress(FfcRemoteAddress ffcRemoteAddress) {
        ffcRemoteAddress.setCreateTime(DateUtils.getNowDate());
        return this.ffcRemoteAddressMapper.insertFfcRemoteAddress(ffcRemoteAddress);
    }

    public int updateFfcRemoteAddress(FfcRemoteAddress ffcRemoteAddress) {
        return this.ffcRemoteAddressMapper.updateFfcRemoteAddress(ffcRemoteAddress);
    }

    public int deleteFfcRemoteAddressByIds(Long[] ids) {
        return this.ffcRemoteAddressMapper.deleteFfcRemoteAddressByIds(ids);
    }

    public int deleteFfcRemoteAddressById(Long id) {
        return this.ffcRemoteAddressMapper.deleteFfcRemoteAddressById(id);
    }
}

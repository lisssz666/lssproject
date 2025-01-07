package com.ruoyi.project.pengsou.address.service;
import com.ruoyi.project.pengsou.address.domain.FfcRemoteAddress;
import java.util.List;
import java.util.Map;

public interface IFfcRemoteAddressService {
    FfcRemoteAddress selectFfcRemoteAddressById(Long paramLong);

    List<FfcRemoteAddress> selectFfcRemoteAddressList(FfcRemoteAddress paramFfcRemoteAddress);

    //查询快递公司是否偏远列表
    Map<String,String> selectIfRemote(FfcRemoteAddress paramFfcRemoteAddress);

    Long insertFfcRemoteAddress(FfcRemoteAddress paramFfcRemoteAddress);

    int updateFfcRemoteAddress(FfcRemoteAddress paramFfcRemoteAddress);

    int deleteFfcRemoteAddressByIds(Long[] paramArrayOfLong);

    int deleteFfcRemoteAddressById(Long paramLong);
}

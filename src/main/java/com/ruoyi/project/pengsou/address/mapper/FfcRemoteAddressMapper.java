package com.ruoyi.project.pengsou.address.mapper;


import com.ruoyi.project.pengsou.address.domain.FfcRemoteAddress;

import java.util.List;

public interface FfcRemoteAddressMapper {
    FfcRemoteAddress selectFfcRemoteAddressById(Long paramLong);

    List<FfcRemoteAddress> selectFfcRemoteAddressList(FfcRemoteAddress paramFfcRemoteAddress);

    Long insertFfcRemoteAddress(FfcRemoteAddress paramFfcRemoteAddress);

    int updateFfcRemoteAddress(FfcRemoteAddress paramFfcRemoteAddress);

    int deleteFfcRemoteAddressById(Long paramLong);

    Integer deleteFfcRemoteAddressByIds(Long[] paramArrayOfLong);

    Integer selectIfRemote(FfcRemoteAddress ffcRemoteAddress);
}

package com.ruoyi.project.pengsou.fba.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.pengsou.fba.domain.FfcFbaWarehouse;
import org.springframework.web.multipart.MultipartFile;

public interface IFfcFbaWarehouseService {
    FfcFbaWarehouse selectFfcFbaWarehouseById(Long paramLong);

    List<FfcFbaWarehouse> selectFfcFbaWarehouseList(FfcFbaWarehouse paramFfcFbaWarehouse);

    int insertFfcFbaWarehouse(FfcFbaWarehouse ffcFbaWarehouse);

    int updateFfcFbaWarehouse(FfcFbaWarehouse paramFfcFbaWarehouse);

    int deleteFfcFbaWarehouseByIds(Long[] paramArrayOfLong);

    int deleteFfcFbaWarehouseById(Long paramLong);

    int crawlData(String paramString);

    String uploadImage(String paramString1, String paramString2, MultipartFile paramMultipartFile) throws IOException;

    Long calVipDayRemaining(Long paramLong);
}

package com.ruoyi.project.pengsou.fba.mapper;
import com.ruoyi.project.pengsou.fba.domain.FfcFbaWarehouse;
import java.util.List;

public interface FfcFbaWarehouseMapper {
    FfcFbaWarehouse selectFfcFbaWarehouseById(Long paramLong);

    List<FfcFbaWarehouse> selectFfcFbaWarehouseList(FfcFbaWarehouse paramFfcFbaWarehouse);

    int insertFfcFbaWarehouse(FfcFbaWarehouse paramFfcFbaWarehouse);

    int updateFfcFbaWarehouse(FfcFbaWarehouse paramFfcFbaWarehouse);

    int deleteFfcFbaWarehouseById(Long paramLong);

    int deleteFfcFbaWarehouseByIds(Long[] paramArrayOfLong);

    void insertImageInfo(String paramString1, String paramString2, String paramString3);

    List<FfcFbaWarehouse> selectFbaInfoByPost(String paramString);
}

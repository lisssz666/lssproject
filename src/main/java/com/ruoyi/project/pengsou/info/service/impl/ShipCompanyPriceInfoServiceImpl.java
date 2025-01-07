package com.ruoyi.project.pengsou.info.service.impl;

import com.ruoyi.common.enums.ShipCompanyType;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.ruoyi.project.pengsou.fba.domain.FfcFbaWarehouse;
import com.ruoyi.project.pengsou.fba.mapper.FfcFbaWarehouseMapper;
import com.ruoyi.project.pengsou.info.domain.LogisticsCompanyInfo;
import com.ruoyi.project.pengsou.info.domain.ShipCompanyPriceInfo;
import com.ruoyi.project.pengsou.info.mapper.ShipCompanyPriceInfoMapper;
import com.ruoyi.project.pengsou.info.service.IShipCompanyPriceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipCompanyPriceInfoServiceImpl implements IShipCompanyPriceInfoService {
    private static final Logger log = LoggerFactory.getLogger(ShipCompanyPriceInfoServiceImpl.class);

    @Autowired
    private ShipCompanyPriceInfoMapper shipCompanyPriceInfoMapper;

    @Autowired
    private FfcFbaWarehouseMapper ffcFbaWarehouseMapper;

    public ShipCompanyPriceInfo selectShipCompanyPriceInfoById(Long id) {
        return this.shipCompanyPriceInfoMapper.selectShipCompanyPriceInfoById(id);
    }

    public List<ShipCompanyPriceInfo> selectShipCompanyPriceInfoList(ShipCompanyPriceInfo shipCompanyPriceInfo) {
        return this.shipCompanyPriceInfoMapper.selectShipCompanyPriceInfoList(shipCompanyPriceInfo);
    }

    public int insertShipCompanyPriceInfo(ShipCompanyPriceInfo shipCompanyPriceInfo) {
        log.info("===================+++++++");
        log.info("shipCompanyPriceInfoBean====:" + shipCompanyPriceInfo);
        shipCompanyPriceInfo.setCreateTime(DateUtils.getNowDate());
        int i = 0;
        String code = shipCompanyPriceInfo.getCompanyCode();
        String postCodes = shipCompanyPriceInfo.getPostcodes();
        log.info("code ==:" + code + "   postCodes ==:" + postCodes);
        if (!StringUtils.isEmpty(code) && !StringUtils.isEmpty(postCodes))
            new BaseException("");
                    Long id = null;
        FfcFbaWarehouse fbaWarehouse = new FfcFbaWarehouse();
        if (!StringUtils.isEmpty(code)) {
            fbaWarehouse.setCompanyCode(code.trim());
            List<FfcFbaWarehouse> ffcList = this.ffcFbaWarehouseMapper.selectFfcFbaWarehouseList(fbaWarehouse);
            if (ffcList.size() == 0)
                new BaseException("");
            for (FfcFbaWarehouse ffc : ffcList)
                id = ffc.getId();
            i = insertPriceInfo(shipCompanyPriceInfo, id);
        } else if (!StringUtils.isEmpty(postCodes)) {
            for (int j = 0; j < postCodes.length(); j++) {
                char c = postCodes.charAt(j);
                String poseFirst = String.valueOf(c);
                List<FfcFbaWarehouse> ffcPoseFirstList = this.ffcFbaWarehouseMapper.selectFbaInfoByPost(poseFirst);
                for (FfcFbaWarehouse ffcPoseFirst : ffcPoseFirstList) {
                    id = ffcPoseFirst.getId();
                    i = insertPriceInfo(shipCompanyPriceInfo, id);
                }
            }
        }
        return i;
    }

    public int updateShipCompanyPriceInfo(ShipCompanyPriceInfo shipCompanyPriceInfo) {
        shipCompanyPriceInfo.setUpdateTime(DateUtils.getNowDate());
        return this.shipCompanyPriceInfoMapper.updateShipCompanyPriceInfo(shipCompanyPriceInfo);
    }

    public int deleteShipCompanyPriceInfoByIds(Long[] ids) {
        return this.shipCompanyPriceInfoMapper.deleteShipCompanyPriceInfoByIds(ids);
    }

    public int deleteShipCompanyPriceInfoById(Long id) {
        return this.shipCompanyPriceInfoMapper.deleteShipCompanyPriceInfoById(id);
    }

    private int insertPriceInfo(ShipCompanyPriceInfo shipCompanyPriceInfo, Long id) {
        int i = 0;
        ShipCompanyPriceInfo newShipCompanyPriceInfo = new ShipCompanyPriceInfo();
        newShipCompanyPriceInfo.setPId(id);
        List<ShipCompanyPriceInfo> shipCompanyPriceInfoList = this.shipCompanyPriceInfoMapper.selectShipCompanyPriceInfoList(newShipCompanyPriceInfo);
        HashMap<String, Long> typeMap = new HashMap<>();
        for (ShipCompanyPriceInfo scp : shipCompanyPriceInfoList)
            typeMap.put(scp.getWhichCompany() + scp.getShipCompanyName(), scp.getId());
        List<LogisticsCompanyInfo> haikaCompanyInfo = shipCompanyPriceInfo.getHaikaCompany();
        if (haikaCompanyInfo != null)
            for (LogisticsCompanyInfo logisCompany : haikaCompanyInfo) {
                String shipCompanyName = logisCompany.getShipCompanyName();
                if (StringUtils.isEmpty(shipCompanyName))
                    continue;
                Set<String> set = typeMap.keySet();
                if (set.contains(ShipCompanyType.HAIKA.getCode() + shipCompanyName))
                    this.shipCompanyPriceInfoMapper.deleteShipCompanyPriceInfoById(typeMap.get(ShipCompanyType.HAIKA.getCode() + shipCompanyName));
                String effectiveness = logisCompany.getEffectiveness();
                BigDecimal volumePrice = logisCompany.getVolumePrice();
                BigDecimal weightPrice = logisCompany.getWeightPrice();
                shipCompanyPriceInfo.setPId(id);
                shipCompanyPriceInfo.setWhichCompany(ShipCompanyType.HAIKA.getCode());
                shipCompanyPriceInfo.setShipCompanyName(shipCompanyName);
                shipCompanyPriceInfo.setEffectiveness(effectiveness);
                shipCompanyPriceInfo.setVolumePrice(volumePrice);
                shipCompanyPriceInfo.setWeightPrice(weightPrice);
                i = this.shipCompanyPriceInfoMapper.insertShipCompanyPriceInfo(shipCompanyPriceInfo);
            }
        List<LogisticsCompanyInfo> haipaiCompanyInfo = shipCompanyPriceInfo.getHaipaiCompany();
        if (haipaiCompanyInfo != null)
            for (LogisticsCompanyInfo logisCompany : haipaiCompanyInfo) {
                String shipCompanyName = logisCompany.getShipCompanyName();
                if (StringUtils.isEmpty(shipCompanyName))
                    continue;
                Set<String> set = typeMap.keySet();
                if (set.contains(ShipCompanyType.HAIPAI.getCode() + shipCompanyName))
                    this.shipCompanyPriceInfoMapper.deleteShipCompanyPriceInfoById(typeMap.get(ShipCompanyType.HAIPAI.getCode() + shipCompanyName));
                String effectiveness = logisCompany.getEffectiveness();
                BigDecimal volumePrice = logisCompany.getVolumePrice();
                BigDecimal weightPrice = logisCompany.getWeightPrice();
                shipCompanyPriceInfo.setPId(id);
                shipCompanyPriceInfo.setWhichCompany(ShipCompanyType.HAIPAI.getCode());
                shipCompanyPriceInfo.setShipCompanyName(shipCompanyName);
                shipCompanyPriceInfo.setEffectiveness(effectiveness);
                shipCompanyPriceInfo.setVolumePrice(volumePrice);
                shipCompanyPriceInfo.setWeightPrice(weightPrice);
                i = this.shipCompanyPriceInfoMapper.insertShipCompanyPriceInfo(shipCompanyPriceInfo);
            }
        List<LogisticsCompanyInfo> airliftCompanyInfo = shipCompanyPriceInfo.getAirliftCompany();
        if (airliftCompanyInfo != null)
            for (LogisticsCompanyInfo logisCompany : airliftCompanyInfo) {
                String shipCompanyName = logisCompany.getShipCompanyName();
                if (StringUtils.isEmpty(shipCompanyName))
                    continue;
                Set<String> set = typeMap.keySet();
                if (set.contains(ShipCompanyType.AIRLIFT.getCode() + shipCompanyName))
                    this.shipCompanyPriceInfoMapper.deleteShipCompanyPriceInfoById(typeMap.get(ShipCompanyType.AIRLIFT.getCode() + shipCompanyName));
                String effectiveness = logisCompany.getEffectiveness();
                BigDecimal volumePrice = logisCompany.getVolumePrice();
                BigDecimal weightPrice = logisCompany.getWeightPrice();
                shipCompanyPriceInfo.setPId(id);
                shipCompanyPriceInfo.setWhichCompany(ShipCompanyType.AIRLIFT.getCode());
                shipCompanyPriceInfo.setShipCompanyName(shipCompanyName);
                shipCompanyPriceInfo.setEffectiveness(effectiveness);
                shipCompanyPriceInfo.setVolumePrice(volumePrice);
                shipCompanyPriceInfo.setWeightPrice(weightPrice);
                i = this.shipCompanyPriceInfoMapper.insertShipCompanyPriceInfo(shipCompanyPriceInfo);
            }
        List<LogisticsCompanyInfo> railwayCompanyInfo = shipCompanyPriceInfo.getRailwayCompany();
        if (railwayCompanyInfo != null)
            for (LogisticsCompanyInfo logisCompany : railwayCompanyInfo) {
                String shipCompanyName = logisCompany.getShipCompanyName();
                if (StringUtils.isEmpty(shipCompanyName))
                    continue;
                Set<String> set = typeMap.keySet();
                if (set.contains(ShipCompanyType.RAILWAY.getCode() + shipCompanyName))
                    this.shipCompanyPriceInfoMapper.deleteShipCompanyPriceInfoById(typeMap.get(ShipCompanyType.RAILWAY.getCode() + shipCompanyName));
                String effectiveness = logisCompany.getEffectiveness();
                BigDecimal volumePrice = logisCompany.getVolumePrice();
                BigDecimal weightPrice = logisCompany.getWeightPrice();
                shipCompanyPriceInfo.setPId(id);
                shipCompanyPriceInfo.setWhichCompany(ShipCompanyType.RAILWAY.getCode());
                shipCompanyPriceInfo.setShipCompanyName(shipCompanyName);
                shipCompanyPriceInfo.setEffectiveness(effectiveness);
                shipCompanyPriceInfo.setVolumePrice(volumePrice);
                shipCompanyPriceInfo.setWeightPrice(weightPrice);
                i = this.shipCompanyPriceInfoMapper.insertShipCompanyPriceInfo(shipCompanyPriceInfo);
            }
        return i;
    }
}

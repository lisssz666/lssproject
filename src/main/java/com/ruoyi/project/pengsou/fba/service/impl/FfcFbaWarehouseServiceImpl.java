package com.ruoyi.project.pengsou.fba.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.project.pengsou.fba.domain.FfcFbaWarehouse;
import com.ruoyi.project.pengsou.fba.mapper.FfcFbaWarehouseMapper;
import com.ruoyi.project.pengsou.fba.service.IFfcFbaWarehouseService;
import com.ruoyi.project.pengsou.info.domain.LogisticsCompanyInfo;
import com.ruoyi.project.pengsou.info.domain.ShipCompanyPriceInfo;
import com.ruoyi.project.pengsou.info.mapper.ShipCompanyPriceInfoMapper;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.mapper.SysUserMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FfcFbaWarehouseServiceImpl implements IFfcFbaWarehouseService {
    @Value("${spring.upload.path}")
    private String uploadPath;

    @Value("${spring.upload.server}")
    private String serverPath;

    @Value("${common.vipDayRemaining}")
    private Long vipDayRemaining;

    @Autowired
    private FfcFbaWarehouseMapper ffcFbaWarehouseMapper;

    @Autowired
    private ShipCompanyPriceInfoMapper shipCompanyPriceInfoMapper;

    @Autowired
    private SysUserMapper userMapper;

    public FfcFbaWarehouse selectFfcFbaWarehouseById(Long id) {
        return this.ffcFbaWarehouseMapper.selectFfcFbaWarehouseById(id);
    }

    public List<FfcFbaWarehouse> selectFfcFbaWarehouseList(FfcFbaWarehouse ffcFbaWarehouse) {
        List<FfcFbaWarehouse> ffcList = this.ffcFbaWarehouseMapper.selectFfcFbaWarehouseList(ffcFbaWarehouse);
        ShipCompanyPriceInfo sc = new ShipCompanyPriceInfo();
        for (FfcFbaWarehouse ffc : ffcList) {
            Long id = ffc.getId();
            sc.setPId(id);
            List<ShipCompanyPriceInfo> shipCompanyPriceInfos = this.shipCompanyPriceInfoMapper.selectShipCompanyPriceInfoList(sc);
            if (shipCompanyPriceInfos.size() == 0)
                continue;
            String company0 = null;
            String company1 = null;
            String company2 = null;
            String company3 = null;
            IdentityHashMap<String, List<LogisticsCompanyInfo>> map = new IdentityHashMap<>();
            List<LogisticsCompanyInfo> logisList0 = new ArrayList<>();
            List<LogisticsCompanyInfo> logisList1 = new ArrayList<>();
            List<LogisticsCompanyInfo> logisList2 = new ArrayList<>();
            List<LogisticsCompanyInfo> logisList3 = new ArrayList<>();
            for (ShipCompanyPriceInfo shipci : shipCompanyPriceInfos) {
                LogisticsCompanyInfo lc = new LogisticsCompanyInfo();
                String company = shipci.getWhichCompany();
                lc.setShipCompanyName(shipci.getShipCompanyName());
                lc.setVolumePrice(shipci.getVolumePrice());
                lc.setWeightPrice(shipci.getWeightPrice());
                lc.setEffectiveness(shipci.getEffectiveness());
                if ("0".equals(company)) {
                    company0 = company;
                    logisList0.add(lc);
                    continue;
                }
                if ("1".equals(company)) {
                    company1 = company;
                    logisList1.add(lc);
                    continue;
                }
                if ("2".equals(company)) {
                    company2 = company;
                    logisList2.add(lc);
                    continue;
                }
                if ("3".equals(company)) {
                    company3 = company;
                    logisList3.add(lc);
                }
            }
            map.put(company0, logisList0);
            map.put(company1, logisList1);
            map.put(company2, logisList2);
            map.put(company3, logisList3);
            ShipCompanyPriceInfo newShipCompanyPriceInfos = new ShipCompanyPriceInfo();
            for (String key : map.keySet()) {
                if ("0".equals(key)) {
                    newShipCompanyPriceInfos.setHaikaCompany(map.get(key));
                    continue;
                }
                if ("1".equals(key)) {
                    newShipCompanyPriceInfos.setHaipaiCompany(map.get(key));
                    continue;
                }
                if ("2".equals(key)) {
                    newShipCompanyPriceInfos.setAirliftCompany(map.get(key));
                    continue;
                }
                if ("3".equals(key))
                    newShipCompanyPriceInfos.setRailwayCompany(map.get(key));
            }
            ffc.setShipPriceInfo(newShipCompanyPriceInfos);
        }
        return ffcList;
    }

    public int insertFfcFbaWarehouse(FfcFbaWarehouse ffcFbaWarehouse) {
//        FfcFbaWarehouse ffcFbaWarehouse = new FfcFbaWarehouse();
//        ffcFbaWarehouse.setCompanyCode(String.valueOf(params.get("companyCode")));
//        ffcFbaWarehouse.setCompany(String.valueOf(params.get("company")));
//        ffcFbaWarehouse.setModuleType(String.valueOf(params.get("moduleType")));
//        ffcFbaWarehouse.setRemote(String.valueOf(params.get("remote")));
//        ffcFbaWarehouse.setAddress(String.valueOf(params.get("address")));
//        ffcFbaWarehouse.setCity(String.valueOf(params.get("city")));
//        ffcFbaWarehouse.setProvince(String.valueOf(params.get("province")));
//        ffcFbaWarehouse.setCountry(String.valueOf(params.get("country")));
        return this.ffcFbaWarehouseMapper.insertFfcFbaWarehouse(ffcFbaWarehouse);
    }

    public int updateFfcFbaWarehouse(FfcFbaWarehouse ffcFbaWarehouse) {
        return this.ffcFbaWarehouseMapper.updateFfcFbaWarehouse(ffcFbaWarehouse);
    }

    public int deleteFfcFbaWarehouseByIds(Long[] ids) {
        return this.ffcFbaWarehouseMapper.deleteFfcFbaWarehouseByIds(ids);
    }

    public int deleteFfcFbaWarehouseById(Long id) {
        return this.ffcFbaWarehouseMapper.deleteFfcFbaWarehouseById(id);
    }

    public int crawlData(String cookie) {
        int result = 0;
        try {
            result = getAllCity1("http://www.jiufanglogistics.cn/channel/1/fbackdz");
            System.out.println(""+ result);
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }

    public static int getAllCity(String baseUrl, String cookie) throws IOException {
        int result = 0;
        Document document = Jsoup.connect(baseUrl).get();
        if (document != null) {
            Elements list = document.getElementsByTag("p");
            if (list == null) {
                System.out.println("");
                return 0;
            }
            List<FfcFbaWarehouse> ffcFbaWareList = new ArrayList<>();
            int k = 8;
            int size = list.size() / 9 - 1;
            for (int j = 0; j < size; j++) {
                FfcFbaWarehouse ffcFbaWarehouse = new FfcFbaWarehouse();
                for (int i = 0; i < list.size(); i++) {
                    String text = list.eq(i).text();
                    if (i == 0)
                        ffcFbaWarehouse.setCompany(list.eq(i + k).text());
                    if (i == 1)
                        ffcFbaWarehouse.setCompanyCode(list.eq(i + k).text());
                    if (i == 2)
                        ffcFbaWarehouse.setPostalCode(list.eq(i + k).text());
                    if (i == 3)
                        ffcFbaWarehouse.setCountry(list.eq(i + k).text());
                    if (i == 4)
                        ffcFbaWarehouse.setProvince(list.eq(i + k).text());
                    if (i == 5)
                        ffcFbaWarehouse.setCity(list.eq(i + k).text());
                    if (i == 6)
                        ffcFbaWarehouse.setAddress(list.eq(i + k).text());
                    if (i == 7)
                        ffcFbaWarehouse.setRemote(list.eq(i + k).text());
                    if (i == 8) {
                        ffcFbaWarehouse.setModuleType("0");
                        k += 8;
                        break;
                    }
                }
            }
        } else {
            System.out.println("");
        }
        return result;
    }

    public int getAllCity1(String baseUrl) throws IOException {
        int total = 0;
        Document document = Jsoup.connect(baseUrl).get();
        if (document != null) {
            Elements list = document.getElementsByTag("p");
            Elements h4 = document.getElementsByTag("h4");
            String text1 = h4.eq(0).text();
            Elements tr = document.getElementsByTag("tr");
            int num = 0;
            for (int i = 0; i < tr.size(); i++) {
                Element element = (Element)tr.get(i);
                Elements td = element.getElementsByTag("td");
                FfcFbaWarehouse ffcFbaWarehouse = new FfcFbaWarehouse();
                for (int j = 0; j < 5; ) {
                    String text = td.eq(j).text();
                    if ("FBA Code".equals(text)) {
                        num++;
                        break;
                    }
                    if ((((num == 9) ? 1 : 0) | ((num == 10) ? 1 : 0) | ((num == 11) ? 1 : 0) | ((num == 13) ? 1 : 0)) != 0) {
                        String text11 = td.eq(j).text();
                        if (j == 0) {
                            ffcFbaWarehouse.setCompanyCode(text11);
                        } else if (j == 1) {
                            ffcFbaWarehouse.setAddress(text11);
                        } else if (j == 2) {
                            ffcFbaWarehouse.setCity(text11);
                        } else if (j == 3) {
                            ffcFbaWarehouse.setProvince(text11);
                        } else if (j == 4) {
                            ffcFbaWarehouse.setPostalCode(text11);
                        }
                        j++;
                    }
                }
                if (ffcFbaWarehouse.getCompanyCode() != null) {
                    ffcFbaWarehouse.setModuleType("3");
                    switch (num) {
                        case 9:
                            ffcFbaWarehouse.setCountry("");
                            break;
                        case 10:
                            ffcFbaWarehouse.setCountry("");
                            break;
                        case 11:
                            ffcFbaWarehouse.setCountry("");
                            break;
                        case 13:
                            ffcFbaWarehouse.setCountry("");
                            break;
                    }
                    total++;
                    System.out.println(ffcFbaWarehouse);
                    System.out.println(total);
                }
            }
        } else {
            System.out.println("");
        }
        return total;
    }

    public String uploadImage(String companyName, String link, MultipartFile file) throws IOException {
        try {
            String result = FileUploadUtils.upload(this.uploadPath, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            result = result.replaceFirst("/home/web/dist", "");
            result = this.serverPath + result;
            this.ffcFbaWarehouseMapper.insertImageInfo(companyName, link, result);
            return result;
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public Long calVipDayRemaining(Long userId) {
        if (userId == null)
            return Long.valueOf(0L);
        SysUser sysUser = this.userMapper.selectUserById(userId);
        Date vipCreateTime = sysUser.getVipCreateTime();
        if (vipCreateTime == null)
            return Long.valueOf(0L);
        try {
            Date nowDate = DateUtils.getNowDate();
            long diff = nowDate.getTime() - vipCreateTime.getTime();
            long days = diff / 86400000L;
            days = this.vipDayRemaining.longValue() - days;
            System.out.println("" + days + "");
            if (days == 0L || days < 0L) {
                SysUser user = new SysUser();
                user.setUserId(userId);
                user.setMemberVip("0");
                this.userMapper.updateUser(user);
                days = 0L;
            }
            return Long.valueOf(days);
        } catch (Exception exception) {
            return Long.valueOf(0L);
        }
    }

    public static void main(String[] args) throws IOException {
        String urlStr = "http://www.jiufanglogistics.cn/channel/1/fbackdz";
    }
}


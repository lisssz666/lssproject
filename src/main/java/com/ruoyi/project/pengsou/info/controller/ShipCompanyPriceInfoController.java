package com.ruoyi.project.pengsou.info.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.pengsou.info.domain.ShipCompanyPriceInfo;
import com.ruoyi.project.pengsou.info.service.IShipCompanyPriceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/price/info"})
public class ShipCompanyPriceInfoController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ShipCompanyPriceInfoController.class);

    @Autowired
    private IShipCompanyPriceInfoService shipCompanyPriceInfoService;

    @GetMapping({"/list"})
    public TableDataInfo list(ShipCompanyPriceInfo shipCompanyPriceInfo) {
        startPage();
        List<ShipCompanyPriceInfo> list = this.shipCompanyPriceInfoService.selectShipCompanyPriceInfoList(shipCompanyPriceInfo);
        return getDataTable(list);
    }

            @PostMapping({"/export"})
            public void export(HttpServletResponse response, ShipCompanyPriceInfo shipCompanyPriceInfo) {
        List<ShipCompanyPriceInfo> list = this.shipCompanyPriceInfoService.selectShipCompanyPriceInfoList(shipCompanyPriceInfo);
        ExcelUtil<ShipCompanyPriceInfo> util = new ExcelUtil(ShipCompanyPriceInfo.class);
        util.exportExcel(response, list, "");
    }

    @GetMapping({"/{id}"})
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(this.shipCompanyPriceInfoService.selectShipCompanyPriceInfoById(id));
    }

    @PostMapping({"/add"})
    public AjaxResult add(@RequestBody ShipCompanyPriceInfo shipCompanyPriceInfo) {
        log.info("conparams ====:" + shipCompanyPriceInfo);
        return toAjax(this.shipCompanyPriceInfoService.insertShipCompanyPriceInfo(shipCompanyPriceInfo));
    }

    @PutMapping
    public AjaxResult edit(@RequestBody ShipCompanyPriceInfo shipCompanyPriceInfo) {
        return toAjax(this.shipCompanyPriceInfoService.updateShipCompanyPriceInfo(shipCompanyPriceInfo));
    }

    @DeleteMapping({"/{ids}"})
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(this.shipCompanyPriceInfoService.deleteShipCompanyPriceInfoByIds(ids));
            }
}

package com.ruoyi.project.pengsou.fba.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.FbaTableDataInfo;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.pengsou.fba.domain.FfcFbaWarehouse;
import com.ruoyi.project.pengsou.fba.service.IFfcFbaWarehouseService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/fba/warehouse"})
public class FfcFbaWarehouseController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(FfcFbaWarehouseController.class);

    @Autowired
    private IFfcFbaWarehouseService ffcFbaWarehouseService;

    @GetMapping({"/list"})
    public FbaTableDataInfo list(FfcFbaWarehouse ffcFbaWarehouse) {
        System.out.print("ffcFbaWarehouse =="+ffcFbaWarehouse);
        System.out.print("CompanyCode =="+ffcFbaWarehouse.getCompanyCode());
        startPage();
        List<FfcFbaWarehouse> list = this.ffcFbaWarehouseService.selectFfcFbaWarehouseList(ffcFbaWarehouse);
        Long userId = ffcFbaWarehouse.getUserId();
        Long vipDayRemain = this.ffcFbaWarehouseService.calVipDayRemaining(userId);
        return getFbaDataTable(list, vipDayRemain);
    }

    @GetMapping({"/{id}"})
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(this.ffcFbaWarehouseService.selectFfcFbaWarehouseById(id));
    }

    @PostMapping({"/add"})
    public AjaxResult add(@RequestBody FfcFbaWarehouse ffcFbaWarehouse) {
        log.info("FfcFbaWarehouseController ====:" + ffcFbaWarehouse);
        return toAjax(this.ffcFbaWarehouseService.insertFfcFbaWarehouse(ffcFbaWarehouse));
    }

    @PutMapping({"/edit"})
    public AjaxResult edit(@RequestBody FfcFbaWarehouse ffcFbaWarehouse) {
        return toAjax(this.ffcFbaWarehouseService.updateFfcFbaWarehouse(ffcFbaWarehouse));
    }

    @DeleteMapping({"/delete"})
    public AjaxResult remove(@RequestParam(value = "ids") Long[] ids) {
        return toAjax(this.ffcFbaWarehouseService.deleteFfcFbaWarehouseByIds(ids));
    }

    @PostMapping({"/crawlData"})
    public AjaxResult crawlData(String cookie) {
    return toAjax(this.ffcFbaWarehouseService.crawlData(cookie));
    }

    @PostMapping({"/uploadImage"})
    public AjaxResult uploadImage(String companyName, String link, MultipartFile file) throws IOException {
        String result = this.ffcFbaWarehouseService.uploadImage(companyName, link, file);
        return AjaxResult.success("操作成功", result);
    }


    protected FbaTableDataInfo getFbaDataTable(List<?> list, Object vipDayRemainObj) {
        long vipDayRemain = Long.valueOf(String.valueOf(vipDayRemainObj)).longValue();
        FbaTableDataInfo rspData = new FbaTableDataInfo();
        rspData.setCode(200);
        rspData.setMsg("操作成功");
        rspData.setRows(list);
        rspData.setVipDayRemain(Long.valueOf(vipDayRemain));
        if (null == list || list.isEmpty()) {
            rspData.setTotal(0L);
        } else {
            long total = (new PageInfo(list)).getTotal();
            long size = list.size();
            rspData.setTotal(total);
        }
        return rspData;
    }
}

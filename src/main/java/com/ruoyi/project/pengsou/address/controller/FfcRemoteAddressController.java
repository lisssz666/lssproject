package com.ruoyi.project.pengsou.address.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.pengsou.address.domain.FfcRemoteAddress;
import com.ruoyi.project.pengsou.address.service.IFfcRemoteAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping({"/cms/address"})
public class FfcRemoteAddressController extends BaseController {
    @Autowired
    private IFfcRemoteAddressService ffcRemoteAddressService;

    @GetMapping({"/list"})
    public TableDataInfo list(FfcRemoteAddress ffcRemoteAddress) {
        startPage();
        List<FfcRemoteAddress> list = this.ffcRemoteAddressService.selectFfcRemoteAddressList(ffcRemoteAddress);
        return getDataTable(list);
    }

    @GetMapping({"/selectIfRemotelist"})
    public AjaxResult isRemoteAddresslist(FfcRemoteAddress ffcRemoteAddress) {
        Map<String, String> map = ffcRemoteAddressService.selectIfRemote(ffcRemoteAddress);
        return AjaxResult.success(map);
    }

    @PostMapping({"/export"})
    public void export(HttpServletResponse response, FfcRemoteAddress ffcRemoteAddress) {
        List<FfcRemoteAddress> list = this.ffcRemoteAddressService.selectFfcRemoteAddressList(ffcRemoteAddress);
        ExcelUtil<FfcRemoteAddress> util = new ExcelUtil(FfcRemoteAddress.class);
        util.exportExcel(response, list, "偏远地区数据");
    }

    @GetMapping({"/{id}"})
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(this.ffcRemoteAddressService.selectFfcRemoteAddressById(id));
    }

    @PostMapping({"/add"})
    public AjaxResult add(@RequestBody FfcRemoteAddress ffcRemoteAddress) {
    return AjaxResult.success(ffcRemoteAddressService.insertFfcRemoteAddress(ffcRemoteAddress));
    }

    @PostMapping({"/edit"})
    public AjaxResult edit(@RequestBody FfcRemoteAddress ffcRemoteAddress) {
    return toAjax(this.ffcRemoteAddressService.updateFfcRemoteAddress(ffcRemoteAddress));
    }

    @DeleteMapping({"/delete"})
    public AjaxResult remove(Long id) {
    return toAjax(this.ffcRemoteAddressService.deleteFfcRemoteAddressById(id));
    }
}


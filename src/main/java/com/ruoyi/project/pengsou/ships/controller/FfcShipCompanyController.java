package com.ruoyi.project.pengsou.ships.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.pengsou.ships.domain.FfcShipCompany;
import com.ruoyi.project.pengsou.ships.service.IFfcShipCompanyService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/ships"})
public class FfcShipCompanyController extends BaseController {
    @Autowired
    private IFfcShipCompanyService ffcShipCompanyService;

    @GetMapping({"/list"})
    public TableDataInfo list(FfcShipCompany ffcShipCompany) {
        startPage();
        List<FfcShipCompany> list = this.ffcShipCompanyService.selectFfcShipCompanyList(ffcShipCompany);
        return getDataTable(list);
    }

    @PostMapping({"/add"})
    public AjaxResult add(@RequestBody FfcShipCompany ffcShipCompany) {
        return toAjax(this.ffcShipCompanyService.insertFfcShipCompany(ffcShipCompany));
    }

    @PostMapping({"/delete"})
    public AjaxResult remove(@RequestBody Map<String, Long[]> map) {
        Long[] ids = map.get("ids");
        return toAjax(this.ffcShipCompanyService.deleteFfcShipCompanyByIds(ids));
    }
}


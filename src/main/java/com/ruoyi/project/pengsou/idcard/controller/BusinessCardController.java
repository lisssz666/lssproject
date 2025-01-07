package com.ruoyi.project.pengsou.idcard.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.pengsou.address.domain.FfcRemoteAddress;
import com.ruoyi.project.pengsou.idcard.domain.BusinessCard;
import com.ruoyi.project.pengsou.idcard.service.BusinessCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/businessCards")
public class BusinessCardController extends BaseController {

    @Autowired
    private BusinessCardService businessCardService;

    /**
     * 获取所有名片信息
     */
    @GetMapping
    public List<BusinessCard> getAllBusinessCards() {
        return businessCardService.list();
    }

    @GetMapping("/findAll")
    public TableDataInfo findAllBusinessCards(BusinessCard businessCard) {
        System.out.print("businessCard =="+businessCard);
        startPage();
        List<BusinessCard> all = businessCardService.findAll(businessCard);
        return getDataTable(all);
    }

    /**
     * 根据ID获取名片信息
     */
    @GetMapping("/{id}")
    public BusinessCard getBusinessCardById(@PathVariable Long id) {
        return businessCardService.getById(id);
    }

    /**
     * 添加名片信息
     */
    @PostMapping("/add")
    public AjaxResult addBusinessCard(@RequestBody BusinessCard businessCard) throws IOException {
        Long res=  businessCardService.addBusinessCard(businessCard);
        return AjaxResult.success("操作完成",res);
    }

    /**
     * 更新名片信息
     */
    @PostMapping("/edit")
    public AjaxResult updateBusinessCard(@RequestBody BusinessCard businessCard) throws IOException{
        return AjaxResult.success(businessCardService.updateBusinessCard(businessCard));
    }

    /**
     * 更新名片信息2
     */
    @PostMapping("/edit2")
    public AjaxResult updateBusinessCard2(BusinessCard businessCard) throws IOException{
        return AjaxResult.success(businessCardService.updateBusinessCard(businessCard));
    }

    /**
     * 删除名片信息
     */
    @DeleteMapping("/delete")
    public AjaxResult deleteBusinessCard(Long id) {
        return AjaxResult.success(businessCardService.deleteBusinessCard(id));
    }
}

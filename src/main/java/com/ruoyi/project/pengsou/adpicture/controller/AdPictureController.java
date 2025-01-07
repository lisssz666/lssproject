package com.ruoyi.project.pengsou.adpicture.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import java.io.IOException;
import java.util.List;

import com.ruoyi.project.pengsou.adpicture.domain.AdPicture;
import com.ruoyi.project.pengsou.adpicture.service.IAdPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/adpicture"})
public class AdPictureController extends BaseController {
    @Autowired
    private IAdPictureService adPictureService;

    @GetMapping({"/list"})
    public TableDataInfo list(AdPicture adPicture) {
        startPage();
        List<AdPicture> list = this.adPictureService.selectAdPictureList(adPicture);
        return getDataTable(list);
    }

    @DeleteMapping({"/delete"})
    public AjaxResult remove(Long id) {
        return toAjax(this.adPictureService.deleteAdPictureById(id));
    }

    @PostMapping({"/uploadAdImage"})
    public AjaxResult uploadImage(String link, MultipartFile fileName,String title,String content,Integer sign ) throws IOException {
        System.out.print("fileName =="+fileName);
        System.out.print("title =="+title);
        int result = this.adPictureService.uploadImage(fileName, link, title, content,sign);
        return AjaxResult.success("操作成功", result);
    }

    @PostMapping({"/edit"})
    public AjaxResult editAd(Long id,String link, MultipartFile fileName,String title,String content,  Integer sign) throws IOException {
        System.out.print("fileName =="+fileName);
        System.out.print("title =="+title);
        int result = this.adPictureService.editAd(id, fileName, link, title, content,sign);
        return AjaxResult.success("操作成功", result);
    }
}

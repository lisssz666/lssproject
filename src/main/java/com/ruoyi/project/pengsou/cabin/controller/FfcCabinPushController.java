package com.ruoyi.project.pengsou.cabin.controller;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.pengsou.cabin.domain.FfcCabinPush;
import com.ruoyi.project.pengsou.cabin.domain.vo.FfcCabinPushVo;
import com.ruoyi.project.pengsou.cabin.service.FfcCabinPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cabin/push")
public class FfcCabinPushController extends BaseController {
    @Autowired
    private FfcCabinPushService service;

    @GetMapping("/findAll")
    public TableDataInfo findAll(FfcCabinPush cabinPush) {
        startPage();
        List<FfcCabinPush> list = service.findAll(cabinPush);
        return getDataTable(list);
    }


    @PostMapping("/add")
    public AjaxResult create(FfcCabinPush ffcCabinPush) throws IOException {
        System.out.print("ffcCabinPush =="+ffcCabinPush);
        System.out.print("MainContent =="+ffcCabinPush.getMainContent());
        int created = service.createFfcCabinPush(ffcCabinPush);
        return AjaxResult.success(created);
    }

    @PostMapping("/add1")
    public AjaxResult create3(@RequestBody Map<String, Object> stamp) throws IOException {
        // 打印调试信息
        System.out.println("stamp===: " + stamp);

        // 创建FfcCabinPush对象
        FfcCabinPush ffcCabinPush = new FfcCabinPush();

        // 假设Map中的键与FfcCabinPush的属性名相对应
        ffcCabinPush.setSubject((String) stamp.get("subject"));
        ffcCabinPush.setLink((String) stamp.get("link"));
        ffcCabinPush.setContent((String) stamp.get("content"));

        // 由于@JsonIgnore注解，logo和logoPath需要手动处理
        // 假设stamp中包含了logo文件的二进制数据及其路径
        MultipartFile logo = (MultipartFile) stamp.get("logo");
        ffcCabinPush.setLogo(logo);

        // 处理mainContent列表
        List<FfcCabinPushVo> mainContentList = new ArrayList<>();
        List<Map<String, Object>> mainContentMapList = (List<Map<String, Object>>) stamp.get("mainContent");
        for (Map<String, Object> contentMap : mainContentMapList) {
            FfcCabinPushVo vo = new FfcCabinPushVo();
            vo.setContent((String) contentMap.get("content"));

            // 假设contentMap中包含了image文件的二进制数据及其路径
            MultipartFile image = (MultipartFile) contentMap.get("image");
            // 这里你需要将imageBytes保存为文件，并设置imagePath
            vo.setImage(image);

            mainContentList.add(vo);
        }
        ffcCabinPush.setMainContent(mainContentList);

        System.out.println("ffcCabinPush===: " + ffcCabinPush);
        // 调用服务层方法
        int created = service.createFfcCabinPush(ffcCabinPush);

        // 返回结果
        return AjaxResult.success(created);
    }

    @PostMapping("/edit")
    public AjaxResult edit(FfcCabinPush ffcCabinPush) throws IOException {
//        int res = service.editCabinPush(ffcCabinPush);
        return AjaxResult.success(1);
    }

    @DeleteMapping("/delete")
    public AjaxResult deleteById(Long id) {
        return AjaxResult.success(service.deleteById(id));
    }
}

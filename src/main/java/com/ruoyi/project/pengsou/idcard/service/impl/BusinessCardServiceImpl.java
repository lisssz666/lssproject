package com.ruoyi.project.pengsou.idcard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.project.pengsou.idcard.domain.BusinessCard;
import com.ruoyi.project.pengsou.idcard.mapper.BusinessCardMapper;
import com.ruoyi.project.pengsou.idcard.service.BusinessCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

// Service实现
@Service
public class BusinessCardServiceImpl extends ServiceImpl<BusinessCardMapper, BusinessCard> implements BusinessCardService {

    @Autowired
    private BusinessCardMapper businessCardMapper;

    //文件上传路径
    @Value("${spring.upload.path}")
    private String uploadImgPath;

    @Override
    public Long addBusinessCard(BusinessCard businessCard) throws IOException {
        MultipartFile avatar = businessCard.getAvatarImageFile();
        System.out.print("球员avatar文件 ： "+avatar);
        if (avatar != null)
        {
            //上传头像
            String path = FileUploadUtils.upload(uploadImgPath, avatar);
            businessCard.setAvatarImage(path);
        }
        businessCardMapper.addBusinessCard(businessCard);
        return businessCard.getId();
    }

    // 查找所有数据的方法
    public List<BusinessCard> findAll(BusinessCard businessCard) {
        // 使用 MyBatis-Plus 提供的 list 方法获取所有数据
        return businessCardMapper.findAllBusinessCard(businessCard);
    }

    @Override
    public int deleteBusinessCard(Long id) {
        return businessCardMapper.deleteBusinessCard(id);
    }

    @Override
    public Long updateBusinessCard(BusinessCard businessCard) throws IOException{
        MultipartFile avatar = businessCard.getAvatarImageFile();
        System.out.print("球员avatar文件 ： "+avatar);
        if (avatar != null)
        {
            //上传头像
            String path = FileUploadUtils.upload(uploadImgPath, avatar);
            businessCard.setAvatarImage(path);
        }
        businessCardMapper.updateBusinessCard(businessCard);
        return businessCard.getId();
    }
}

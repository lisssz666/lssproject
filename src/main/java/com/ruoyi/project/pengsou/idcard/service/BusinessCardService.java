package com.ruoyi.project.pengsou.idcard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.pengsou.idcard.domain.BusinessCard;

import java.io.IOException;
import java.util.List;

// Service接口
public interface BusinessCardService extends IService<BusinessCard> {

    //新增
    Long addBusinessCard(BusinessCard businessCard) throws IOException;
    //查所有
    List<BusinessCard> findAll(BusinessCard businessCard);
    //删除
    int deleteBusinessCard(Long id);

    Long updateBusinessCard(BusinessCard businessCard) throws IOException;
}


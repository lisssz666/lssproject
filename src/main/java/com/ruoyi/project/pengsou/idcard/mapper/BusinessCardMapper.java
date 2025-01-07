package com.ruoyi.project.pengsou.idcard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.pengsou.idcard.domain.BusinessCard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessCardMapper extends BaseMapper<BusinessCard> {

    int addBusinessCard(BusinessCard businessCard);

    List<BusinessCard> findAllBusinessCard(BusinessCard businessCard);

    int deleteBusinessCard(Long id);

    Long updateBusinessCard(BusinessCard businessCard);
}

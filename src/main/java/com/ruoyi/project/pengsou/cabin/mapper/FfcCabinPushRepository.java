package com.ruoyi.project.pengsou.cabin.mapper;

import com.ruoyi.project.pengsou.cabin.domain.FfcCabinPush;
import com.ruoyi.project.pengsou.cabin.domain.FfcCabinPushEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FfcCabinPushRepository{

    int saveCabinPush(FfcCabinPush ffcCabinPush);

    List<FfcCabinPush> findAllCabinPush(FfcCabinPush cabinPush);

    int deleteById(Long id);

    Optional<FfcCabinPush> findById(Long id);

}
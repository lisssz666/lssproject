package com.ruoyi.project.pengsou.cabin.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
public class FfcCabinPushVo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String content;

    private String imagePath;

    @JsonIgnore
    private MultipartFile image;

}


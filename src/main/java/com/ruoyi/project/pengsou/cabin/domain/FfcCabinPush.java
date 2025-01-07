package com.ruoyi.project.pengsou.cabin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.pengsou.cabin.domain.vo.FfcCabinPushVo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "ffc_cabin_push")
public class FfcCabinPush extends BaseEntity {
    private Integer id;

    private String subject;

    private String link;

    @JsonIgnore
    private String content;

    @JsonIgnore
    private MultipartFile logo;

    private String logoPath;

    private List<FfcCabinPushVo> mainContent;

    // 搜索内容（用于搜索功能）
    private String searchContent;
}
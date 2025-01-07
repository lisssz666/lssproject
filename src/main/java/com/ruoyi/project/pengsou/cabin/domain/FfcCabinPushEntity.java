package com.ruoyi.project.pengsou.cabin.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "ffc_cabin_push")
@Entity
public class FfcCabinPushEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 主题
    private String subject;

    // 图片
    private String logo;

    // 内容
    private String content;

    // 链接
    private String link;

    // 创建时间
    private Date createTime;

    // 更新者
    private String updateBy;

    // 更新时间
    private Date updateTime;
}
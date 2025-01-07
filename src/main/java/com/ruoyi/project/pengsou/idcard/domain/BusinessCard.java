package com.ruoyi.project.pengsou.idcard.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 业务名片信息表实体类
 */
@ApiModel(description = "业务名片信息表")
@TableName("ffc_business_card")
@Data
public class BusinessCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 职位
     */
    @ApiModelProperty(value = "职位")
    private String position;

    /**
     * 公司
     */
    @ApiModelProperty(value = "公司")
    private String company;

    /**
     * 业务范围
     */
    @ApiModelProperty(value = "业务范围")
    private String businessScope;

    /**
     * 头像图片文件
     */
    @ApiModelProperty(value = "头像图片文件")
    private MultipartFile avatarImageFile;

    /**
     * 头像图片URL
     */
    @ApiModelProperty(value = "头像图片URL")
    private String avatarImage;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * QQ
     */
    @ApiModelProperty(value = "QQ")
    private String qq;

    /**
     * 微信
     */
    @ApiModelProperty(value = "微信")
    private String wechat;

    // 搜索内容（用于搜索功能）
    private String searchContent;

}

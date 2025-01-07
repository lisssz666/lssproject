package com.ruoyi.project.pengsou.adpicture.domain;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdPicture extends BaseEntity {
    private static final long serialVersionUID = 1L;

    // 唯一标识符
    private Long id;
    // 链接地址
    private String link;
    // 文件
    private MultipartFile fileName;
    // 文件路径
    private String filePath;
    // 标题
    private String title;
    // 内容
    private String content;
    // 标志位，1 pc广告，2 小程序广告
    private int sign;

    // 搜索内容（用于搜索功能）
    private String searchContent;

}


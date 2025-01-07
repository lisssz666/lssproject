package com.ruoyi.project.pengsou.adpicture.service;
import java.io.IOException;
import java.util.List;

import com.ruoyi.project.pengsou.adpicture.domain.AdPicture;
import org.springframework.web.multipart.MultipartFile;

public interface IAdPictureService {
    AdPicture selectAdPictureById(Long paramLong);

    List<AdPicture> selectAdPictureList(AdPicture paramAdPicture);

    int deleteAdPictureByIds(Long[] paramArrayOfLong);

    int deleteAdPictureById(Long paramLong);

    int uploadImage(MultipartFile File, String link,String title,String content ,Integer sign) throws IOException;

    int editAd(Long id,MultipartFile File, String link,String title,String content ,Integer sign) throws IOException;
}

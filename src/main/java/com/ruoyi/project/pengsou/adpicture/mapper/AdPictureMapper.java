package com.ruoyi.project.pengsou.adpicture.mapper;

import com.ruoyi.project.pengsou.adpicture.domain.AdPicture;
import java.util.List;

public interface AdPictureMapper {
    AdPicture selectAdPictureById(Long paramLong);

    List<AdPicture> selectAdPictureList(AdPicture paramAdPicture);

    int deleteAdPictureById(Long id);

    int deleteAdPictureByIds(Long[] paramArrayOfLong);

    int insertImageInfo(String link, String filePath,String title,String content,Integer sign);

    int editAd(Long id,String link, String filePath, String title, String content, Integer sign);
}

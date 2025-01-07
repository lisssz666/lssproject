package com.ruoyi.project.pengsou.adpicture.service.impl;

import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import java.io.IOException;
import java.util.List;

import com.ruoyi.project.pengsou.adpicture.domain.AdPicture;
import com.ruoyi.project.pengsou.adpicture.mapper.AdPictureMapper;
import com.ruoyi.project.pengsou.adpicture.service.IAdPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdPictureServiceImpl implements IAdPictureService {
    @Value("${spring.upload.path}")
    private String uploadPath;

    @Value("${spring.upload.server}")
    private String serverPath;

    @Autowired
    private AdPictureMapper adPictureMapper;

    public AdPicture selectAdPictureById(Long id) {
        return this.adPictureMapper.selectAdPictureById(id);
    }

    public List<AdPicture> selectAdPictureList(AdPicture adPicture) {
        return this.adPictureMapper.selectAdPictureList(adPicture);
    }

    public int deleteAdPictureByIds(Long[] ids) {
        return this.adPictureMapper.deleteAdPictureByIds(ids);
    }

    public int deleteAdPictureById(Long id) {
        return this.adPictureMapper.deleteAdPictureById(id);
    }

    public int uploadImage(MultipartFile file, String link,String title,String content,Integer sign ) throws IOException {
        String result =null;
        if (file != null && !file.isEmpty()) {
            try {
                result = FileUploadUtils.upload(this.uploadPath, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
                result = result.replaceFirst("/home/web/dist", "");
                result = this.serverPath + result;
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            }
        }
        int i = adPictureMapper.insertImageInfo(link, result, title, content, sign);
        return i;
    }

    //编辑
    @Override
    public int editAd(Long id,MultipartFile file, String link,String title,String content,Integer sign ) throws IOException{
        String result =null;
        if (file != null && !file.isEmpty()){
            try {
                result = FileUploadUtils.upload(this.uploadPath, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
                result = result.replaceFirst("/home/web/dist", "");
                result = this.serverPath + result;
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            }
        }
        int i = adPictureMapper.editAd(id,link, result, title, content, sign);
        return i;
    }
}


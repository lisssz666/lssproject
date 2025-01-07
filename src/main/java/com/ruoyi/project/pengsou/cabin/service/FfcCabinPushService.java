package com.ruoyi.project.pengsou.cabin.service;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.project.pengsou.cabin.domain.FfcCabinPush;
import com.ruoyi.project.pengsou.cabin.domain.vo.FfcCabinPushVo;
import com.ruoyi.project.pengsou.cabin.mapper.FfcCabinPushRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class FfcCabinPushService {
    @Autowired
    private FfcCabinPushRepository repository;

    //文件上传路径
    @Value("${spring.upload.path}")
    private String uploadImgPath;

    //文件访问路径
    @Value("${spring.upload.server}")
    private String server;

    public int createFfcCabinPush(FfcCabinPush ffcCabinPush)  throws IOException {
        MultipartFile logo = ffcCabinPush.getLogo();
        if (logo != null){
            // 上传图片
            String path = FileUploadUtils.upload(uploadImgPath, logo);
            path = path.replaceFirst("/home/web/dist", "");
            ffcCabinPush.setLogoPath(path);
        }
        List<FfcCabinPushVo> mainContent = ffcCabinPush.getMainContent();
        StringBuilder paths = new StringBuilder();
        if (mainContent != null && !mainContent.isEmpty()) {
            for (FfcCabinPushVo content : mainContent) {
                String content1 = content.getContent();
                MultipartFile imageFile = content.getImage();
                if (imageFile != null && !imageFile.isEmpty()) {
                    // 上传图片
                    String path = FileUploadUtils.upload(uploadImgPath, imageFile);
                    //nginx 配置了/img ，映射到/home/web/dist/img，所以只需要访问/img
                    path = path.replaceFirst("/home/web/dist", "");
                    paths.append(content1).append("&&").append(path).append("##");
                }
            }
            // 移除最后一个逗号（如果有上传文件的话）
            if (paths.length() > 0) {
                paths.setLength(paths.length() - 1);
            }
        }
        ffcCabinPush.setContent(paths.toString());
        return repository.saveCabinPush(ffcCabinPush);
    }

    public List<FfcCabinPush> findAll(FfcCabinPush cabinPush) {
        List<FfcCabinPush> ffcCabinPushList = repository.findAllCabinPush(cabinPush);
        for (FfcCabinPush ffcCabinPush : ffcCabinPushList) {
            String logo = ffcCabinPush.getLogoPath();
            String logoPath =  logo !=null ? server + logo.trim() : null;
            ffcCabinPush.setLogoPath(logoPath);
            List<FfcCabinPushVo> fullImagePaths = new ArrayList<>();
            if (ffcCabinPush != null && ffcCabinPush.getContent() != null) {
                String[] contents = ffcCabinPush.getContent().split("##");
                for (String content : contents) {
                    List<String> split = Arrays.asList(content.split("&&"));
                    FfcCabinPushVo ffcCabinPushVo = new FfcCabinPushVo();
                    // 确保split至少有两个元素
                    if (split.size() > 1) {
                        String con = split.get(0);
                        String image = split.get(1);
                        // server服务器地址前缀
                        String fullImagePath = server + image.trim();

                        // 创建新的FfcCabinPushVo对象，以避免在同一个对象上重复设置属性
                        ffcCabinPushVo.setImagePath(fullImagePath);
                        ffcCabinPushVo.setContent(con);
                        fullImagePaths.add(ffcCabinPushVo);
                    } else {
                        // 处理split只有一个元素或没有元素的情况
                        // 例如，可以设置默认值或进行其他逻辑处理
                        ffcCabinPushVo.setContent(split.get(0)); // 假设至少有一个元素
                        fullImagePaths.add(ffcCabinPushVo);
                    }
                }
            }
            // 将fullImagePaths设置到ffcCabinPush对象中，而不是单个FfcCabinPushVo对象
            ffcCabinPush.setMainContent(fullImagePaths);
        }
        return ffcCabinPushList;
    }

    public int deleteById(Long id) {
        // 查询实体，获取文件路径
        FfcCabinPush entity = repository.findById(id).orElse(null);
        if (entity == null) {
            return 0; // 如果实体不存在，返回0
        }

        // 删除logo文件
        if (entity.getLogoPath() != null && !entity.getLogoPath().isEmpty()) {
            deleteFile(entity.getLogoPath());
        }

        // 删除关联的image文件
//        List<FfcCabinPushVo> mainContent = entity.getMainContent();
//        if (mainContent != null) {
//            for (FfcCabinPushVo vo : mainContent) {
//                if (vo.getImagePath() != null && !vo.getImagePath().isEmpty()) {
//                    deleteFile(vo.getImagePath());
//                }
//            }
//        }
        // 删除实体
        int i = repository.deleteById(id);
        return i;
    }


    /**
     * 删除指定路径的文件。
     *
     * @param filePath 文件路径
     * @return 是否删除成功
     */
    public boolean deleteFile(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return false;
        }

        File file = new File(filePath);
        if (file.exists()) {
            try {
                // 使用Files类删除文件
                Files.deleteIfExists(Paths.get(filePath));
                return true;
            } catch (Exception e) {
                // 日志记录异常信息
                // log.error("Error deleting file: " + filePath, e);
                return false;
            }
        }
        return false;
    }




//    public int deleteById(Long id) {
//        int i = repository.deleteById(id);
//        return i;
//    }


//    public Optional<FfcCabinPush> findById(Integer id) {
//        return repository.findById(id);
//    }
//
//    public void deleteById(Integer id) {
//        repository.deleteById(id);
//    }

}


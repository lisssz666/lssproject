package com.ruoyi.project.red.mapper;

import java.util.List;
import com.ruoyi.project.red.domain.RedCaptcha;

/**
 * 验证码Mapper接口
 * 
 * @author ruoyi
 * @date 2021-12-10
 */
public interface RedCaptchaMapper 
{
    /**
     * 查询验证码
     * 
     * @param captchaId 验证码主键
     * @return 验证码
     */
    public RedCaptcha selectRedCaptchaByCaptchaId(Long captchaId);

    /**
     * 查询验证码列表
     * 
     * @param redCaptcha 验证码
     * @return 验证码集合
     */
    public List<RedCaptcha> selectRedCaptchaList(RedCaptcha redCaptcha);

    /**
     * 新增验证码
     * 
     * @param redCaptcha 验证码
     * @return 结果
     */
    public int insertRedCaptcha(RedCaptcha redCaptcha);

    /**
     * 修改验证码
     * 
     * @param redCaptcha 验证码
     * @return 结果
     */
    public int updateRedCaptcha(RedCaptcha redCaptcha);

    /**
     * 删除验证码
     * 
     * @param captchaId 验证码主键
     * @return 结果
     */
    public int deleteRedCaptchaByCaptchaId(Long captchaId);

    /**
     * 批量删除验证码
     * 
     * @param captchaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRedCaptchaByCaptchaIds(Long[] captchaIds);
    
    
    public RedCaptcha selectRedCaptchaByCaptchaKey(String key);
}

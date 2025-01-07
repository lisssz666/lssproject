package com.ruoyi.project.red.service;

import java.util.List;
import com.ruoyi.project.red.domain.RedCaptcha;

/**
 * 验证码Service接口
 * 
 * @author ruoyi
 * @date 2021-12-10
 */
public interface IRedCaptchaService 
{
    /**
     * 查询验证码
     * 
     * @param captchaId 验证码主键
     * @return 验证码
     */
    public RedCaptcha selectRedCaptchaByCaptchaId(Long captchaId);
    
    /**
     * 查询
     * @param captchaKey
     * @return
     */
    public RedCaptcha selectRedCaptchaByCaptchaKey(String captchaKey);

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
     * 批量删除验证码
     * 
     * @param captchaIds 需要删除的验证码主键集合
     * @return 结果
     */
    public int deleteRedCaptchaByCaptchaIds(Long[] captchaIds);

    /**
     * 删除验证码信息
     * 
     * @param captchaId 验证码主键
     * @return 结果
     */
    public int deleteRedCaptchaByCaptchaId(Long captchaId);
}

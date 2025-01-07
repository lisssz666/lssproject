package com.ruoyi.project.red.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.red.mapper.RedCaptchaMapper;
import com.ruoyi.project.red.domain.RedCaptcha;
import com.ruoyi.project.red.service.IRedCaptchaService;

/**
 * 验证码Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-12-10
 */
@Service
public class RedCaptchaServiceImpl implements IRedCaptchaService 
{
    @Autowired
    private RedCaptchaMapper redCaptchaMapper;

    /**
     * 查询验证码
     * 
     * @param captchaId 验证码主键
     * @return 验证码
     */
    @Override
    public RedCaptcha selectRedCaptchaByCaptchaId(Long captchaId)
    {
        return redCaptchaMapper.selectRedCaptchaByCaptchaId(captchaId);
    }

    /**
     * 查询验证码列表
     * 
     * @param redCaptcha 验证码
     * @return 验证码
     */
    @Override
    public List<RedCaptcha> selectRedCaptchaList(RedCaptcha redCaptcha)
    {
        return redCaptchaMapper.selectRedCaptchaList(redCaptcha);
    }

    /**
     * 新增验证码
     * 
     * @param redCaptcha 验证码
     * @return 结果
     */
    @Override
    public int insertRedCaptcha(RedCaptcha redCaptcha)
    {
        redCaptcha.setCreateTime(DateUtils.getNowDate());
        return redCaptchaMapper.insertRedCaptcha(redCaptcha);
    }

    /**
     * 修改验证码
     * 
     * @param redCaptcha 验证码
     * @return 结果
     */
    @Override
    public int updateRedCaptcha(RedCaptcha redCaptcha)
    {
        return redCaptchaMapper.updateRedCaptcha(redCaptcha);
    }

    /**
     * 批量删除验证码
     * 
     * @param captchaIds 需要删除的验证码主键
     * @return 结果
     */
    @Override
    public int deleteRedCaptchaByCaptchaIds(Long[] captchaIds)
    {
        return redCaptchaMapper.deleteRedCaptchaByCaptchaIds(captchaIds);
    }

    /**
     * 删除验证码信息
     * 
     * @param captchaId 验证码主键
     * @return 结果
     */
    @Override
    public int deleteRedCaptchaByCaptchaId(Long captchaId)
    {
        return redCaptchaMapper.deleteRedCaptchaByCaptchaId(captchaId);
    }

	@Override
	public RedCaptcha selectRedCaptchaByCaptchaKey(String captchaKey) {
		return redCaptchaMapper.selectRedCaptchaByCaptchaKey(captchaKey);
	}
}

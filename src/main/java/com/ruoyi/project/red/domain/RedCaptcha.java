package com.ruoyi.project.red.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 验证码对象 red_captcha
 * 
 * @author ruoyi
 * @date 2021-12-10
 */
public class RedCaptcha extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 验证码id */
    private Long captchaId;

    /** 验证码Key */
    @Excel(name = "验证码Key")
    private String captchaKey;

    /** 验证码 */
    @Excel(name = "验证码")
    private String captchaCode;

    /** 验证码有效期 */
    @Excel(name = "验证码有效期")
    private Integer captchaExpiration;

    /** 有效期单位 */
    @Excel(name = "有效期单位")
    private String captchaUtil;

    /** 超期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "超期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expreationTime;

    public void setCaptchaId(Long captchaId) 
    {
        this.captchaId = captchaId;
    }

    public Long getCaptchaId() 
    {
        return captchaId;
    }
    public void setCaptchaKey(String captchaKey) 
    {
        this.captchaKey = captchaKey;
    }

    public String getCaptchaKey() 
    {
        return captchaKey;
    }
    public void setCaptchaCode(String captchaCode) 
    {
        this.captchaCode = captchaCode;
    }

    public String getCaptchaCode() 
    {
        return captchaCode;
    }
    public void setCaptchaExpiration(Integer captchaExpiration) 
    {
        this.captchaExpiration = captchaExpiration;
    }

    public Integer getCaptchaExpiration() 
    {
        return captchaExpiration;
    }
    public void setCaptchaUtil(String captchaUtil) 
    {
        this.captchaUtil = captchaUtil;
    }

    public String getCaptchaUtil() 
    {
        return captchaUtil;
    }
    public void setExpreationTime(Date expreationTime) 
    {
        this.expreationTime = expreationTime;
    }

    public Date getExpreationTime() 
    {
        return expreationTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("captchaId", getCaptchaId())
            .append("captchaKey", getCaptchaKey())
            .append("captchaCode", getCaptchaCode())
            .append("captchaExpiration", getCaptchaExpiration())
            .append("captchaUtil", getCaptchaUtil())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("expreationTime", getExpreationTime())
            .toString();
    }
}

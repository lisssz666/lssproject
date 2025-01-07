package com.ruoyi.project.red.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 登录用户信息对象 red_user
 * 
 * @author ruoyi
 * @date 2021-12-10
 */
public class RedUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 缓存ID */
    private Long redUserId;


    /** 用户ID */
    private Long userId;

    /** 缓存KEY */
    @Excel(name = "缓存KEY")
    private String userKey;

    /** 缓存用户信息 */
    @Excel(name = "缓存用户信息")
    private String sysuser;

    /** 缓存登录信息 */
    @Excel(name = "缓存登录信息")
    private String logininfo;

    /** 权限信息 */
    @Excel(name = "权限信息")
    private String permissions;

    /** 有效期 */
    @Excel(name = "有效期")
    private Integer captchaExpiration;

    /** 有效期单位 */
    @Excel(name = "有效期单位")
    private String captchaUtil;

    /** 超期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "超期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expreationTime;

    public Long getRedUserId() {
        return redUserId;
    }

    public void setRedUserId(Long redUserId) {
        this.redUserId = redUserId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserKey(String userKey) 
    {
        this.userKey = userKey;
    }

    public String getUserKey() 
    {
        return userKey;
    }
    public void setSysuser(String sysuser) 
    {
        this.sysuser = sysuser;
    }

    public String getSysuser() 
    {
        return sysuser;
    }
    public void setLogininfo(String logininfo) 
    {
        this.logininfo = logininfo;
    }

    public String getLogininfo() 
    {
        return logininfo;
    }
    public void setPermissions(String permissions) 
    {
        this.permissions = permissions;
    }

    public String getPermissions() 
    {
        return permissions;
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
                .append("RedUserId", getRedUserId())
            .append("userId", getUserId())
            .append("userKey", getUserKey())
            .append("sysuser", getSysuser())
            .append("logininfo", getLogininfo())
            .append("permissions", getPermissions())
            .append("captchaExpiration", getCaptchaExpiration())
            .append("captchaUtil", getCaptchaUtil())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("expreationTime", getExpreationTime())
            .toString();
    }
}

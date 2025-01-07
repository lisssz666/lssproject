package com.ruoyi.project.red.service;

import java.util.List;
import com.ruoyi.project.red.domain.RedUser;

/**
 * 登录用户信息Service接口
 * 
 * @author ruoyi
 * @date 2021-12-10
 */
public interface IRedUserService 
{
    /**
     * 查询登录用户信息
     * 
     * @param userId 登录用户信息主键
     * @return 登录用户信息
     */
    public RedUser selectRedUserByUserId(Long userId);
    
    /**
     * 根据KEY 查找登录信息
     * @param userKey
     * @return
     */
    public RedUser selectRedUserByUserKey(String userKey);

    /**
     * 查询登录用户信息列表
     * 
     * @param redUser 登录用户信息
     * @return 登录用户信息集合
     */
    public List<RedUser> selectRedUserList(RedUser redUser);

    /**
     * 新增登录用户信息
     * 
     * @param redUser 登录用户信息
     * @return 结果
     */
    public int insertRedUser(RedUser redUser);

    /**
     * 修改登录用户信息
     * 
     * @param redUser 登录用户信息
     * @return 结果
     */
    public int updateRedUser(RedUser redUser);

    /**
     * 批量删除登录用户信息
     * 
     * @param userIds 需要删除的登录用户信息主键集合
     * @return 结果
     */
    public int deleteRedUserByUserIds(Long[] userIds);

    /**
     * 删除登录用户信息信息
     * 
     * @param userId 登录用户信息主键
     * @return 结果
     */
    public int deleteRedUserByUserId(Long userId);
    
    public int deleteRedUserByUserKey(String userKey);
}

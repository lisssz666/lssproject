package com.ruoyi.project.red.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.red.mapper.RedUserMapper;
import com.ruoyi.project.red.domain.RedUser;
import com.ruoyi.project.red.service.IRedUserService;

/**
 * 登录用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-12-10
 */
@Service
public class RedUserServiceImpl implements IRedUserService 
{
    @Autowired
    private RedUserMapper redUserMapper;

    /**
     * 查询登录用户信息
     * 
     * @param userId 登录用户信息主键
     * @return 登录用户信息
     */
    @Override
    public RedUser selectRedUserByUserId(Long userId)
    {
        return redUserMapper.selectRedUserByUserId(userId);
    }

    /**
     * 查询登录用户信息列表
     * 
     * @param redUser 登录用户信息
     * @return 登录用户信息
     */
    @Override
    public List<RedUser> selectRedUserList(RedUser redUser)
    {
        return redUserMapper.selectRedUserList(redUser);
    }

    /**
     * 新增登录用户信息
     * 
     * @param redUser 登录用户信息
     * @return 结果
     */
    @Override
    public int insertRedUser(RedUser redUser)
    {
        redUser.setCreateTime(DateUtils.getNowDate());
        return redUserMapper.insertRedUser(redUser);
    }

    /**
     * 修改登录用户信息
     * 
     * @param redUser 登录用户信息
     * @return 结果
     */
    @Override
    public int updateRedUser(RedUser redUser)
    {
        return redUserMapper.updateRedUser(redUser);
    }

    /**
     * 批量删除登录用户信息
     * 
     * @param userIds 需要删除的登录用户信息主键
     * @return 结果
     */
    @Override
    public int deleteRedUserByUserIds(Long[] userIds)
    {
        return redUserMapper.deleteRedUserByUserIds(userIds);
    }

    /**
     * 删除登录用户信息信息
     * 
     * @param userId 登录用户信息主键
     * @return 结果
     */
    @Override
    public int deleteRedUserByUserId(Long userId)
    {
        return redUserMapper.deleteRedUserByUserId(userId);
    }

	@Override
	public RedUser selectRedUserByUserKey(String userKey) {
		return redUserMapper.selectRedUserByUserKey(userKey);
	}
	
	@Override
    public int deleteRedUserByUserKey(String userKey)
    {
        return redUserMapper.deleteRedUserByUserKey(userKey);
    }
}

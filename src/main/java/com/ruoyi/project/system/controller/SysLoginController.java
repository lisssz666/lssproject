package com.ruoyi.project.system.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.ruoyi.common.utils.file.ImageUtils;
import com.ruoyi.project.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.security.service.SysLoginService;
import com.ruoyi.framework.security.service.SysPermissionService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysMenuService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
public class SysLoginController
{
    private static final Logger log = LoggerFactory.getLogger(SysLoginController.class);
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @RequestMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        String name = loginBody.getUsername();
        String password = loginBody.getPassword();
        log.info("======username======"+name +"======password======"+password);
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid(),loginBody.getIsApplets());
        //获取用户信息
        SysUser user = iSysUserService.selectUserByUserName(name);
        String memberVip = user.getMemberVip();
        log.info("roles ======" +memberVip);
        ajax.put(Constants.TOKEN, token);
        ajax.put("memberVip", memberVip);
        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }

}

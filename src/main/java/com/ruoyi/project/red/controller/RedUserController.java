package com.ruoyi.project.red.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.red.domain.RedUser;
import com.ruoyi.project.red.service.IRedUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 登录用户信息Controller
 * 
 * @author ruoyi
 * @date 2021-12-10
 */
@RestController
@RequestMapping("/red/user")
public class RedUserController extends BaseController
{
    @Autowired
    private IRedUserService redUserService;

    /**
     * 查询登录用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('red:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(RedUser redUser)
    {
        startPage();
        List<RedUser> list = redUserService.selectRedUserList(redUser);
        return getDataTable(list);
    }

    /**
     * 导出登录用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('red:user:export')")
    @Log(title = "登录用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RedUser redUser)
    {
        List<RedUser> list = redUserService.selectRedUserList(redUser);
        ExcelUtil<RedUser> util = new ExcelUtil<RedUser>(RedUser.class);
        util.exportExcel(response, list, "登录用户信息数据");
    }

    /**
     * 获取登录用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('red:user:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(redUserService.selectRedUserByUserId(userId));
    }

    /**
     * 新增登录用户信息
     */
    @PreAuthorize("@ss.hasPermi('red:user:add')")
    @Log(title = "登录用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RedUser redUser)
    {
        return toAjax(redUserService.insertRedUser(redUser));
    }

    /**
     * 修改登录用户信息
     */
    @PreAuthorize("@ss.hasPermi('red:user:edit')")
    @Log(title = "登录用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RedUser redUser)
    {
        return toAjax(redUserService.updateRedUser(redUser));
    }

    /**
     * 删除登录用户信息
     */
    @PreAuthorize("@ss.hasPermi('red:user:remove')")
    @Log(title = "登录用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(redUserService.deleteRedUserByUserIds(userIds));
    }
}

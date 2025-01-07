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
import com.ruoyi.project.red.domain.RedCaptcha;
import com.ruoyi.project.red.service.IRedCaptchaService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 验证码Controller
 * 
 * @author ruoyi
 * @date 2021-12-10
 */
@RestController
@RequestMapping("/red/captcha")
public class RedCaptchaController extends BaseController
{
    @Autowired
    private IRedCaptchaService redCaptchaService;

    /**
     * 查询验证码列表
     */
    @PreAuthorize("@ss.hasPermi('red:captcha:list')")
    @GetMapping("/list")
    public TableDataInfo list(RedCaptcha redCaptcha)
    {
        startPage();
        List<RedCaptcha> list = redCaptchaService.selectRedCaptchaList(redCaptcha);
        return getDataTable(list);
    }

    /**
     * 导出验证码列表
     */
    @PreAuthorize("@ss.hasPermi('red:captcha:export')")
    @Log(title = "验证码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RedCaptcha redCaptcha)
    {
        List<RedCaptcha> list = redCaptchaService.selectRedCaptchaList(redCaptcha);
        ExcelUtil<RedCaptcha> util = new ExcelUtil<RedCaptcha>(RedCaptcha.class);
        util.exportExcel(response, list, "验证码数据");
    }

    /**
     * 获取验证码详细信息
     */
    @PreAuthorize("@ss.hasPermi('red:captcha:query')")
    @GetMapping(value = "/{captchaId}")
    public AjaxResult getInfo(@PathVariable("captchaId") Long captchaId)
    {
        return AjaxResult.success(redCaptchaService.selectRedCaptchaByCaptchaId(captchaId));
    }

    /**
     * 新增验证码
     */
    @PreAuthorize("@ss.hasPermi('red:captcha:add')")
    @Log(title = "验证码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RedCaptcha redCaptcha)
    {
        return toAjax(redCaptchaService.insertRedCaptcha(redCaptcha));
    }

    /**
     * 修改验证码
     */
    @PreAuthorize("@ss.hasPermi('red:captcha:edit')")
    @Log(title = "验证码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RedCaptcha redCaptcha)
    {
        return toAjax(redCaptchaService.updateRedCaptcha(redCaptcha));
    }

    /**
     * 删除验证码
     */
    @PreAuthorize("@ss.hasPermi('red:captcha:remove')")
    @Log(title = "验证码", businessType = BusinessType.DELETE)
	@DeleteMapping("/{captchaIds}")
    public AjaxResult remove(@PathVariable Long[] captchaIds)
    {
        return toAjax(redCaptchaService.deleteRedCaptchaByCaptchaIds(captchaIds));
    }
}

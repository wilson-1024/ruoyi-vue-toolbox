package com.ruoyi.web.controller.workflow;

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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.workflow.domain.WflowProcessTemplate;
import com.ruoyi.workflow.service.IWflowProcessTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 流程模板Controller
 *
 * @author HHAOYU
 * @date 2025-04-14
 */
@RestController
@RequestMapping("/workflow/wflowProcessTemplate")
public class WflowProcessTemplateController extends BaseController
{
    @Autowired
    private IWflowProcessTemplateService wflowProcessTemplateService;

    /**
     * 查询流程模板列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowProcessTemplate:list')")
    @GetMapping("/list")
    public TableDataInfo list(WflowProcessTemplate wflowProcessTemplate)
    {
        startPage();
        List<WflowProcessTemplate> list = wflowProcessTemplateService.selectWflowProcessTemplateList(wflowProcessTemplate);
        return getDataTable(list);
    }

    /**
     * 导出流程模板列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowProcessTemplate:export')")
    @Log(title = "流程模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WflowProcessTemplate wflowProcessTemplate)
    {
        List<WflowProcessTemplate> list = wflowProcessTemplateService.selectWflowProcessTemplateList(wflowProcessTemplate);
        ExcelUtil<WflowProcessTemplate> util = new ExcelUtil<WflowProcessTemplate>(WflowProcessTemplate.class);
        util.exportExcel(response, list, "流程模板数据");
    }

    /**
     * 获取流程模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowProcessTemplate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wflowProcessTemplateService.selectWflowProcessTemplateById(id));
    }

    /**
     * 新增流程模板
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowProcessTemplate:add')")
    @Log(title = "流程模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WflowProcessTemplate wflowProcessTemplate)
    {
        return toAjax(wflowProcessTemplateService.insertWflowProcessTemplate(wflowProcessTemplate));
    }

    /**
     * 修改流程模板
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowProcessTemplate:edit')")
    @Log(title = "流程模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WflowProcessTemplate wflowProcessTemplate)
    {
        return toAjax(wflowProcessTemplateService.updateWflowProcessTemplate(wflowProcessTemplate));
    }

    /**
     * 删除流程模板
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowProcessTemplate:remove')")
    @Log(title = "流程模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wflowProcessTemplateService.deleteWflowProcessTemplateByIds(ids));
    }
}

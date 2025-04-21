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
import com.ruoyi.workflow.domain.WflowPTempDetail;
import com.ruoyi.workflow.service.IWflowPTempDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 流程模板详情Controller
 *
 * @author HHAOYU
 * @date 2025-04-14
 */
@RestController
@RequestMapping("/workflow/wflowPTempDetail")
public class WflowPTempDetailController extends BaseController
{
    @Autowired
    private IWflowPTempDetailService wflowPTempDetailService;

    /**
     * 查询流程模板详情列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowPTempDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(WflowPTempDetail wflowPTempDetail)
    {
        startPage();
        List<WflowPTempDetail> list = wflowPTempDetailService.selectWflowPTempDetailList(wflowPTempDetail);
        return getDataTable(list);
    }

    /**
     * 导出流程模板详情列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowPTempDetail:export')")
    @Log(title = "流程模板详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WflowPTempDetail wflowPTempDetail)
    {
        List<WflowPTempDetail> list = wflowPTempDetailService.selectWflowPTempDetailList(wflowPTempDetail);
        ExcelUtil<WflowPTempDetail> util = new ExcelUtil<WflowPTempDetail>(WflowPTempDetail.class);
        util.exportExcel(response, list, "流程模板详情数据");
    }

    /**
     * 获取流程模板详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowPTempDetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wflowPTempDetailService.selectWflowPTempDetailById(id));
    }

    /**
     * 新增流程模板详情
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowPTempDetail:add')")
    @Log(title = "流程模板详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WflowPTempDetail wflowPTempDetail)
    {
        return toAjax(wflowPTempDetailService.insertWflowPTempDetail(wflowPTempDetail));
    }

    /**
     * 修改流程模板详情
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowPTempDetail:edit')")
    @Log(title = "流程模板详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WflowPTempDetail wflowPTempDetail)
    {
        return toAjax(wflowPTempDetailService.updateWflowPTempDetail(wflowPTempDetail));
    }

    /**
     * 删除流程模板详情
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowPTempDetail:remove')")
    @Log(title = "流程模板详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wflowPTempDetailService.deleteWflowPTempDetailByIds(ids));
    }
}

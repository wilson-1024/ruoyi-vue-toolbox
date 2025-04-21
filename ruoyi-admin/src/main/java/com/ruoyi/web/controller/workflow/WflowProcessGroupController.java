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
import com.ruoyi.workflow.domain.WflowProcessGroup;
import com.ruoyi.workflow.service.IWflowProcessGroupService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 流程分组Controller
 *
 * @author HHAOYU
 * @date 2025-04-14
 */
@RestController
@RequestMapping("/workflow/wflowProcessGroup")
public class WflowProcessGroupController extends BaseController
{
    @Autowired
    private IWflowProcessGroupService wflowProcessGroupService;

    /**
     * 查询流程分组列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowProcessGroup:list')")
    @GetMapping("/list")
    public TableDataInfo list(WflowProcessGroup wflowProcessGroup)
    {
        startPage();
        List<WflowProcessGroup> list = wflowProcessGroupService.selectWflowProcessGroupList(wflowProcessGroup);
        return getDataTable(list);
    }

    /**
     * 导出流程分组列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowProcessGroup:export')")
    @Log(title = "流程分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WflowProcessGroup wflowProcessGroup)
    {
        List<WflowProcessGroup> list = wflowProcessGroupService.selectWflowProcessGroupList(wflowProcessGroup);
        ExcelUtil<WflowProcessGroup> util = new ExcelUtil<WflowProcessGroup>(WflowProcessGroup.class);
        util.exportExcel(response, list, "流程分组数据");
    }

    /**
     * 获取流程分组详细信息
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowProcessGroup:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wflowProcessGroupService.selectWflowProcessGroupById(id));
    }

    /**
     * 新增流程分组
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowProcessGroup:add')")
    @Log(title = "流程分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WflowProcessGroup wflowProcessGroup)
    {
        return toAjax(wflowProcessGroupService.insertWflowProcessGroup(wflowProcessGroup));
    }

    /**
     * 修改流程分组
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowProcessGroup:edit')")
    @Log(title = "流程分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WflowProcessGroup wflowProcessGroup)
    {
        return toAjax(wflowProcessGroupService.updateWflowProcessGroup(wflowProcessGroup));
    }

    /**
     * 删除流程分组
     */
    @PreAuthorize("@ss.hasPermi('workflow:wflowProcessGroup:remove')")
    @Log(title = "流程分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wflowProcessGroupService.deleteWflowProcessGroupByIds(ids));
    }
}

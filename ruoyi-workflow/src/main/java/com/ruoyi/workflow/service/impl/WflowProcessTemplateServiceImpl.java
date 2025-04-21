package com.ruoyi.workflow.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workflow.mapper.WflowProcessTemplateMapper;
import com.ruoyi.workflow.domain.WflowProcessTemplate;
import com.ruoyi.workflow.service.IWflowProcessTemplateService;

/**
 * 流程模板Service业务层处理
 * 
 * @author HHAOYU
 * @date 2025-04-14
 */
@Service
public class WflowProcessTemplateServiceImpl implements IWflowProcessTemplateService 
{
    @Autowired
    private WflowProcessTemplateMapper wflowProcessTemplateMapper;

    /**
     * 查询流程模板
     * 
     * @param id 流程模板主键
     * @return 流程模板
     */
    @Override
    public WflowProcessTemplate selectWflowProcessTemplateById(Long id)
    {
        return wflowProcessTemplateMapper.selectWflowProcessTemplateById(id);
    }

    /**
     * 查询流程模板列表
     * 
     * @param wflowProcessTemplate 流程模板
     * @return 流程模板
     */
    @Override
    public List<WflowProcessTemplate> selectWflowProcessTemplateList(WflowProcessTemplate wflowProcessTemplate)
    {
        return wflowProcessTemplateMapper.selectWflowProcessTemplateList(wflowProcessTemplate);
    }

    /**
     * 新增流程模板
     * 
     * @param wflowProcessTemplate 流程模板
     * @return 结果
     */
    @Override
    public int insertWflowProcessTemplate(WflowProcessTemplate wflowProcessTemplate)
    {
        wflowProcessTemplate.setCreateTime(DateUtils.getNowDate());
        return wflowProcessTemplateMapper.insertWflowProcessTemplate(wflowProcessTemplate);
    }

    /**
     * 修改流程模板
     * 
     * @param wflowProcessTemplate 流程模板
     * @return 结果
     */
    @Override
    public int updateWflowProcessTemplate(WflowProcessTemplate wflowProcessTemplate)
    {
        wflowProcessTemplate.setUpdateTime(DateUtils.getNowDate());
        return wflowProcessTemplateMapper.updateWflowProcessTemplate(wflowProcessTemplate);
    }

    /**
     * 批量删除流程模板
     * 
     * @param ids 需要删除的流程模板主键
     * @return 结果
     */
    @Override
    public int deleteWflowProcessTemplateByIds(Long[] ids)
    {
        return wflowProcessTemplateMapper.deleteWflowProcessTemplateByIds(ids);
    }

    /**
     * 删除流程模板信息
     * 
     * @param id 流程模板主键
     * @return 结果
     */
    @Override
    public int deleteWflowProcessTemplateById(Long id)
    {
        return wflowProcessTemplateMapper.deleteWflowProcessTemplateById(id);
    }
}

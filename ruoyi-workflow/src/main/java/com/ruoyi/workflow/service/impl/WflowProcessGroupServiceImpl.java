package com.ruoyi.workflow.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workflow.mapper.WflowProcessGroupMapper;
import com.ruoyi.workflow.domain.WflowProcessGroup;
import com.ruoyi.workflow.service.IWflowProcessGroupService;

/**
 * 流程分组Service业务层处理
 * 
 * @author HHAOYU
 * @date 2025-04-14
 */
@Service
public class WflowProcessGroupServiceImpl implements IWflowProcessGroupService 
{
    @Autowired
    private WflowProcessGroupMapper wflowProcessGroupMapper;

    /**
     * 查询流程分组
     * 
     * @param id 流程分组主键
     * @return 流程分组
     */
    @Override
    public WflowProcessGroup selectWflowProcessGroupById(Long id)
    {
        return wflowProcessGroupMapper.selectWflowProcessGroupById(id);
    }

    /**
     * 查询流程分组列表
     * 
     * @param wflowProcessGroup 流程分组
     * @return 流程分组
     */
    @Override
    public List<WflowProcessGroup> selectWflowProcessGroupList(WflowProcessGroup wflowProcessGroup)
    {
        return wflowProcessGroupMapper.selectWflowProcessGroupList(wflowProcessGroup);
    }

    /**
     * 新增流程分组
     * 
     * @param wflowProcessGroup 流程分组
     * @return 结果
     */
    @Override
    public int insertWflowProcessGroup(WflowProcessGroup wflowProcessGroup)
    {
        wflowProcessGroup.setCreateTime(DateUtils.getNowDate());
        return wflowProcessGroupMapper.insertWflowProcessGroup(wflowProcessGroup);
    }

    /**
     * 修改流程分组
     * 
     * @param wflowProcessGroup 流程分组
     * @return 结果
     */
    @Override
    public int updateWflowProcessGroup(WflowProcessGroup wflowProcessGroup)
    {
        wflowProcessGroup.setUpdateTime(DateUtils.getNowDate());
        return wflowProcessGroupMapper.updateWflowProcessGroup(wflowProcessGroup);
    }

    /**
     * 批量删除流程分组
     * 
     * @param ids 需要删除的流程分组主键
     * @return 结果
     */
    @Override
    public int deleteWflowProcessGroupByIds(Long[] ids)
    {
        return wflowProcessGroupMapper.deleteWflowProcessGroupByIds(ids);
    }

    /**
     * 删除流程分组信息
     * 
     * @param id 流程分组主键
     * @return 结果
     */
    @Override
    public int deleteWflowProcessGroupById(Long id)
    {
        return wflowProcessGroupMapper.deleteWflowProcessGroupById(id);
    }
}

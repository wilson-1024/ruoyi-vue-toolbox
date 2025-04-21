package com.ruoyi.workflow.service;

import java.util.List;
import com.ruoyi.workflow.domain.WflowProcessGroup;

/**
 * 流程分组Service接口
 * 
 * @author HHAOYU
 * @date 2025-04-14
 */
public interface IWflowProcessGroupService 
{
    /**
     * 查询流程分组
     * 
     * @param id 流程分组主键
     * @return 流程分组
     */
    public WflowProcessGroup selectWflowProcessGroupById(Long id);

    /**
     * 查询流程分组列表
     * 
     * @param wflowProcessGroup 流程分组
     * @return 流程分组集合
     */
    public List<WflowProcessGroup> selectWflowProcessGroupList(WflowProcessGroup wflowProcessGroup);

    /**
     * 新增流程分组
     * 
     * @param wflowProcessGroup 流程分组
     * @return 结果
     */
    public int insertWflowProcessGroup(WflowProcessGroup wflowProcessGroup);

    /**
     * 修改流程分组
     * 
     * @param wflowProcessGroup 流程分组
     * @return 结果
     */
    public int updateWflowProcessGroup(WflowProcessGroup wflowProcessGroup);

    /**
     * 批量删除流程分组
     * 
     * @param ids 需要删除的流程分组主键集合
     * @return 结果
     */
    public int deleteWflowProcessGroupByIds(Long[] ids);

    /**
     * 删除流程分组信息
     * 
     * @param id 流程分组主键
     * @return 结果
     */
    public int deleteWflowProcessGroupById(Long id);
}

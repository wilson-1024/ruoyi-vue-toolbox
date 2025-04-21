package com.ruoyi.workflow.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.workflow.domain.WflowProcessGroup;

/**
 * 流程分组Mapper接口
 * 
 * @author HHAOYU
 * @date 2025-04-14
 */
public interface WflowProcessGroupMapper extends BaseMapper<WflowProcessGroup>
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
     * 删除流程分组
     * 
     * @param id 流程分组主键
     * @return 结果
     */
    public int deleteWflowProcessGroupById(Long id);

    /**
     * 批量删除流程分组
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWflowProcessGroupByIds(Long[] ids);
}

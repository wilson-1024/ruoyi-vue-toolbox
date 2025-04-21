package com.ruoyi.workflow.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.workflow.domain.WflowProcessTemplate;

/**
 * 流程模板Mapper接口
 * 
 * @author HHAOYU
 * @date 2025-04-14
 */
public interface WflowProcessTemplateMapper extends BaseMapper<WflowProcessTemplate>
{
    /**
     * 查询流程模板
     * 
     * @param id 流程模板主键
     * @return 流程模板
     */
    public WflowProcessTemplate selectWflowProcessTemplateById(Long id);

    /**
     * 查询流程模板列表
     * 
     * @param wflowProcessTemplate 流程模板
     * @return 流程模板集合
     */
    public List<WflowProcessTemplate> selectWflowProcessTemplateList(WflowProcessTemplate wflowProcessTemplate);

    /**
     * 新增流程模板
     * 
     * @param wflowProcessTemplate 流程模板
     * @return 结果
     */
    public int insertWflowProcessTemplate(WflowProcessTemplate wflowProcessTemplate);

    /**
     * 修改流程模板
     * 
     * @param wflowProcessTemplate 流程模板
     * @return 结果
     */
    public int updateWflowProcessTemplate(WflowProcessTemplate wflowProcessTemplate);

    /**
     * 删除流程模板
     * 
     * @param id 流程模板主键
     * @return 结果
     */
    public int deleteWflowProcessTemplateById(Long id);

    /**
     * 批量删除流程模板
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWflowProcessTemplateByIds(Long[] ids);
}

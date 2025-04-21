package com.ruoyi.workflow.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.workflow.domain.WflowPTempDetail;

/**
 * 流程模板详情Mapper接口
 * 
 * @author HHAOYU
 * @date 2025-04-14
 */
public interface WflowPTempDetailMapper extends BaseMapper<WflowPTempDetail>
{
    /**
     * 查询流程模板详情
     * 
     * @param id 流程模板详情主键
     * @return 流程模板详情
     */
    public WflowPTempDetail selectWflowPTempDetailById(Long id);

    /**
     * 查询流程模板详情列表
     * 
     * @param wflowPTempDetail 流程模板详情
     * @return 流程模板详情集合
     */
    public List<WflowPTempDetail> selectWflowPTempDetailList(WflowPTempDetail wflowPTempDetail);

    /**
     * 新增流程模板详情
     * 
     * @param wflowPTempDetail 流程模板详情
     * @return 结果
     */
    public int insertWflowPTempDetail(WflowPTempDetail wflowPTempDetail);

    /**
     * 修改流程模板详情
     * 
     * @param wflowPTempDetail 流程模板详情
     * @return 结果
     */
    public int updateWflowPTempDetail(WflowPTempDetail wflowPTempDetail);

    /**
     * 删除流程模板详情
     * 
     * @param id 流程模板详情主键
     * @return 结果
     */
    public int deleteWflowPTempDetailById(Long id);

    /**
     * 批量删除流程模板详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWflowPTempDetailByIds(Long[] ids);
}

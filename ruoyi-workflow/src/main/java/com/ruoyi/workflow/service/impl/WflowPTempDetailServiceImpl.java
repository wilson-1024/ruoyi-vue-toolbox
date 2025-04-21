package com.ruoyi.workflow.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workflow.mapper.WflowPTempDetailMapper;
import com.ruoyi.workflow.domain.WflowPTempDetail;
import com.ruoyi.workflow.service.IWflowPTempDetailService;

/**
 * 流程模板详情Service业务层处理
 * 
 * @author HHAOYU
 * @date 2025-04-14
 */
@Service
public class WflowPTempDetailServiceImpl implements IWflowPTempDetailService 
{
    @Autowired
    private WflowPTempDetailMapper wflowPTempDetailMapper;

    /**
     * 查询流程模板详情
     * 
     * @param id 流程模板详情主键
     * @return 流程模板详情
     */
    @Override
    public WflowPTempDetail selectWflowPTempDetailById(Long id)
    {
        return wflowPTempDetailMapper.selectWflowPTempDetailById(id);
    }

    /**
     * 查询流程模板详情列表
     * 
     * @param wflowPTempDetail 流程模板详情
     * @return 流程模板详情
     */
    @Override
    public List<WflowPTempDetail> selectWflowPTempDetailList(WflowPTempDetail wflowPTempDetail)
    {
        return wflowPTempDetailMapper.selectWflowPTempDetailList(wflowPTempDetail);
    }

    /**
     * 新增流程模板详情
     * 
     * @param wflowPTempDetail 流程模板详情
     * @return 结果
     */
    @Override
    public int insertWflowPTempDetail(WflowPTempDetail wflowPTempDetail)
    {
        wflowPTempDetail.setCreateTime(DateUtils.getNowDate());
        return wflowPTempDetailMapper.insertWflowPTempDetail(wflowPTempDetail);
    }

    /**
     * 修改流程模板详情
     * 
     * @param wflowPTempDetail 流程模板详情
     * @return 结果
     */
    @Override
    public int updateWflowPTempDetail(WflowPTempDetail wflowPTempDetail)
    {
        wflowPTempDetail.setUpdateTime(DateUtils.getNowDate());
        return wflowPTempDetailMapper.updateWflowPTempDetail(wflowPTempDetail);
    }

    /**
     * 批量删除流程模板详情
     * 
     * @param ids 需要删除的流程模板详情主键
     * @return 结果
     */
    @Override
    public int deleteWflowPTempDetailByIds(Long[] ids)
    {
        return wflowPTempDetailMapper.deleteWflowPTempDetailByIds(ids);
    }

    /**
     * 删除流程模板详情信息
     * 
     * @param id 流程模板详情主键
     * @return 结果
     */
    @Override
    public int deleteWflowPTempDetailById(Long id)
    {
        return wflowPTempDetailMapper.deleteWflowPTempDetailById(id);
    }
}

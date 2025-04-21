package com.ruoyi.workflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 流程模板详情对象 wflow_p_temp_detail
 * 
 * @author HHAOYU
 * @date 2025-04-14
 */
@TableName("wflow_p_temp_detail")
public class WflowPTempDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId(value = "id")
    private Long id;
    /** 流程模板ID */
    @Excel(name = "流程模板ID")

    private Long processTempId;
    /** 流程版本 */
    @Excel(name = "流程版本")

    private Long processVersion;
    /** 流程ID */
    @Excel(name = "流程ID")

    private Long processId;
    /** 模型内容 */
    @Excel(name = "模型内容")

    private String modelContent;
    /** 流程表单 */
    @Excel(name = "流程表单")

    private String processForm;
    /** $column.columnComment */

    private String delFlag;
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProcessTempId(Long processTempId) 
    {
        this.processTempId = processTempId;
    }

    public Long getProcessTempId() 
    {
        return processTempId;
    }
    public void setProcessVersion(Long processVersion) 
    {
        this.processVersion = processVersion;
    }

    public Long getProcessVersion() 
    {
        return processVersion;
    }
    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }
    public void setModelContent(String modelContent) 
    {
        this.modelContent = modelContent;
    }

    public String getModelContent() 
    {
        return modelContent;
    }
    public void setProcessForm(String processForm) 
    {
        this.processForm = processForm;
    }

    public String getProcessForm() 
    {
        return processForm;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("processTempId", getProcessTempId())
            .append("processVersion", getProcessVersion())
            .append("processId", getProcessId())
            .append("modelContent", getModelContent())
            .append("processForm", getProcessForm())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}

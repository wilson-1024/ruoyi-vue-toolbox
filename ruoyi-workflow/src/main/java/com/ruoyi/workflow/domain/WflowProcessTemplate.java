package com.ruoyi.workflow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 流程模板对象 wflow_process_template
 * 
 * @author HHAOYU
 * @date 2025-04-14
 */
@TableName("wflow_process_template")
public class WflowProcessTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId(value = "id")
    private Long id;
    /** 模版key */
    @Excel(name = "模版key")

    private String processKey;
    /** 模版名称 */
    @Excel(name = "模版名称")

    private String processName;
    /** 图标 */
    @Excel(name = "图标")

    private String processIcon;
    /** 排序 */
    @Excel(name = "排序")

    private Long processSort;
    /** 模板状态 */
    @Excel(name = "模板状态")

    private String processState;
    /** 模版类型 */
    @Excel(name = "模版类型")

    private String processType;
    /** 激活版本 */
    @Excel(name = "激活版本")

    private Long activeVersion;
    /** 当前最大版本号 */
    @Excel(name = "当前最大版本号")

    private Long nowVersion;
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
    public void setProcessKey(String processKey) 
    {
        this.processKey = processKey;
    }

    public String getProcessKey() 
    {
        return processKey;
    }
    public void setProcessName(String processName) 
    {
        this.processName = processName;
    }

    public String getProcessName() 
    {
        return processName;
    }
    public void setProcessIcon(String processIcon) 
    {
        this.processIcon = processIcon;
    }

    public String getProcessIcon() 
    {
        return processIcon;
    }
    public void setProcessSort(Long processSort) 
    {
        this.processSort = processSort;
    }

    public Long getProcessSort() 
    {
        return processSort;
    }
    public void setProcessState(String processState) 
    {
        this.processState = processState;
    }

    public String getProcessState() 
    {
        return processState;
    }
    public void setProcessType(String processType) 
    {
        this.processType = processType;
    }

    public String getProcessType() 
    {
        return processType;
    }
    public void setActiveVersion(Long activeVersion) 
    {
        this.activeVersion = activeVersion;
    }

    public Long getActiveVersion() 
    {
        return activeVersion;
    }
    public void setNowVersion(Long nowVersion) 
    {
        this.nowVersion = nowVersion;
    }

    public Long getNowVersion() 
    {
        return nowVersion;
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
            .append("processKey", getProcessKey())
            .append("processName", getProcessName())
            .append("processIcon", getProcessIcon())
            .append("processSort", getProcessSort())
            .append("processState", getProcessState())
            .append("processType", getProcessType())
            .append("activeVersion", getActiveVersion())
            .append("nowVersion", getNowVersion())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}

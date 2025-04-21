<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="模版key" prop="processKey">
        <el-input
          v-model="queryParams.processKey"
          placeholder="请输入模版key"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="模版名称" prop="processName">
        <el-input
          v-model="queryParams.processName"
          placeholder="请输入模版名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序" prop="processSort">
        <el-input
          v-model="queryParams.processSort"
          placeholder="请输入排序"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="模板状态" prop="processState">
        <el-select v-model="queryParams.processState" placeholder="请选择模板状态" clearable>
          <el-option label="启用" value="1" />
          <el-option label="禁用" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="激活版本" prop="activeVersion">
        <el-input
          v-model="queryParams.activeVersion"
          placeholder="请输入激活版本"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['workflow:wflowProcessTemplate:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['workflow:wflowProcessTemplate:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['workflow:wflowProcessTemplate:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['workflow:wflowProcessTemplate:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wflowProcessTemplateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="模版key" align="center" prop="processKey" />
      <el-table-column label="模版名称" align="center" prop="processName" />
      <el-table-column label="图标" align="center" prop="processIcon" />
      <el-table-column label="排序" align="center" prop="processSort" />
      <el-table-column label="模板状态" align="center" prop="processState" />
      <el-table-column label="模版类型" align="center" prop="processType" />
      <el-table-column label="激活版本" align="center" prop="activeVersion" />
      <el-table-column label="当前最大版本号" align="center" prop="nowVersion" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            type="text"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['workflow:wflowProcessTemplate:edit']"
          >修改</el-button>
          <el-button
            type="text"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['workflow:wflowProcessTemplate:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改流程模板对话框 -->
    <ProcessTemplateForm
      v-model="open"
      :title="title"
      :form-data="form.value"
      @submit="submitForm"
      @cancel="cancel"
    />
  </div>
</template>

<script setup name="WflowProcessTemplate">
import { listWflowProcessTemplate, getWflowProcessTemplate, delWflowProcessTemplate, addWflowProcessTemplate, updateWflowProcessTemplate } from "@/api/workflow/wflowProcessTemplate";
import ProcessTemplateForm from './components/ProcessTemplateForm.vue';

const { proxy } = getCurrentInstance();

const wflowProcessTemplateList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    processKey: null,
    processName: null,
    processSort: null,
    processState: null,
    processType: null,
    activeVersion: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询流程模板列表 */
function getList() {
  loading.value = true;
  listWflowProcessTemplate(queryParams.value).then(response => {
    wflowProcessTemplateList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    processKey: null,
    processName: null,
    processIcon: null,
    processSort: null,
    processState: null,
    processType: null,
    activeVersion: null,
    nowVersion: null,
    remark: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    delFlag: null
  };
  proxy.resetForm("wflowProcessTemplateRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加流程模板";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const id = row.id || ids.value
  getWflowProcessTemplate(id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改流程模板";
  });
}

/** 提交按钮 */
function submitForm(formData) {
  if (formData.id != null) {
    updateWflowProcessTemplate(formData).then(response => {
      proxy.$modal.msgSuccess("修改成功");
      open.value = false;
      getList();
    });
  } else {
    addWflowProcessTemplate(formData).then(response => {
      proxy.$modal.msgSuccess("新增成功");
      open.value = false;
      getList();
    });
  }
}

/** 删除按钮操作 */
function handleDelete(row) {
  const ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除流程模板编号为"' + ids + '"的数据项？').then(function() {
    return delWflowProcessTemplate(ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('workflow/wflowProcessTemplate/export', {
    ...queryParams.value
  }, `wflowProcessTemplate_${new Date().getTime()}.xlsx`)
}

getList();
</script>

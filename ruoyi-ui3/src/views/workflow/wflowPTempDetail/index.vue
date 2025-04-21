<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="流程模板ID" prop="processTempId">
        <el-input
          v-model="queryParams.processTempId"
          placeholder="请输入流程模板ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="流程版本" prop="processVersion">
        <el-input
          v-model="queryParams.processVersion"
          placeholder="请输入流程版本"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="流程ID" prop="processId">
        <el-input
          v-model="queryParams.processId"
          placeholder="请输入流程ID"
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
          v-hasPermi="['workflow:wflowPTempDetail:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['workflow:wflowPTempDetail:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['workflow:wflowPTempDetail:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['workflow:wflowPTempDetail:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wflowPTempDetailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="流程模板ID" align="center" prop="processTempId" />
      <el-table-column label="流程版本" align="center" prop="processVersion" />
      <el-table-column label="流程ID" align="center" prop="processId" />
      <el-table-column label="模型内容" align="center" prop="modelContent" />
      <el-table-column label="流程表单" align="center" prop="processForm" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            type="text"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['workflow:wflowPTempDetail:edit']"
          >修改</el-button>
          <el-button
            type="text"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['workflow:wflowPTempDetail:remove']"
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

    <!-- 添加或修改流程模板详情对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="wflowPTempDetailRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="流程模板ID" prop="processTempId">
          <el-input v-model="form.processTempId" placeholder="请输入流程模板ID" />
        </el-form-item>
        <el-form-item label="流程版本" prop="processVersion">
          <el-input v-model="form.processVersion" placeholder="请输入流程版本" />
        </el-form-item>
        <el-form-item label="流程ID" prop="processId">
          <el-input v-model="form.processId" placeholder="请输入流程ID" />
        </el-form-item>
        <el-form-item label="模型内容">
          <editor v-model="form.modelContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="流程表单" prop="processForm">
          <el-input v-model="form.processForm" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="${comment}" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入${comment}" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="WflowPTempDetail">
import { listWflowPTempDetail, getWflowPTempDetail, delWflowPTempDetail, addWflowPTempDetail, updateWflowPTempDetail } from "@/api/workflow/wflowPTempDetail";

const { proxy } = getCurrentInstance();

const wflowPTempDetailList = ref([]);
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
    processTempId: null,
    processVersion: null,
    processId: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询流程模板详情列表 */
function getList() {
  loading.value = true;
  listWflowPTempDetail(queryParams.value).then(response => {
    wflowPTempDetailList.value = response.rows;
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
    processTempId: null,
    processVersion: null,
    processId: null,
    modelContent: null,
    processForm: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    delFlag: null
  };
  proxy.resetForm("wflowPTempDetailRef");
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
  title.value = "添加流程模板详情";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const id = row.id || ids.value
  getWflowPTempDetail(id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改流程模板详情";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["wflowPTempDetailRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateWflowPTempDetail(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addWflowPTempDetail(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除流程模板详情编号为"' + ids + '"的数据项？').then(function() {
    return delWflowPTempDetail(ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('workflow/wflowPTempDetail/export', {
    ...queryParams.value
  }, `wflowPTempDetail_${new Date().getTime()}.xlsx`)
}

getList();
</script>

<template>
  <el-dialog :title="title" v-model="open" width="800px" append-to-body>
    <el-tabs v-model="activeTab" class="mb-4" @tab-click="handleTabClick">
      <el-tab-pane label="基础信息" name="0" />
      <el-tab-pane label="表单设计" name="1" />
      <el-tab-pane label="流程设计" name="2" />
      <el-tab-pane label="扩展设置" name="3" />
    </el-tabs>
    
    <el-form ref="processTemplateFormRef" :model="form" :rules="rules" label-width="100px">
      <!-- 基础信息 -->
      <div v-show="activeTab === '0'">

        <el-form-item label="模版key" prop="processKey">
          <el-input v-model="form.processKey" placeholder="请输入模版key" />
        </el-form-item>
        <el-form-item label="模版名称" prop="processName">
          <el-input v-model="form.processName" placeholder="请输入模版名称" />
        </el-form-item>
        <el-form-item label="图标" prop="processIcon">
          <el-input v-model="form.processIcon" placeholder="请输入图标" />
        </el-form-item>
        <el-form-item label="排序" prop="processSort">
          <el-input v-model="form.processSort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </div>

      <!-- 步骤2：表单设计 -->
      <div v-show="activeTab === '1'">
        <div class="form-designer-container">
          <!-- 这里可以集成表单设计器组件 -->
          <el-empty description="表单设计器待集成" />
        </div>
      </div>

      <!-- 步骤3：流程设计 -->
      <div v-show="activeTab === '2'">
        <div class="process-designer-container">
          <!-- 这里可以集成流程设计器组件 -->
          <el-empty description="流程设计器待集成" />
        </div>
      </div>

      <!-- 步骤4：扩展设置 -->
      <div v-show="activeTab === '3'">
        <el-form-item label="模板状态" prop="processState">
          <el-select v-model="form.processState" placeholder="请选择模板状态">
            <el-option label="启用" value="1" />
            <el-option label="禁用" value="0" />
          </el-select>
        </el-form-item>
        <el-form-item label="激活版本" prop="activeVersion">
          <el-input v-model="form.activeVersion" placeholder="请输入激活版本" />
        </el-form-item>
        <el-form-item label="当前版本号" prop="nowVersion">
          <el-input v-model="form.nowVersion" placeholder="请输入当前版本号" />
        </el-form-item>
      </div>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleCancel">取 消</el-button>
        <el-button type="primary" @click="handleSubmit">确 定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup name="ProcessTemplateForm">


const emit = defineEmits(['update:modelValue', 'submit', 'cancel']);

const props = defineProps({
  open: {
    type: Boolean,
    default: false
  },
  title: {
    type: String,
    default: ''
  },
  formData: {
    type: Object,
    default: () => ({})
  }
});

const processTemplateFormRef = ref(null);
const activeTab = ref('0');

const open = ref(props.open);

const form = reactive({
  id: null,
  processKey: null,
  processName: null,
  processIcon: null,
  processSort: null,
  processState: null,
  processType: null,
  activeVersion: null,
  nowVersion: null,
  remark: null
});

const rules = reactive({
  processKey: [
    { required: true, message: '请输入模版key', trigger: 'blur' }
  ],
  processName: [
    { required: true, message: '请输入模版名称', trigger: 'blur' }
  ],
  processState: [
    { required: true, message: '请选择模板状态', trigger: 'change' }
  ]
});

// 取消按钮
const handleCancel = () => {
  resetForm();
  emit('cancel');
};

// 表单重置
const resetForm = () => {
  processTemplateFormRef.value?.resetFields();
  activeTab.value = '0';
  Object.assign(form, {
    id: null,
    processKey: null,
    processName: null,
    processIcon: null,
    processSort: null,
    processState: null,
    processType: null,
    activeVersion: null,
    nowVersion: null,
    remark: null
  });
};

// 点击标签页
const handleTabClick = (tab) => {
  const newTab = tab.props.name;
  if (newTab === activeTab.value) return;
  
  // 如果是从基础信息标签页切换出去，需要验证表单
  if (activeTab.value === '0') {
    processTemplateFormRef.value?.validate((valid) => {
      if (valid) {
        activeTab.value = newTab;
      }
    });
  } else {
    activeTab.value = newTab;
  }
};

// 提交按钮
const handleSubmit = () => {
  processTemplateFormRef.value?.validate((valid) => {
    if (valid) {
      emit('submit', form);
    }
  });
};
</script>

<style scoped>
.form-designer-container,
.process-designer-container {
  min-height: 300px;
  border: 1px dashed #ccc;
  border-radius: 4px;
  padding: 20px;
}
</style>
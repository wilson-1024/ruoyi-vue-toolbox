<template>
<div>
  <div class="mb8 flex-center">
    <el-button type="primary" icon="Save" @click="handleSave">保存</el-button>
  </div>
  <er-form-editor
    :lang="lang"
    @listener="handleListener"
    ref="EReditorRef"
  />
</div>
</template>
<script setup>
import { ref } from 'vue'
import { erFormEditor } from 'everright-formeditor'
import 'Everright-formEditor/dist/style.css'
import { ElMessage } from 'element-plus'

const lang = ref('zh-cn')
const EReditorRef = ref(null)

const handleListener = async ({ type, data }) => {
  switch (type) {
    case 'lang':
      lang.value = data
      break
  }
}

const handleSave = () => {
  if (EReditorRef.value) {
    const formData = EReditorRef.value.getData()
    console.log('表单数据：', formData)
    ElMessage.success('保存成功')
  }
}
</script>
<style>
.el-aside {
  padding: 0;
}
.mb8 {
  margin-bottom: 8px;
}
.flex-center {
  display: flex;
  justify-content: center;
}
</style>
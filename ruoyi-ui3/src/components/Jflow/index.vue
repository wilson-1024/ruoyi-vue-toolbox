<template>
    <el-affix
      :offset="16"
      style="height: 74px; width: 100%">
        <el-row >
          <el-col :span="2" :offset="17">
            <el-button
              type="primary"
              @click="() => (drawer = true)">
              查看 JSON
            </el-button>
          </el-col>
          <el-col :span="2">
            <div class="slider">
              <el-button
                icon="Minus" circle
                style="margin-right: 16px; width: 32px"
                @click="minusZoom" />
                <span>
                  {{(zoom * 100).toFixed(0)}}%
                </span>
               
              <el-button
                icon="Plus" circle
                style="margin-left: 16px; width: 32px"
                @click="addZoom" />
            </div>
          </el-col>
        </el-row>
    </el-affix>
    <div
      class="affix-container"
      :style="`transform: scale(${zoom})`"
     >
      <sc-workflow
        class="workflow"
        ref="workflowRef"
        id="content-to-capture"
        v-model="modelContent" />
      <el-dialog
        v-model="drawer"
        title="查看 JSON"
        width="50%"
        :append-to-body="true"
        :close-on-click-modal="false"
        :close-on-press-escape="false">
        <div class="dialog-content">
          <div class="button-group">
            <el-button
              type="primary"
              @click="copyParseJson">
              复制格式化后的 JSON
            </el-button>
          </div>
          <json-editor-vue
            class="editor"
            language="zh-CN"
            current-mode="view"
            v-model="modelContent" />
        </div>
      </el-dialog>
    </div>
  </template>
  <script setup>
  import useClipboard from 'vue-clipboard3'
  import scWorkflow from '@/components/Jflow/scWorkflow/index.vue'
  import 'jsoneditor'
  import JsonEditorVue from 'json-editor-vue3'
  import { provideFlowConfig } from '@/composables/useFlowConfig'

  const zoom = ref(1)
  const drawer = ref(false)
  
  const props = defineProps({
    processData: { type: Object, default: () => ({}) }
  })
  
  const { modelContent, updateModelContent } = provideFlowConfig(props.processData)

  // 监听processData的变化
  watch(() => props.processData, (val) => {
    updateModelContent(val)
  })

  // 向父组件发送更新事件
  const emit = defineEmits(['update:modelValue'])
  watch(() => modelContent.value, (val) => {
    emit('update:modelValue', val)
  })
  
  const { toClipboard } = useClipboard()
  
  const copyParseJson = async () => {
    await toClipboard(JSON.stringify(modelContent.value, null, '  '))
  }
  const addZoom = () => {
    if(zoom.value < 1.1){
      zoom.value += 0.1
      zoom.value = parseFloat(zoom.value.toFixed(1))
    }
  }

  const minusZoom = () => {
    zoom.value -= 0.1
    zoom.value = parseFloat(zoom.value.toFixed(1))
  }
  
  
  onMounted(() => {
    //document.getElementById('app').onwheel = (e) => handleWeel(e)
  })
  </script>
  
  <style scoped>
  body {
    margin: 0;
    background-color: #efefef;
  }
  
  .affix-container {
    display: flex;
    justify-content: center;
    flex-direction: row-reverse;
  }
  
  .editor {
    height: 600px;
  }
  
  .editor .jsoneditor-poweredBy,
  .editor .jsoneditor-transform,
  .editor .jsoneditor-repair,
  .editor .full-screen {
    display: none !important;
  }
  
  .workflow {
    padding: 10px;
  }
  
  .jsoneditor-menu > button.jsoneditor-copy {
    background-position: -48px 0px;
  }
  
  
  .dialog-content {
    padding: 20px;
  }
  
  .button-group {
    margin-bottom: 20px;
    display: flex;
    gap: 10px;
  }
  
  
  
  .slider {
    margin-left: 16px;
    width: 300px;
    display: flex;
  }
  </style>
  
import { ref, provide, inject } from 'vue'

const FLOW_CONFIG_KEY = Symbol('flowConfig')

export function provideFlowConfig(initialData = {}) {
  const form = ref({
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
  })

  const modelContent = ref(initialData.modelContent || {})
  const processForm = ref(initialData.processForm || {})
  const activeTab = ref('0')

  const updateForm = (newValue) => {
    Object.assign(form.value, newValue)
  }

  const updateModelContent = (newValue) => {
    modelContent.value = newValue
  }

  const updateProcessForm = (newValue) => {
    processForm.value = newValue
  }

  const updateActiveTab = (newValue) => {
    activeTab.value = newValue
  }

  const resetForm = () => {
    Object.assign(form.value, {
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
    })
    activeTab.value = '0'
  }

  provide(FLOW_CONFIG_KEY, {
    form,
    modelContent,
    processForm,
    activeTab,
    updateForm,
    updateModelContent,
    updateProcessForm,
    updateActiveTab,
    resetForm
  })

  return {
    form,
    modelContent,
    processForm,
    activeTab,
    updateForm,
    updateModelContent,
    updateProcessForm,
    updateActiveTab,
    resetForm
  }
}

export function useFlowConfig() {
  const context = inject(FLOW_CONFIG_KEY)
  if (!context) {
    throw new Error('useFlowConfig must be used within a component that has called provideFlowConfig')
  }
  return context
}
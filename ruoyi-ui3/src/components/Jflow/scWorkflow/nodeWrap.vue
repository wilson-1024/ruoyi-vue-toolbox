<template>
  <promoter v-if="nodeConfig.type==0" v-model="nodeConfig"></promoter>

  <approver v-if="nodeConfig.type==1" v-model="nodeConfig"></approver>

  <send v-if="nodeConfig.type==2" v-model="nodeConfig"></send>

  <branch v-if="nodeConfig.type==4" v-model="nodeConfig">
    <template v-slot="slot">
      <node-wrap v-if="slot.node" v-model="slot.node.childNode"></node-wrap>
    </template>
  </branch>

  <node-wrap v-if="nodeConfig.childNode" v-model="nodeConfig.childNode"></node-wrap>
</template>

<script setup>
import { ref, watch } from 'vue'
import approver from './nodes/approver.vue'
import promoter from './nodes/promoter.vue'
import branch from './nodes/branch.vue'
import send from './nodes/send.vue'

const props = defineProps({
  modelValue: { type: Object, default: () => ({}) }
})

const emit = defineEmits(['update:modelValue'])

const nodeConfig = ref(props.modelValue)

watch(() => props.modelValue, (val) => {
  nodeConfig.value = val
})

watch(() => nodeConfig.value, (val) => {
  emit('update:modelValue', val)
})
</script>

<style>
</style>

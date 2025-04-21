<template>
	<div class="node-wrap">
		<div class="node-wrap-box start-node" @click="show">
			<div class="title" style="background: #576a95;">
				<el-icon class="icon"><user-filled /></el-icon>
				<span>{{ nodeConfig.nodeName }}</span>
			</div>
			<div class="content">
				<span>{{ toText(nodeConfig) }}</span>
			</div>
		</div>
		<add-node v-model="nodeConfig.childNode"></add-node>
		<el-drawer title="发起人" v-model="drawer" destroy-on-close append-to-body :size="500">
			<template #header>
				<div class="node-wrap-drawer__title">
					<label @click="editTitle" v-if="!isEditTitle">{{form.nodeName}}<el-icon class="node-wrap-drawer__title-edit"><edit /></el-icon></label>
					<el-input v-if="isEditTitle" ref="nodeTitle" v-model="form.nodeName" clearable @blur="saveTitle" @keyup.enter="saveTitle"></el-input>
				</div>
			</template>
			<el-container>
				<el-main style="padding:0 20px 20px 20px">
					<el-form label-position="top">
						<el-form-item label="谁可以发起此审批">
							<el-button type="primary" icon="el-icon-plus" round @click="selectHandle(2, form.nodeAssigneeList)">选择角色</el-button>
							<div class="tags-list">
								<el-tag v-for="(role, index) in form.nodeAssigneeList" :key="role.id" type="info" closable @close="delRole(index)">{{role.name}}</el-tag>
							</div>
						</el-form-item>
						<el-alert v-if="form.nodeAssigneeList.length==0" title="不指定则默认所有人都可发起此审批" type="info" :closable="false"/>
					</el-form>
				</el-main>
				<el-footer>
					<el-button type="primary" @click="save">保存</el-button>
					<el-button @click="drawer=false">取消</el-button>
				</el-footer>
			</el-container>
		</el-drawer>
	</div>
</template>

<script setup>
import { ref, nextTick, watch } from 'vue'
import addNode from './addNode.vue'

const props = defineProps({
  modelValue: { type: Object, default: () => ({}) },
  onSelect: { type: Function, required: true }
})

const emit = defineEmits(['update:modelValue'])

const nodeConfig = ref(props.modelValue)
const drawer = ref(false)
const isEditTitle = ref(false)
const form = ref({})
const nodeTitle = ref(null)

watch(() => props.modelValue, (val) => {
  nodeConfig.value = val
})

const show = () => {
	form.value = {}
	form.value = JSON.parse(JSON.stringify(nodeConfig.value))
	isEditTitle.value = false
	drawer.value = true
}

const editTitle = () => {
	isEditTitle.value = true
	nextTick(() => {
		nodeTitle.value.focus()
	})
}

const saveTitle = () => {
	isEditTitle.value = false
}

const selectHandle = (type, data) => {
	props.onSelect(type, data)
}

const delRole = (index) => {
	form.value.nodeAssigneeList.splice(index, 1)
}

const save = () => {
	emit('update:modelValue', form.value)
	drawer.value = false
}

const toText = (nodeConfig) => {
	if(nodeConfig.nodeAssigneeList && nodeConfig.nodeAssigneeList.length > 0){
		return nodeConfig.nodeAssigneeList.map(item=>item.name).join("、")
	}else{
		return "所有人"
	}
}


</script>

<style>
</style>

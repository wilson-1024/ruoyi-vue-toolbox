<template>
	<el-dialog v-model="dialogVisible" :title="titleMap[type-1]" :width="type==1?680:460" destroy-on-close append-to-body @closed="$emit('closed')">
		<template v-if="type==1">
			<div class="sc-user-select">
				<div class="sc-user-select__left">
					<div class="sc-user-select__search">
						<el-input v-model="keyword" prefix-icon="search" placeholder="搜索成员">
							<template #append>
								<el-button icon="search" @click="search"></el-button>
							</template>
						</el-input>
					</div>
					<div class="sc-user-select__select">
						<div class="sc-user-select__tree" v-loading="showGrouploading">
							<el-scrollbar>
								<el-tree class="menu" ref="groupTree" :data="group" :node-key="groupProps.key" :props="groupProps" highlight-current :expand-on-click-node="false" :current-node-key="groupId" @node-click="groupClick"/>
							</el-scrollbar>
						</div>
						<div class="sc-user-select__user" v-loading="showUserloading">
							<div class="sc-user-select__user__list">
								<el-scrollbar ref="userScrollbar">
									<el-tree class="menu" ref="userTree" :data="user" :node-key="userProps.key" :props="userProps" :default-checked-keys="selectedIds" show-checkbox check-on-click-node @check-change="userClick"></el-tree>
								</el-scrollbar>
							</div>
							<footer>
								<el-pagination background layout="prev,next" small :total="total" :page-size="pageSize" v-model:currentPage="currentPage" @current-change="paginationChange"></el-pagination>
							</footer>
						</div>
					</div>
				</div>
				<div class="sc-user-select__toicon"><el-icon><arrow-right /></el-icon></div>
				<div class="sc-user-select__selected">
					<header>已选 ({{selected.length}})</header>
					<ul>
						<el-scrollbar>
							<li v-for="(item, index) in selected" :key="item.id">
								<span class="name">
									<el-avatar size="small">{{item.name.substring(0,1)}}</el-avatar>
									<label>{{item.name}}</label>
								</span>
								<span class="delete">
									<el-button type="danger" icon="delete" circle size="small" @click="deleteSelected(index)"></el-button>
								</span>
							</li>
						</el-scrollbar>
					</ul>
				</div>
			</div>
		</template>

		<template v-if="type==2">
			<div class="sc-user-select sc-user-select-role">
				<div class="sc-user-select__left">
					<div class="sc-user-select__select">
						<div class="sc-user-select__tree" v-loading="showGrouploading">
							<el-scrollbar>
								<el-tree class="menu" ref="groupTree" :data="role" :node-key="roleProps.key" :props="roleProps" show-checkbox check-strictly check-on-click-node :expand-on-click-node="false" :default-checked-keys="selectedIds" @check-change="roleClick"/>
							</el-scrollbar>
						</div>
					</div>
				</div>
				<div class="sc-user-select__toicon"><el-icon><arrow-right /></el-icon></div>
				<div class="sc-user-select__selected">
					<header>已选 ({{selected.length}})</header>
					<ul>
						<el-scrollbar>
							<li v-for="(item, index) in selected" :key="item.id">
								<span class="name">
									<label>{{item.name}}</label>
								</span>
								<span class="delete">
									<el-button type="danger" icon="delete" circle size="small" @click="deleteSelected(index)"></el-button>
								</span>
							</li>
						</el-scrollbar>
					</ul>
				</div>
			</div>
		</template>

		<template #footer>
			<el-button @click="dialogVisible = false">取 消</el-button>
			<el-button type="primary" @click="save">确 认</el-button>
		</template>
	</el-dialog>
</template>

<script setup>
import { ref, computed } from 'vue'
import { deptTreeSelect, listUser } from "@/api/system/user"
import { listRole } from "@/api/system/role";


const props = defineProps({
	modelValue: { type: Boolean, default: false }
})

const emit = defineEmits(['closed'])

const groupProps = ref({
      key: 'id',
      label: 'label',
      children: 'children'
    })
const userProps = ref({
      key: 'userId',
      label: 'nickName'
    })
const roleProps = ref({
      key: 'roleId',
      label: 'roleName'
    })

const titleMap = ref(['人员选择', '角色选择'])
const dialogVisible = ref(false)
const showGrouploading = ref(false)
const showUserloading = ref(false)
const keyword = ref('')
const groupId = ref('')
const pageSize = ref(20)
const total = ref(0)
const currentPage = ref(1)
const group = ref([])
const user = ref([])
const role = ref([])
const type = ref(1)
const selected = ref([])
const value = ref([])

const groupTree = ref(null)
const userTree = ref(null)
const userScrollbar = ref(null)

const selectedIds = computed(() => {
	return selected.value.map(t => t.id)
})

// 获取组织
const getGroup = async () => {
	showGrouploading.value = true
	try {
		const res = await deptTreeSelect()
		group.value = res.data
	} catch (error) {
		console.error('获取组织失败:', error)
	}
	showGrouploading.value = false
}

// 获取用户
const getUser = async () => {
	showUserloading.value = true
	try {
		const params = {
			"nickName": keyword.value || null,
			"deptId": groupId.value || null,
			"pageNum": currentPage.value,
			"pageSize": pageSize.value
		}
		const res = await listUser(params)
		user.value = res.rows
		total.value = res.total || 0
		userScrollbar.value.setScrollTop(0)
	} catch (error) {
		console.error('获取用户失败:', error)
	}
	showUserloading.value = false
}

// 获取角色
const getRole = async () => {
	showGrouploading.value = true
	try {
		const res = await listRole()
		role.value = res.rows
	} catch (error) {
		console.error('获取角色失败:', error)
	}
	showGrouploading.value = false
}

// 组织点击
const groupClick = (data) => {
	keyword.value = ''
	currentPage.value = 1
	groupId.value = data['id']
	getUser()
}

// 用户点击
const userClick = (data, checked) => {
	if(checked) {
		selected.value.push({
			id: data['userId'],
			name: data['nickName']
		})
	} else {
		selected.value = selected.value.filter(item => item.id != data['userId'])
	}
}

// 用户分页点击
const paginationChange = () => {
	getUser()
}

// 用户搜索
const search = () => {
	groupId.value = ''
	groupTree.value.setCurrentKey(groupId.value)
	currentPage.value = 1
	getUser()
}

// 删除已选
const deleteSelected = (index) => {
	selected.value.splice(index, 1)
	if(type.value == 1) {
		userTree.value.setCheckedKeys(selectedIds.value)
	} else if(type.value == 2) {
		groupTree.value.setCheckedKeys(selectedIds.value)
	}
}

// 角色点击
const roleClick = (data, checked) => {
	if(checked) {
		selected.value.push({
			id: data['roleId'],
			name: data['roleName']
		})
	} else {
		selected.value = selected.value.filter(item => item.id != data['roleId'])
	}
}

// 提交保存
const save = () => {
	value.value.splice(0, value.value.length)
	selected.value.forEach(item => {
		value.value.push(item)
	})
	dialogVisible.value = false
}

// 打开赋值
const open = (typeVal, data) => {
	type.value = typeVal
	value.value = data || []
	selected.value = JSON.parse(JSON.stringify(data || []))
	dialogVisible.value = true

	if(type.value == 1) {
		getGroup()
		getUser()
	} else if(type.value == 2) {
		getRole()
	}
}

defineExpose({
	open
})
</script>

<style scoped>
.sc-user-select {display: flex;}
.sc-user-select__left {width: 400px;}
.sc-user-select__right {flex: 1;}

.sc-user-select__search {padding-bottom:10px;}

.sc-user-select__select {display: flex;border: 1px solid var(--el-border-color-light);background: var(--el-color-white);}
.sc-user-select__tree {width: 200px;height:300px;border-right: 1px solid var(--el-border-color-light);}
.sc-user-select__user {width: 200px;height:300px;display: flex;flex-direction: column;}
.sc-user-select__user__list {flex: 1;overflow: auto;}
.sc-user-select__user footer {height:36px;padding-top:5px;border-top: 1px solid var(--el-border-color-light);}

.sc-user-select__toicon {display: flex;justify-content: center;align-items: center;margin:0 10px;}
.sc-user-select__toicon i {display: flex;justify-content: center;align-items: center;background: #ccc;width: 20px;height: 20px;text-align: center;line-height: 20px;border-radius:50%;color: #fff;}

.sc-user-select__selected {height:345px;width: 200px;border: 1px solid var(--el-border-color-light);background: var(--el-color-white);}
.sc-user-select__selected header {height:43px;line-height: 43px;border-bottom: 1px solid var(--el-border-color-light);padding:0 15px;font-size: 12px;}
.sc-user-select__selected ul {height:300px;overflow: auto;}
.sc-user-select__selected li {display: flex;align-items: center;justify-content: space-between;padding:5px 5px 5px 15px;height:38px;}
.sc-user-select__selected li .name {display: flex;align-items: center;}
.sc-user-select__selected li .name .el-avatar {background: #409eff;margin-right: 10px;}
.sc-user-select__selected li .name label {}
.sc-user-select__selected li .delete {display: none;}
.sc-user-select__selected li:hover {background: var(--el-color-primary-light-9);}
.sc-user-select__selected li:hover .delete {display: inline-block;}

.sc-user-select-role .sc-user-select__left {width: 200px;}
.sc-user-select-role .sc-user-select__tree {border: none;height: 343px;}
.sc-user-select-role .sc-user-select__selected {}

[data-theme='dark'] .sc-user-select__selected li:hover {background: rgba(0, 0, 0, 0.2);}
[data-theme='dark'] .sc-user-select__toicon i {background: #383838;}
</style>

import request from '@/utils/request'

// 查询流程分组列表
export function listWflowProcessGroup(query) {
  return request({
    url: '/workflow/wflowProcessGroup/list',
    method: 'get',
    params: query
  })
}

// 查询流程分组详细
export function getWflowProcessGroup(id) {
  return request({
    url: '/workflow/wflowProcessGroup/' + id,
    method: 'get'
  })
}

// 新增流程分组
export function addWflowProcessGroup(data) {
  return request({
    url: '/workflow/wflowProcessGroup',
    method: 'post',
    data: data
  })
}

// 修改流程分组
export function updateWflowProcessGroup(data) {
  return request({
    url: '/workflow/wflowProcessGroup',
    method: 'put',
    data: data
  })
}

// 删除流程分组
export function delWflowProcessGroup(id) {
  return request({
    url: '/workflow/wflowProcessGroup/' + id,
    method: 'delete'
  })
}

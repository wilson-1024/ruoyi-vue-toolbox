import request from '@/utils/request'

// 查询流程模板列表
export function listWflowProcessTemplate(query) {
  return request({
    url: '/workflow/wflowProcessTemplate/list',
    method: 'get',
    params: query
  })
}

// 查询流程模板详细
export function getWflowProcessTemplate(id) {
  return request({
    url: '/workflow/wflowProcessTemplate/' + id,
    method: 'get'
  })
}

// 新增流程模板
export function addWflowProcessTemplate(data) {
  return request({
    url: '/workflow/wflowProcessTemplate',
    method: 'post',
    data: data
  })
}

// 修改流程模板
export function updateWflowProcessTemplate(data) {
  return request({
    url: '/workflow/wflowProcessTemplate',
    method: 'put',
    data: data
  })
}

// 删除流程模板
export function delWflowProcessTemplate(id) {
  return request({
    url: '/workflow/wflowProcessTemplate/' + id,
    method: 'delete'
  })
}

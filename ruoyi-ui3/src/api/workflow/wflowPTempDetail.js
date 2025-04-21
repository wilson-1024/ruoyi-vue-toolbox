import request from '@/utils/request'

// 查询流程模板详情列表
export function listWflowPTempDetail(query) {
  return request({
    url: '/workflow/wflowPTempDetail/list',
    method: 'get',
    params: query
  })
}

// 查询流程模板详情详细
export function getWflowPTempDetail(id) {
  return request({
    url: '/workflow/wflowPTempDetail/' + id,
    method: 'get'
  })
}

// 新增流程模板详情
export function addWflowPTempDetail(data) {
  return request({
    url: '/workflow/wflowPTempDetail',
    method: 'post',
    data: data
  })
}

// 修改流程模板详情
export function updateWflowPTempDetail(data) {
  return request({
    url: '/workflow/wflowPTempDetail',
    method: 'put',
    data: data
  })
}

// 删除流程模板详情
export function delWflowPTempDetail(id) {
  return request({
    url: '/workflow/wflowPTempDetail/' + id,
    method: 'delete'
  })
}

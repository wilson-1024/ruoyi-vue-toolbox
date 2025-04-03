import request from '@/utils/request'

export function transBaiduPoint(data) {
    return request({
        url: '/common/transBaiduPoint',
        method: 'post',
        data: data
    })
}

export function busIndexCount(){
    return request({
        url: '/business/busIndex/count',
        method: 'get'
    })
}

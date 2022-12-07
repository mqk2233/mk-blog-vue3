import Axios from 'axios'
// import qs from "qs";
import {ElNotification} from 'element-plus'
import store from "@/store/index";

const axios = Axios.create({
  timeout: 10000 // 请求超时 10s
})

// 前置拦截器（发起请求之前的拦截）
axios.interceptors.request.use(function (config: any) {
  // 在发送请求之前做某件事
  // if (
  //   config.method === "post" ||
  //   config.method === "put" ||
  //   config.method === "delete"
  // ) {
  //   // 序列化
  //   config.data = qs.parse(config.data);
  // }
  // 若是有做鉴权token , 就给头部带上token
  if (store.state.token) {
    config.headers.Authorization = store.state.token;
  }
  return config;
}, (error: { data: { error: { message: any; }; }; }) => {
  // Message({
  //   //  饿了么的消息弹窗组件,类似toast
  //   showClose: true,
  //   message: error,
  //   type: "error.data.error.message"
  // });
  return Promise.reject(error.data.error.message);
})

// 后置拦截器（获取到响应时的拦截）
axios.interceptors.response.use(
  (response) => {
    /**
     * 根据你的项目实际情况来对 response 和 error 做处理
     * 这里对 response 和 error 不做任何处理，直接返回
     */
    const responseCode = response.data.code;
    const responseMsg = response.data.msg;
    if (responseCode === 200) {
      ElNotification.success?.(responseMsg)
    } else {
      ElNotification.error?.(responseMsg)
    }
    return response
  },
  (error) => {
    if (error.response && error.response.data) {
      const msg = error.response.data.message
      ElNotification.error?.(msg)
    } else {
      ElNotification.error?.(`${error}`)
    }
    return Promise.reject(error)
  }
)

export default axios

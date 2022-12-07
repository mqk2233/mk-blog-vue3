import axios from '@/utils/axios'

export class auth {
  /**
   * 登录
   * @param param 登录参数
   */
  static doLogin(param: string) {
    console.log("doLogin")
    return axios.post(`api/admin/auth/login`, param);
  }

}


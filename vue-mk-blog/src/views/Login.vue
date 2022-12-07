<!--
 * @Author: MK
 * @Date: 2021-08-31 11:48:33
 * @LastEditTime: 2021-12-24 11:13:21
 * @LastEditors: Please set LastEditors
 * @Description: 登录界面
 * @FilePath: \vue-mk-blog\src\views\Login.vue
-->
<template>
  <Ribbons />
  <el-row justify="center" align="middle" class="el-row-top">
    <el-col :xs="6" :sm="7" :md="9" :lg="9" :xl="9" />
    <El-col :xs="12" :sm="10" :md="6" :lg="6" :xl="6">
      <El-card shadow="never" class="el-card">
        <template #header>
          <span class="title-font">MK’ BLOG</span>
        </template>
        <El-form label-position="right" status-icon class="demo-ruleForm">
          <El-formItem size="small" label="用户名" prop="userName">
            <El-input
              size="small"
              prefix-icon="el-icon-user"
              autocomplete="off"
              v-model="loginForm.userName"
            />
          </El-formItem>
          <El-formItem label="密&nbsp;&nbsp;&nbsp;码" prop="password">
            <El-input
              type="password"
              size="small"
              prefix-icon="el-icon-lock"
              autocomplete="off"
              v-model="loginForm.password"
            />
          </El-formItem>
          <El-formItem>
            <El-button size="small" type="primary" class="login-btn" @click="doLogin">登录</El-button>
          </El-formItem>
          <div class="three-login-div">
            <El-divider><span class="three-login-text">第三方账号登录</span></El-divider>
            <span class="three-login-span">
              <El-image lazy alt="qq登录" :src="loginImg1"/>
              <El-image lazy alt="微信登录" :src="loginImg2" />
              <a href="https://github.com/login/oauth/authorize?client_id=Iv1.b9471b40e8fac103&redirect_uri=http://localhost:4000/&scope=user&state=1">
                <El-image lazy alt="github登录" :src="loginImg3" />
              </a>
            </span>
          </div>
        </El-form>
      </El-card>
    </El-col>
    <El-col :xs="6" :sm="7" :md="9" :lg="9" :xl="9" />
  </el-row>
  <Footer />
</template>
<script lang="ts" setup>
import '@/style/login.scss'
import http from '@/api/http'
import router from '@/router/index'
import loginImg1 from '@/assets/qq.png'
import loginImg2 from '@/assets/wx.png'
import loginImg3 from '@/assets/github.png'
import Ribbons from '@/components/Ribbons.vue'
import Footer from '@/components/Footer.vue'
/**
 * 用户form参数
 */
const loginForm = {
  userName: '',
  password: ''
}
/**
 * 登录请求
 */
const doLogin = () => {
  http.auth.doLogin('').then((res) => {
    if (res.data.code === 200) {
      router.go(-1)
    }
  })
}
</script>

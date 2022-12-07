<!--
 * @Author: MK
 * @Date: 2021-08-11 20:26:07
 * @LastEditTime: 2021-12-24 15:51:52
 * @LastEditors: Please set LastEditors
 * @Description: 后台主页
 * @FilePath: \vue-mk-blog\src\views\Home.vue
-->
<template>
  <el-container style="width: 100%;height: 100%;">
    <el-header style="background-color:  #3a98fa"></el-header>
    <el-container >
      <el-aside  width="200px" style="background-color: #ffffff;" >
        <el-menu default-active="2" text-color="#303133" class="el-menu-vertical">
          <el-menuItem index="1">
            <span>文章管理</span>
          </el-menuItem>
          <el-menuItem index="2">
            <span>文章管理</span>
          </el-menuItem>
          <el-menuItem index="3">
            <span>文章管理</span>
          </el-menuItem>
        </el-menu>
      </el-aside>
      <El-container>
        <el-main>
          <VEditor style="width: 100%; height: 100%"/>
        </el-main>
        <el-footer style="background-color: rgb(238, 241, 246)"></el-footer>
      </El-container>
    </el-container>
  </el-container>
  <APlayer/>
</template>

<script lang="ts" setup>
import VEditor from "@/components/VEditor.vue"
import APlayer from "@/components/APlayer.vue"
import Footer from "@/components/Footer.vue"
</script>

<style>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 100px;
}

.el-aside {
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 553px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.layout-container-demo .el-header {
  position: relative;
  background-color: #b3c0d1;
  color: var(--el-text-color-primary);
}
.layout-container-demo .el-aside {
  width: 240px;
  color: var(--el-text-color-primary);
  background: #fff !important;
  border-right: solid 1px #e6e6e6;
  box-sizing: border-box;
}
.layout-container-demo .el-menu {
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 0;
}
.layout-container-demo .toolbar {
  position: absolute;
  display: inline-flex;
  align-items: center;
  top: 50%;
  right: 20px;
  transform: translateY(-50%);
}
</style>

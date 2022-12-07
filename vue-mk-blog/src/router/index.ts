import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/Home.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/ribbons',
    name: 'Ribbons',
    component: () => import('@/components/Ribbons.vue')
  },
  {
    path: '/index',
    name: 'Index',
    component: () => import('@/views/Index.vue')
  },
  {
    path: '/player',
    name: 'Player',
    component: () => import('@/components/APlayer.vue')
  },
  {
    path: '/live',
    name: 'Live2d',
    component: () => import('@/components/Live2d.vue')
  }


]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

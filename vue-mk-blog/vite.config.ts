import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {resolve} from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: [{find: '@', replacement: resolve(__dirname, 'src')}]
  },
  css: {
    preprocessorOptions: {
      scss: { // 注意：键名是scss不是sass！
        // 这里写你想导入全局scss变量的路径，注意只能写相对路径，alias貌似在css中不会生效
        additionalData: `@use "@/assets/scss/global.scss" as *;`,
      },
    },
  },
  base: './', // 设置打包路径
  server: {
    port: 4000, // 设置服务启动端口号
    open: true, // 设置服务启动时是否自动打开浏览器
    cors: true, // 允许跨域
    // 设置代理，根据我们项目实际情况配置
    proxy: {
      '/api': {
        target: 'http://localhost:9000/',
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace('/api/', '/')
      }
    }
  },
})

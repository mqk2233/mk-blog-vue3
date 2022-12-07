<template>
  <div ref="editorRef"></div>
</template>
<script setup lang="ts">
import VEditor from 'vditor'
import 'vditor/dist/index.css'
import type {PropType} from '@vue/runtime-core';
import {nextTick, onBeforeUnmount, onMounted, ref, watch} from 'vue'
//https://gitee.com/wolfox/blog-example/blob/master/vue/src/vditor/VditorEdit.vue#
const editorRef = ref({} as HTMLElement)
let instance: VEditor

const props = defineProps({
  // 历史记录间隔
  undoDelay: {
    type: Number as PropType<number>,
  },
  // 编辑器总高度
  height: {
    type: String as PropType<string | number>,
    default: 'auto'
  },
  // 编辑区域最小高度
  minHeight: {
    type: Number as PropType<number>,
  },
  // 编辑器总宽度，支持 %
  width: {
    type: String as PropType<string | number>,
    default: 'auto'
  },
  // 输入区域为空时的提示
  placeholder: {
    type: String as PropType<string>,
    default: ''
  },
  // 语言种类
  lang: {
    type: String as PropType<keyof II18n>,
    default: 'zh_CN',
  },
  // 是否启用打字机模式
  typewriterMode: {
    type: Boolean as PropType<boolean>,
    default: false
  },
  // 是否显示日志
  debugger: {
    type: Boolean as PropType<boolean>,
    default: false
  },
  // 编辑器初始化值
  value: {
    type: String as PropType<string>,
    default: ''
  },
  // 主题
  theme: {
    type: String as PropType<'classic' | 'dark'>,
    default: 'classic'
  },
  // 图标风格
  icon: {
    type: String as PropType<'ant' | 'material'>,
    default: 'ant'
  },
  //可选模式：sv-分屏预览, ir-即时渲染, wysiwyg-所见即所得
  mode: {
    type: String as PropType<'sv' | 'ir' | 'wysiwyg'>,
    default: 'ir'
  },
  content: {
    type: String as PropType<string>,
    default: ''
  },
  displayToolbarConfig: {
    type: Boolean as PropType<boolean>,
    default: false
  },
})

// 监听content
watch(() => props.content,
  (content: string) => {
    if (instance) {
      instance.setValue(content)
    }
  },
  {
    immediate: true
  }
)
// 初始化
onMounted(() => {
  nextTick(() => {
    instance = new VEditor(editorRef.value, {
      undoDelay: props.undoDelay,
      height: props.height,
      minHeight: props.minHeight,
      width: props.width,
      placeholder: props.placeholder,
      lang: props.lang,
      typewriterMode: props.typewriterMode,
      mode: props.mode,
      debugger: props.debugger,
      value: props.value,
      theme: props.theme,
      icon: props.icon,
      toolbarConfig: {
        pin: true
      },
      cache: {
        enable: props.displayToolbarConfig
      }
    })
  })
})
// 销毁
onBeforeUnmount(() => {
  instance.destroy()
})
</script>

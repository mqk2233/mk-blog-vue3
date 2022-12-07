<template>
  <div class="app">
    <div class="live2d-panel">
      <live2d v-if="isLive2d" :modelPath="modelPath" ref='l2dMange'></live2d>
    </div>
    <div class="tools-panel">
      <live2dTools v-for="(item,index) in toolsData"
                   :key="index"
                   :position="item.position"
                   @click="toolsClick(item)"
                   :width="item.width"
                   :toolsID="item.tabMsg"
                   :tabMsg="item.tabMsg"
                   :customDialogue='item.customDialogue'
                   :backgroundColor="item.backgroundColor"
                   ref='tool'
      />
    </div>
  </div>
</template>
<script setup>
import custom from "@/common/custom";
import {onMounted} from "vue";
let modelPath = "";
let modelPaths = "";
const toolsData = [
  {
    tabMsg: "dialogue",
    width: 280,
    customDialogue: custom,
    show: true,
    position: "left"
  },
  {
    tabMsg: "github",
    backgroundColor: "#add8e6",
    show: true,
    position: "left"
  },
  {
    tabMsg: "save",
    backgroundColor: "#add8e6",
    show: true,
    position: "left"
  },
  {
    tabMsg: "hide",
    backgroundColor: "#add8e6",
    show: true,
    position: "left"
  }
];
let isLive2d = true;
const isDialogue = false;
onMounted(() => {
  setInterval(() => {
    fetch(
      "https://v1.hitokoto.cn/?c=b"
    )
      .then(res => res.json())
      .then(data => {
        if (!isDialogue) {
          let tool = this.$refs.tool.filter(item => {
            return item.customDialogue;
          });
          if (tool && tool.length > 0) tool[0].showMessage(data.hitokoto);
        } else {
          this.$refs.dialogue.showMessage(data.hitokoto);
        }
      });
  }, 10000);
  modelPath =
    "https://cdn.jsdelivr.net/gh/mqk2233/blog-file/live2d/live2d-widget-model-unitychan/assets/unitychan.model.json";
  setTimeout(() => {
    modelPaths =
      "https://cdn.jsdelivr.net/gh/mqk2233/blog-file/live2d/live2d-widget-model-unitychan/assets/unitychan.physics.json";
  }, 2000000);
})
const toolsClick = (item) => {
  switch (item.tabMsg) {
    case "github":
      window.open("https://github.com/mqk2233");
      break;
    case "save":
      this.$refs.l2dMange.save(`live2d-${Date.now()}.png`);
      break;
    case "hide":
      isLive2d = false;
      toolsDisplay("hide");
      break;
    case "show":
      isLive2d = true;
      toolsDisplay("show");
      break;
  }
};
const toolsDisplay = (display) => {
  for (let i = 0, len = toolsData.length; i < len; i++) {
    let tabMsg = toolsData[i].tabMsg;
    if (display === "hide") {
      if (tabMsg === "home" || tabMsg === "about") continue;
      toolsData[i].show = false;
      if (tabMsg === "hide") {
        toolsData[i].show = true;
        toolsData[i].tabMsg = "show";
      }
    } else {
      toolsData[i].show = true;
      if (tabMsg === "show") toolsData[i].tabMsg = "hide";
    }
  }
}
</script>

<style lang="stylus" scoped>
.tools-panel
  position fixed
  left 0
  bottom 7em
  max-width 100px
  z-index 999

.live2d-panel
  position fixed
  left: 0
  bottom: 0
  z-index 999

#app
  height 100px
  z-index 999

.aPlayer
  position: fixed !important
  margin-left 0;
  z-index 99

</style>

<template>
  <div class="footer">{{ props.msg }}</div>
</template>
<script lang="ts" setup>
const props = defineProps({
  msg: {
    type: String,
    default: 'Copyright © 2021 by MK',
    required: false
  }
});
</script>
<style lang="scss" scoped>
.footer {
  display: flex;
  position: relative;
  z-index: 1;
  justify-content: center;
  top: 47vh;
  font-size: 12px;
}
</style>

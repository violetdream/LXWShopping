<template>
  <div id="app">
    <router-view class="main"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang="scss" scoped="" type="text/css">
@import "assets/style/index.scss";

#app {
  height: 100%;
}

.main {
  background-color: #b8b9ae;;
}
</style>

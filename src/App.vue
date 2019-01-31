<template>
  <div id="app">
    <el-container>
      <el-header style="height: 32px">
        <router-view name="header"></router-view>
      </el-header>
<!--      <el-header style="height: 162px">
        <router-view name="navigation"></router-view>
      </el-header>-->
      <el-container class="mainContainer">
        <el-main>
          <router-view name="main"></router-view>
        </el-main>
      </el-container>
    </el-container>
    <el-footer class="footerContainer">
      <router-view name="footer"></router-view>
    </el-footer>
  </div>
</template>

<script>
export default {
  name: "App"
};
</script>

<style>

body {
  margin: 0;
  padding: 0;
}
.el-header {
  background-color: #ffffff;
  color: #333;
}
.mainContainer {
  display: flex;
  min-height: 487px;
}
.el-aside {
  background-color: #d3dce6;
  flex: 1;

}

.el-main {
  background-color: #ffffff;
  flex: 8;
}
</style>

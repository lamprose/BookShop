<template>
  <div class="leftBox">
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
    >
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>导航一</span>
        </template>

        <el-submenu index="1-1">
          <template slot="title">订单</template>
          <router-link to="/orders/FactoryOrder">
            <el-menu-item index="1-1-1">手术订单</el-menu-item>
          </router-link>
          <router-link to="/orders/SurgeryOrder">
            <el-menu-item index="1-1-2">工厂订单</el-menu-item>
          </router-link>
        </el-submenu>

        <el-submenu index="1-2">
          <template slot="title">用户</template>
          <router-link to="/orders/userInfoManage">
            <el-menu-item index="1-2-1">用户信息</el-menu-item>
          </router-link>
          <router-link to="/orders/perUserInfo">
          <el-menu-item index="1-2-2">个人信息</el-menu-item>
          </router-link>
        </el-submenu>

      <el-submenu index="1-3">
        <template slot="title">书籍</template>
        <router-link to="/bookInfo/Search">
          <el-menu-item index="1-3-1">图书搜索</el-menu-item>
        </router-link>
        <router-link to="/bookInfo/Manage">
          <el-menu-item index="1-3-2">书籍管理</el-menu-item>
        </router-link>
      </el-submenu>

      <el-submenu index="1-4">
        <template slot="title">留言</template>
        <router-link to="/messageInfo/Manage">
          <el-menu-item index="1-4-1">留言信息</el-menu-item>
        </router-link>
        <el-menu-item index="1-4-2">选项2-2</el-menu-item>
      </el-submenu>

      </el-submenu>


      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>导航二</span>
        </template>

        <el-submenu index="2-1">
          <template slot="title">选项1</template>
          <el-menu-item index="2-1-1">选项1-1</el-menu-item>
          <el-menu-item index="2-1-2">选项1-2</el-menu-item>
        </el-submenu>
      </el-submenu>

      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>导航三</span>
        </template>

        <el-submenu index="3-1">
          <template slot="title">选项1</template>
          <el-menu-item index="3-1-1">选项1-1</el-menu-item>
          <el-menu-item index="3-1-2">选项1-2</el-menu-item>
        </el-submenu>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "LeftBox",
  data() {
    return {};
  },
  methods: {}
};
</script>

<style lang="" scoped>
.el-menu a{
  text-decoration: none;
}
</style>


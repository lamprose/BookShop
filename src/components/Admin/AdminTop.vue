<template>
  <!--导航栏-->
    <div class="adminTop">
      <el-row type="flex" class="row-bg" justify="center">
        <el-col :span="4"><div class="grid-content">
          <img src="../../assets/logo.png" style="width: 100px"/></div>
        </el-col>
        <el-menu :default-active="activeIndex"  mode="horizontal" style="margin-top: 15px;margin-right: 100px;" >
          <el-menu-item index="0" @click="usermanage">用户信息管理</el-menu-item>
          <el-menu-item index="1" @click="bookmanage">商品信息管理</el-menu-item>
          <el-menu-item index="2" @click="shopmanage">订单管理</el-menu-item>
          <el-menu-item index="3" @click="massagemanage">留言管理</el-menu-item>
        </el-menu>
      </el-row>

      <!--用户信息表-->
      <div v-if="show === 0" style="text-align:center;"><!--display:none-->
        <ShowUser></ShowUser>
      </div>

      <!--留言信息表-->
      <div v-else-if="show === 3" style="text-align:center;">
        <ShowMessage></ShowMessage>
      </div>

      <!--订单信息表-->
      <div v-else-if="show === 2" style="text-align:center;">
        <ShowShop></ShowShop>
      </div>

      <!--书籍信息表-->
      <div v-else-if="show === 1" style="text-align:center; ">
        <ShowBook></ShowBook>
      </div>
    </div>
</template>

<script>
    import ShowUser from  './ShowUser'
    import ShowBook from  './ShowBook'
    import ShowShop from  './ShowShop'
    import ShowMessage from  './ShowMessage'

    export default {
      name: "AdminTop",
      provide(){
        return{
          reloadAdmin:this.reloadAdmin
        }
      },
      components: {ShowMessage, ShowShop, ShowBook, ShowUser},
      methods:{
        reloadAdmin(num){
          this.show=5;
          this.$nextTick(()=>{
            this.show=num;
          })
        },
        usermanage(){
          this.show=0;
        },
        bookmanage(){
          this.show=1;
        },
        shopmanage(){
          this.show=2;
        },
        massagemanage(){
          this.show=3;
        },
      },
      data(){
          return {
            show:0,
          }
      }
    }
</script>

<style scoped>


</style>

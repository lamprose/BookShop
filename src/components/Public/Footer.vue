<template>
    <div class="appfooter">
      <div class="appfooterContent">
        <el-breadcrumb separator="    ">
          <el-breadcrumb-item :to="{ path: '/' }" class="appfooterItem">网站首页</el-breadcrumb-item>
          <el-breadcrumb-item class="appfooterItem">企业合作</el-breadcrumb-item>
          <el-breadcrumb-item class="appfooterItem">人才招聘</el-breadcrumb-item>
          <el-breadcrumb-item class="appfooterItem" >
            <label type="text" @click="aboutUs = true">关于我们</label>
          </el-breadcrumb-item>
          <el-breadcrumb-item class="appfooterItem">专家招募</el-breadcrumb-item>
          <el-breadcrumb-item class="appfooterItem">常见问题</el-breadcrumb-item>
          <el-breadcrumb-item class="appfooterItem" :to="toMessage">意见反馈</el-breadcrumb-item>
          <el-breadcrumb-item class="appfooterItem">友情链接</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="appfooterRight">
          {{appfooterRight}}
        </div>
        <el-dialog title="关于" :visible.sync="aboutUs" width="30%" center>
          <div style="width: 170px;margin:30px auto">
            <label>组长：宋利云 161310526</label><br>
            <label>组员：蒙香朱 151340112</label><br>
            <label style="margin-left: 42px">黄 &nbsp; 艺 151340122</label><br>
            <label style="margin-left: 42px">饶学亮 161310120</label><br>
            <label style="margin-left: 42px">李鹏书 161310523</label><br>
          </div>
        </el-dialog>
      </div>
    </div>
</template>

<script>
    export default {
        name: "appfooter",
      data(){
          return{
            //footer版权声明
            appfooterRight:"Copyright © 2018 cxy.com All Rights Reserved | 沪ICP备 12003892号-11",
            aboutUs: false,
            toMessage:{ path: '/messageboard' },
          }
      },
      mounted(){
          if(!this.$store.state.loginStatus)
            this.toMessage=''
        console.log(this.$store.state.loginStatus)
      },
      watch:{
        loginStatus() {
          if(!this.$store.state.loginStatus)
            this.toMessage=''
          else
            this.toMessage={ path: '/messageboard' }
        }
      },
      computed:{
        loginStatus(){
          return this.$store.state.loginStatus
        }
      }
    }
</script>

<style scoped>
  .appfooter {
    clear: both;
    width: 100%;
    height: 100px;
    background-color: #ffffff;
  }
  .appfooter {

  }
  .appfooterContent{
    margin: 10px auto;
    width: 700px;
  }
  .appfooter{
    float: left;
    margin-right: 30px;
    margin-top: 30px;
    margin-bottom: 10px;
    color: #000000;
    font-size: 10px;
  }
  .appfooterItem {
    margin-right: 10px;
  }
  .appfooter .appfooterRight {
    clear: both;
    font-size: 10px;
    color: darkgray;
  }
</style>

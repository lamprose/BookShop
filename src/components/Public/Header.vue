<template>
  <div class="header">
    <el-header style="float: right;background: #ffffff">
      <el-breadcrumb separator="    ">
        <el-breadcrumb-item class="headerLabel" v-if="showAlter">
          <router-link :to="{path:'/user/userInfoManage'}" class="headerLabel" style="color: red">您未设置密保问题，暂时只能浏览商品，如想购买商品，请点击此链接设置密保问题</router-link>
        </el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/' }" class="headerLabel">网站首页</el-breadcrumb-item>
        <el-breadcrumb-item v-if="loginStatus">
          <el-button type="text" class="headerButton" :disabled="showAlter" @click="toShoppingTrolley">购物车</el-button>
        </el-breadcrumb-item>
        <el-breadcrumb-item v-if="!loginStatus" class="headerLabel">欢迎光临，请</el-breadcrumb-item>
        <el-breadcrumb-item v-if="!loginStatus" style="margin-left: -27px">
          <el-button @click="ifLogin = true" type="text" class="headerButton">登录</el-button>
        </el-breadcrumb-item>
        <el-breadcrumb-item v-if="!loginStatus">
          <el-button v-if="registerB" @click="toRegister" type="text" class="headerButton">成为会员</el-button>
        </el-breadcrumb-item>
        <el-breadcrumb-item v-else class="headerLabel">
          <label style="font-size: 14px;padding-right: 0px">你好，
            <el-dropdown>
              <i style="margin-right: 15px">{{userName}}</i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <el-button type="text" @click="toUserInfo">我的</el-button>
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-button type="text" v-if="showAdminButton" @click="toAdmin">管理</el-button>
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-button type="text" @click="logoutConfirm=true" style="color: #f78989">注销</el-button>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </label>
        </el-breadcrumb-item>
      </el-breadcrumb>
    </el-header>

    <el-dialog :visible.sync="ifLogin" title="登录" center width="40%" @open="loginInit" @close="loginClose">
      <el-form ref="form" :model="user" style="width: 350px;margin:auto;">
        <el-form-item>
          <el-input v-model="user.id" placeholder="输入用户名"  class="input" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="user.password" placeholder="输入密码" type="password" class="input" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <label>记住密码</label>
          <el-radio-group v-model="radio">
            <el-radio label='1'>是</el-radio>
            <el-radio label='0'>否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <drag-verify v-if="identify.show" text="拉拽到右边以验证" :width="identify.width" :height="identify.height" success-text="验证成功" ref="Verity"></drag-verify>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer" v-if="!loginStatus">
        <el-button type="primary" round :disabled=loginDisabled @click="onSubmit">立即登录</el-button>
        <el-button type="primary" round  @click="toForgotPassword">忘记密码</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="提示"
      :visible.sync="logoutConfirm"
      width="30%"
      :show-close="false"
      center>
      <div style="width: 70px;margin:30px auto"><label style="width: 70px;margin: auto">确认注销？</label></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="logoutConfirm = false">取 消</el-button>
        <el-button type="danger" @click="logout">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>


<script>
  import DragVerify from "vue-drag-verify/src/dragVerify";
export default {
  name: "Header",
  components: {DragVerify},
  data() {
    return {
      //控制显示跳转管理员界面按钮
      showAdminButton:false,
      //控制注销确认弹窗
      logoutConfirm:false,
      //控制验证进度条属性
      identify:{
        show:true,
        width:350,
        height:40
      },
      //控制显示登陆弹窗
      ifLogin:false,
      //记住密码选项值
      radio:'0',
      //登陆按钮在未输入前被禁用
      loginDisabled:true,
      //控制注册按钮显示
      registerB:true,
      //用户  账号和密码
      user: {
        address:null,
        flag:null,
        id:'',
        name:null,
        password:'',
        phone:null,
        sex:null,
        uId:null,
        secretOption:null,
        secret:null
      }
    };
  },
  mounted(){
    if(sessionStorage.getItem("userId")!==null){
      this.$store.commit("changeLoginStatus")
      //console.log(JSON.parse(sessionStorage.getItem("user")))
      this.$store.commit("setUser",JSON.parse(sessionStorage.getItem("user")));
      if(this.$store.state.user.flag===1)
        this.showAdminButton=true
      //console.log(this.$store.state.user)
    }
    this.user.id=this.keepId
    this.user.password=this.keepPassword
    console.log(this.keepId,this.keepPassword)
    //console.log(sessionStorage.getItem("userId"));
  },
  methods: {
    onSubmit(){
      if(!this.$refs.Verity.isPassing){
        this.$message.error({
          message:"验证失败,请拉拽到右边以验证",
          showClose:true
        });
        return
      }
      //通过用户名获取密码用于验证
      this.$ajax.get('http://localhost:8080/user/login/?id='+this.user.id)
        .then((response)=>{
        //console.log(response.data)
        if(this.user.password===response.data['password']){
          this.$message.success({
            message:"登陆成功",
            showClose:true
          });
          this.user=response.data
          //console.log(this.user)
          //将用户名和token放入sessionStorage
          sessionStorage.setItem("userId",this.user.id)
          sessionStorage.setItem("user",JSON.stringify(this.user))
          //console.log("in login image is " + sessionStorage.getItem("user"))
          //将用户信息放入vuex
          this.$store.commit("setUser",this.user)
          this.$store.commit("changeLoginStatus")
          //如果记住密码，就将信息存入vuex
          if(this.radio==='1'){
            sessionStorage.setItem("keepId",this.user.id);
            sessionStorage.setItem("keepPassword",this.user.password);
            //console.log("test success")
          }else {//没有记住密码则清空
            sessionStorage.removeItem("keepId")
            sessionStorage.removeItem("keepPassword")
          }
          //登陆成功后跳转主页面并隐藏登陆弹窗
          if(this.$store.state.user.flag===1){
            this.toAdmin()
            this.showAdminButton=true
          }
          else
            this.$router.push({path:'/home'})
          this.ifLogin=false
        }else {
          this.$message.error({
            message:"啊偶，登录失败",
            showClose:true
          });
          this.ifLogin=false
        }
      })
      //console.log(sessionStorage.getItem("userId"));
      //console.log(this.user);

    },
    //显示登陆窗口时初始化数据
    loginInit(){
      this.radio='0'
      this.identify.show=true
      this.user.id=this.keepId
      this.user.password=this.keepPassword
      if(this.user.id!==''&&this.user.password!=='')
        this.loginDisabled=false
    },
    //关闭登陆窗口时清除数据
    loginClose(){
      this.identify.show=false
    },
    //跳转到注册页面
    toRegister(){
      this.$router.push({name:'Register'})
      this.ifLogin=false
      this.registerB=false
    },
    toForgotPassword(){
      this.$router.push({name:'ForgotPassword'})
      this.ifLogin=false
    },
    toShoppingTrolley(){
      this.$router.push({name:'ShoppingTrolley'})
    },
    toUserInfo(){
      this.$router.push({name:'UserInfoManage'})
    },
    toAdmin(){
      this.$router.push({name:'Admin'})
    },
    //注销当前用户
    logout(){
      this.logoutConfirm=false
      sessionStorage.removeItem("userId")
      sessionStorage.removeItem("user")
      this.$store.commit("setUser",null)
      //console.log("注销成功")
      this.$router.push({path:'/home'})
      location.reload();
    }
  },
  watch:{
    idInput() {
      this.loginDisabled=(this.user.id===''||this.user.password==='')
    },
    pwdInput() {
      this.loginDisabled=(this.user.id===''||this.user.password==='')
    }
  },
  computed:{
    showAlter(){
      //console.log(this.$store.state.user)
      if(this.$store.state.loginStatus)
        return this.$store.state.user.secretOption===null
      else
        return false
    },
    //返回用户输入框值
    idInput(){
      return this.user.id
    },
    //返回密码输入框值
    pwdInput(){
      return this.user.password
    },
    userName(){
      if(this.$store.state.user.name===null)
        return '用户'
      else
        return this.$store.state.user.name
    },
    loginStatus(){
      return this.$store.state.loginStatus
    },
    keepId(){
      return sessionStorage.getItem("keepId")?sessionStorage.getItem("keepId"):''
    },
    keepPassword(){
      return sessionStorage.getItem("keepPassword")?sessionStorage.getItem("keepPassword"):''
    }
  }
}
</script>

<style scoped>
  .headerLabel{
    margin-right: 10px;
    font-size: 15px;
    margin-top: 17px;
    padding-right: 0px
  }
  .headerButton {
    margin-top: 0px;
    height: 16px;
    margin-right: 10px;
    margin-top: 5px;
    color: #409EFF;
    }

  .input{
    width: 350px;
  }

</style>


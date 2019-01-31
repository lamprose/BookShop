<template>
  <div class="mainBox">
    <div style="float: left;width: 300px;background: #545c64;min-height: 487px">
      <leftbox ></leftbox>
    </div>
    <div style="float: left">
      <el-row :gutter="20" class="orderTitle">
        <!--标题-->
        <el-col :span="2.5">
          <b>
            <i class="el-icon-mobile-phone"></i>
          </b>
          <span>
          <b>用户信息管理</b>
        </span>
        </el-col>
        <el-col :span="1" :offset="20">
          <el-button type="primary" @click="dialogCreateVisible = true">管理用户信息（管理员）</el-button>
        </el-col>
      </el-row>

      <!-- 个人信息表格 -->
      <el-table :data="userInfo" border>
        <el-table-column type="index" label="No." align="center"></el-table-column>
        <el-table-column prop="uId" label="用户编号" align="center"></el-table-column>
        <el-table-column prop="id" label="用户账号" align="center"></el-table-column>
        <el-table-column prop="password" label="账号密码" align="center"></el-table-column>
        <el-table-column prop="name" label="账号名称" align="center"></el-table-column>
        <el-table-column prop="sex" label="个人性别" align="center"></el-table-column>
        <el-table-column prop="phone" label="联系电话" align="center"></el-table-column>
        <el-table-column prop="address" label="家庭住址" align="center"></el-table-column>
        <el-table-column prop="flag" label="是否为管理员" align="center"></el-table-column>
        <el-table-column label="操作" width="220" align="center">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-setting"
              @click="setCurrent(scope.row)"
            >修改</el-button>
            <el-button type="danger" size="small" icon="el-icon-delete" @click="removed(scope.row)">注销</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 新建用户 -->
    <el-dialog title="编辑用户" :visible.sync="dialogCreateVisible">
      <el-form id="#create" :model="create" ref="create" label-width="100px">
        <el-form-item label="用户编号" prop="uId">
          <el-input v-model="create.uId"></el-input>
        </el-form-item>
        <el-form-item label="用户账号" prop="id">
          <el-input v-model="create.id"></el-input>
        </el-form-item>
        <el-form-item label="账号密码" prop="password">
          <el-input v-model="create.password"></el-input>
        </el-form-item>
        <el-form-item label="账号名称" prop="name">
          <el-input v-model="create.name"></el-input>
        </el-form-item>
        <el-form-item label="个人性别" prop="sex">
          <el-input v-model="create.sex"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="create.phone"></el-input>
        </el-form-item>
        <el-form-item label="家庭住址" prop="address">
          <el-input v-model="create.address"></el-input>
        </el-form-item>
        <el-form-item label="是否为管理员" prop="flag">
          <el-input v-model="create.flag" type="password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="checkpass">
          <el-input v-model="create.remark" type="password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogCreateVisible = false">取 消</el-button>
        <el-button type="primary" @click="createOrder">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改信息 -->
    <el-dialog
      title="修改用户信息"
      :visible.sync="dialogUpdateVisible"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <el-form id="#update" :model="update" ref="update" label-width="100px">
        <el-form-item label="用户编号" prop="uId">
          <el-input v-model="update.uId"></el-input>
        </el-form-item>
        <el-form-item label="用户账号" prop="id">
          <el-input v-model="update.id"></el-input>
        </el-form-item>
        <el-form-item label="账号密码" prop="password">
          <el-input v-model="update.password"></el-input>
        </el-form-item>
        <el-form-item label="账户名称" prop="name">
          <el-input v-model="update.uId"></el-input>
        </el-form-item>
        <el-form-item label="个人性别" prop="sex">
          <el-input v-model="update.id"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="create.phone"></el-input>
        </el-form-item>
        <el-form-item label="家庭住址" prop="address">
          <el-input v-model="create.address"></el-input>
        </el-form-item>
        <el-form-item label="是否为管理员" prop="flag">
          <el-input v-model="create.flag"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="totalPrice">
          <el-input v-model="update.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogUpdateVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateOrder">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>


<script>
  import leftbox from  './LeftBox'
  export default {
    name: "MainBox",
    components: {leftbox},
    inject:['reload'],
    methods: {
      // 新增账户
      createOrder() {
        let data = this.create;
        console.log(data);
        this.$ajax.post('http://localhost:8080/order/saveOne/', JSON.stringify(data),
          {
            headers: { 'Content-Type': 'application/json;charset=UTF-8'}
          }
        ).then(response=> {
          console.log(response);
          this.dialogCreateVisible=false;
          this.open2();
        }).catch(function (error){
          console.log("save failed！")
        });
      },
      setCurrent(currentOrder) {
        console.log(currentOrder);
        this.update.uId = currentOrder.uId;
        this.update.id = currentOrder.id;
        this.update.password = currentOrder.password;
        this.update.name = currentOrder.name;
        this.update.sex = currentOrder.sex;
        this.update.phone = currentOrder.phone;
        this.update.address = currentOrder.address;
        this.update.flag = currentOrder.flag;
        this.dialogUpdateVisible = true;

        console.log(this.dialogUpdateVisible);
      },
      updateOrder() {
        console.log("确定修改");
        this.dialogUpdateVisible = false;
      },
      // 注销账号
      removed(currentOrder) {
        console.log("删除账号");
        this.$confirm(
          "此操作将永久删除账号 " + currentOrder.orderId + ", 是否继续?",
          "提示",
          {
            type: "warning"
          }
        ).then(() => {
          console.log("确认删除该用户");
          // 向请求服务端删除
          let uId = currentOrder.uId;
          console.log(uId);
          this.$ajax.get('http://localhost:8080/order/deleteOne/'+uId,).then(response=> {
            console.log(response);
            if(response.data=="success"){
              this.open1();
            }
          }).catch(function (error){
            console.log("delete failed！")
          });
        })
          .catch(() => {
            this.$message.info("已取消操作!");
          });
      },
      open1() {
        this.$message({
          message: '恭喜你，删除成功!',
          type: 'success'
        });
        this.reload();
      },
      open2() {
        this.$message({
          message: '恭喜你，添加成功!',
          type: 'success'
        });
        this.reload();
      },
      open3() {
        this.$message({
          message: '警告哦，这是一条警告消息',
          type: 'warning'
        });
      },
    },
    mounted(){
        // 加载数据
        console.log("loading data.")
        this.$ajax({
          method:'get',
          url:'http://localhost:8080/order/findAll',
        }).then(response=>{
          console.log(response.data);
          for(let i= 0; i<response.data.length;i++) {
            this.FactoryOrderInfo.push(response.data[i]);
          }
        });
    },
    data() {
      return {
        dialogCreateVisible: false,
        dialogUpdateVisible: false,
        create: {
          uId: "",
          id: "",
          password: "",
          name: "",
          sex: "",
          phone: "",
          address: "",
          flag: "",
          remark: ""
        },
        update: {
          uId: "",
          id: "",
          password: "",
          name: "",
          sex: "",
          phone: "",
          address: "",
          flag: "",
          remark: ""
        },
        userInfo: [],
      };
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1,
  h2 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }

  .orderTitle {
    border-bottom: 2px #409eff solid;
    font-size: 24px;
    padding-bottom: 10px;
  }
</style>


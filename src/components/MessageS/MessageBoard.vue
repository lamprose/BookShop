<template>
    <div>
      <!--头部：发表留言按钮-->
      <div>
        <img src="../../assets/icon.png">
        <el-button type="success" style="float:right; margin-top: 120px; margin-bottom: 50px" @click="dialogCreateVisible = true">发表留言</el-button>
      </div>
      <!--主体：各用户留言展示-->
      <div>
        <el-table
          ref="multipleTable"
          :data="messageInfo"
          tooltip-effect="dark"
          style="width: 100%">
          <el-table-column
            prop="content"
            label="留言内容">
          </el-table-column>
          <el-table-column
            prop="userid"
            label="用户"
            width="180">
          </el-table-column>
          <el-table-column
            prop="date"
            width="180"
            label="日期"
            show-overflow-tooltip>
          </el-table-column>
        </el-table>
      </div>
      <!--弹窗：编辑留言并发表-->
      <el-dialog title="发表留言" :visible.sync="dialogCreateVisible">
        <el-form :model="messageCreate" ref="messageCreate" label-width="100px">
          <el-input
            v-model="messageCreate.content"
            type="textarea"
            :rows="10"
            placeholder="请输入内容">
          </el-input>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogCreateVisible = false">取 消</el-button>
          <el-button type="primary" @click="createMessage">确 定</el-button>
        </div>
      </el-dialog>
    </div>
</template>

<script>
    export default {
      name: "MessageBoard",
      inject:['reload'],
      data(){
        return{
          dialogCreateVisible: false,
          //创建留言定义的数据类型
          messageCreate:{
            content:'',
            userid:'',
            date:'',
          },
          //各用户留言信息表
          messageInfo:[]
        }
      },
      mounted(){
        //加载用户留言表
        this.messageCreate.userid = this.loginUserId;
        console.log("loading data.");
        this.$ajax({
          method:'get',
          url:'http://localhost:8080/message/findAll',
        }).then(response=>{
          console.log(response.data);
          for(let i= 0; i<response.data.length;i++) {
            this.messageInfo.push(response.data[i]);
          }
        });
      },
      methods:{
        createMessage(){
          //发表留言，向后台发送保存
          let data = this.messageCreate;
          var data1=new Date();
          data.date = data1.toDateString();
          this.$ajax.post('http://localhost:8080/message/saveOne/', JSON.stringify(data),
            {
              headers: { 'Content-Type': 'application/json;charset=UTF-8'}
            }
          ).then(response=> {
            console.log(response);
            this.dialogCreateVisible=false;
            this.$message({
              message: '发表成功',
              type: 'success'
            });
            this.reload();
          }).catch(function (error){
            console.log("save failed！")
          });
        }
      },
      computed:{
        loginUserId(){
          return this.$store.state.user.id;
        }
      }
    }
</script>

<style scoped>

</style>

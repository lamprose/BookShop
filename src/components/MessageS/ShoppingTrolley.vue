<template>
  <div>
    <!--头部：我的购物车-->
    <div style="margin-bottom: 20px">
      <label style="font-weight: bold; color: #606266;">我的购物车</label>
    </div>
    <!--主体：购物车信息表-->
    <div>
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="gId"
          label="书籍编号"
          width="120">
        </el-table-column>
        <el-table-column
          prop="img"
          label="封面"
          width="120">
          <template slot-scope="scope">
            <img :src="scope.row.img" width="60" height="80"/>
          </template>
        </el-table-column>
        <el-table-column
          prop="title"
          label="书名"
          width="120">
        </el-table-column>
        <el-table-column
          prop="price"
          label="单价"
          width="120">
        </el-table-column>
        <el-table-column
          prop="sum"
          label="数量"
          show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.sum" @change="handleChange" :min="1" style="width: 150px"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button type="danger" size="small" icon="el-icon-delete" @click="deleteOrder(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--底部：总计+总金额+确认购买-->
    <div style="margin-top: 120px">
      <div style="margin-right: 47px;float:right">
        <el-button type="success" size="small" style="width: 73px">确认支付</el-button>
      </div>
      <div style="margin-right: 120px; float:right">
        <label style="font-weight: bold; color: #606266;">总金:</label>
        <label style="font-weight: bold; color: #606266;">{{totalPrice}}</label>
      </div>
      <div style="margin-right: 120px; float:right">
        <label style="font-weight: bold; color: #606266;">总计：</label>
        <label style="font-weight: bold; color: #606266;">{{totalGoods}}</label>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
      name: "ShoppingTrolley",
      inject:['reload'],
      data() {
        return {
          tableData: [],                //购物车信息表
          multipleSelection: [],        //CheckBox已勾选的行的数据
          totalGoods:0,                 //总件数
          totalPrice:0,                 //总金额
          userId:''
        }
      },
      methods: {
        handleSelectionChange(val) {
          //CheckBox改变时，计算相应总件数和总金额
          this.totalGoods = 0;
          this.totalPrice = 0;
          for(let book of val)
          {
            this.totalGoods += Number(book.sum);
            this.totalPrice += Number(book.sum) * Number(book.price);
          }
          //multipleSelection保存已勾选项的信息
          this.multipleSelection = val;
        },
        handleChange(value) {
          //Input-number改变时，即加减数量，计算相应的总件数和总金额
          this.totalGoods = 0;
          this.totalPrice = 0;
          for(let book of this.multipleSelection)
          {
            this.totalGoods += Number(book.sum);
            this.totalPrice += Number(book.sum) * Number(book.price);
          }
        },
        deleteOrder(currentOrder){
          console.log("移出购物车");
          this.$confirm(
            "此操作将移出此订单, 是否继续?",
            "提示",
            {
              type: "warning"
            }
          ).then(() => {
            // 向请求服务端删除
            this.$ajax.get('http://localhost:8080/shop/deleteOneByIdAndTitle?userid='+this.userId+'&title='+currentOrder.title,).then(response=> {
              if(response.data===true){
                this.$message({
                  message: '恭喜你，删除成功',
                  type: 'success'
                });
                this.reload();
              }
            }).catch(function (error){
              console.log("delete failed！")
            });
          })
            .catch(() => {
              this.$message.info("已取消操作!");
            });
        }
      },
      mounted(){
        //从后端加载相应的数据
        this.userId=this.loginUserId;
        this.$ajax.get('http://localhost:8080/shop/finduserAll/'+ this.userId,
        ).then(response=>{
          //bookInfo临时保存返回的书籍信息
          let bookInfo=response.data;
          //console.log(bookInfo);
          this.$ajax({
            method:'get',
            url:'http://localhost:8080/shop/findAllid/'+ this.userId
          }).then(response=>{
            //bookAmount临时保存返回的相应的数量信息
            let bookAmount=response.data;
            //console.log(bookAmount);
            let tmp={};
            for(let i=0; i<bookInfo.length; i++){
              //bookInfo和bookAmount组合成购物车信息表
              tmp=Object.assign(bookInfo[i],bookAmount[i]);
              this.tableData.push(tmp);
            }
            console.log(tmp);
          });
        });
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

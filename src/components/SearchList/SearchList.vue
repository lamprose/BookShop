<template>

  <el-container style="height:800px;border: 1px solid #eee">
    <el-aside width="100px" style="background-color: rgb(238, 241, 246)">
      <el-row>
        <!--购物车栏-->
        <div class="bg-purple" style="text-align:center;line-height:40px">购物车<i class="el-icon-refresh"></i></div>
      </el-row>
      <!--v-for渲染-->
      <el-card :body-style="{ padding: '0px'}" style="padding-bottom:5px" v-for="(item,index) in BookList">
        <el-col :span="24">
        <span style="color:#409EFF;padding-left:10%;
  ">{{item.title}}</span><br/>
            <span style="color:#F56C6C;padding-left:20%;
          ">￥{{item.price}}</span>
            <el-button type="danger" size="mini" style="float:right"round @click="deleteBook(item.gId,index)">溜了</el-button>
          </el-col>
        </el-card>
    </el-aside>


    <el-main>
      <div class="mainBox">
        <el-row :gutter="20" class="orderTitle">
          <!--标题-->
          <el-col :span="2.5">
            <b>
              <i class="el-icon-mobile-phone"></i>
            </b>
            <span>
          <b>搜索结果汇总</b>
        </span>
          </el-col>
        </el-row>

        <!-- 搜索结果汇总 -->
        <!--<el-card :body-style="{ padding: '0px' }">-->
          <!--<el-col :span="8">-->
            <!--<img src="http://ima.ntwikis.com/cancollezh/20151119/S_NORMAL_1084.png?ver=20160802" class="image">-->
          <!--</el-col>-->
          <!--<el-col :span="12" :offset="2">-->

            <!--<p style="color:#409EFF;-->
<!--">他改变了中国</p>-->
            <!--<p style="color:#F56C6C;-->
        <!--">¥666.00<br/><span class="time">群星/2000-6-6/出版社名</span></p>-->

            <!--<el-button type="success" size="mini" round>我要预订</el-button>-->

          <!--</el-col>-->
        <!--</el-card>-->


        <el-card :body-style="{ padding: '0px' }" v-for="item in SearchListInfo" style="padding-bottom:5px">
        <el-col :span="5">
          <div class="box">
            <img :src=item.img class="image"></div>
        </el-col>
        <el-col :span="12" :offset="0">

        <p style="color:#409EFF;
        ">{{item.title}}</p>
        <p style="color:#F56C6C;
        ">￥{{item.price}}<br/><span class="time">{{item.author}}/{{item.date}}/{{item.press}}</span></p>
          <p class="introText">{{item.others}}</p>
        <el-button type="success" size="mini" round @click="addBook(item)">加入到购物车</el-button>

        </el-col>
        </el-card>

      </div>
    </el-main>

  </el-container>

</template>

<script>
  export default {
    name: "Searchlist",
    inject:['reload'],
    methods: {
      // 新建

      /*searchQuery() {
        // let data = this.queryString;
        //searchkey : API中‘搜索关键字’参数对应的参数名
        let data1={searchkey:this.queryString};
        console.log(data);
        this.$ajax.post('http://localhost:8080/book/findAllSearch/', JSON.stringify(data1),
          {
            headers: { 'Content-Type': 'application/json;charset=UTF-8'}
          }
        ).then(response=> {
          console.log("searchQuery函数获得了以下数据：");
          console.log(response);
          this.SearchListInfo=response;
        }).catch(function (error){
          console.log("searchQuery未能成功运行！报错信息："+error)
        });
      },*/

      //从购物车中删除书本
      deleteBook(gId,index)
      {
        console.log("尝试从购物车删除以下标识号的书籍："+gId);
        this.BookList.splice(index,1);
      },
      // 添加图书信息
      addBook(item){
        console.log("尝试将以下书本加入购物车：:"+item);
        this.BookList.push(item);

        this.orderOne.id = "s123";
        this.orderOne.title = item.title;
        let data = this.orderOne;
        this.$ajax.post('http://localhost:8080/shop/saveOne/', JSON.stringify(data),
          {
            headers: { 'Content-Type': 'application/json;charset=UTF-8'}
          }
        ).then(response=> {
          console.log(response);
          this.$message.info("添加成功!");
        }).catch(function (error){
          console.log("save failed！")
        });

      },
      open1() {
        this.$message({
          message: '恭喜你，删除成功',
          type: 'success'
        });
        this.reload();
      },

    },
    mounted(){
      //搜索框提交数据并跳转至的方式this.$router.push({ name: 'Searchlist', params: { querystring: "搜索关键字" }})
      /*this.queryString=this.$route.params.querystring;
      console.log(this.queryString);*/

      this.queryString = this.queryText;
      console.log(this.queryText)
      let data = this.queryString;
      /*//searchkey : API中‘搜索关键字’参数对应的参数名
      let data1={searchkey:this.queryString};*/
      console.log(data);
      this.$ajax.get('http://localhost:8080/book/findAllSearch/'+data,).then(response=> {
        console.log("searchQuery函数获得了以下数据：");
        console.log(response.data);
        /*response.data*/
        this.SearchListInfo=response.data;
      }).catch(function (error){
        console.log("searchQuery未能成功运行！报错信息："+error)
      });
    },
    computed:{
      queryText(){
        return this.$route.params.queryString
      }
    },
    data() {
      return {
        queryString:'',
        dialogCreateVisible: false,
        dialogUpdateVisible: false,

        orderOne:{
        id:"",
        title:"",
        sum:1,
      },

        BookList:[
         //硬编码数据，测试购物车栏
          {
            gId: "3",
            date:"1990-1-1",
            title: "ddd",
            author: "fff",
            press:"2333",
            others:"heheda",
            price:"12",
            img:"http://ima.ntwikis.com/cancollezh/20151119/S_NORMAL_1084.png?ver=20160802",
          },
          {
            gId: "3",
            date:"1990-1-1",
            title: "ddd",
            author: "fff",
            press:"2333",
            others:"heheda",
            price:"12",
            img:"http://ima.ntwikis.com/cancollezh/20151119/S_NORMAL_1084.png?ver=20160802",
          },
          {
            gId: "3",
            date:"1990-1-1",
            title: "ddd",
            author: "fff",
            press:"2333",
            others:"heheda",
            price:"12",
            img:"http://ima.ntwikis.com/cancollezh/20151119/S_NORMAL_1084.png?ver=20160802",
          },
        ],

        SearchListInfo: [
          {
            gId: "",
            date:"",
            title: "",
            author: "",
            press:"",
            others:"",
            price:"",
            img:"",
          },
        ],

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

  .introText {
    overflow: hidden;/*超出部分隐藏*/
    white-space: nowrap;/*不换行*/
    text-overflow:ellipsis;/*超出部分文字以...显示*/

    font-size: 13px;
    color: #999;
  }

  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    height:100%;
    width: 100%;
    display: block;
  }

  .box{
    width: 200px;
    height:250px;
    border:1px solid #000;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

</style>

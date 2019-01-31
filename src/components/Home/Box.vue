<template>
  <div>
    <div style="width: auto">
      <el-carousel :interval="4000" type="card" height="315px">
        <el-carousel-item v-for="index in 5" :key="index">
            <img :src="headerPic[type ]">
        </el-carousel-item>
     </el-carousel>
    </div>
    <div style="width: 1090px;margin: auto">
      <el-row>
        <el-col :span="8" v-for="(o, index) in bookShelf" :key="o" :offset="index > 0 ? 2 : 0" class="el_col">
          <el-card :body-style="{ padding: '0px' }" class="card">
            <img :src="o['img']" class="image">
            <div style="padding: 14px;">
              <span>{{o['title']}}</span>
              <div class="bottom clearfix">
                <time class="time">{{ currentDate }}</time>
                <el-button type="text" class="button" @click="showBookInfo($event)">
                  详情<label id="isbn" style="visibility: hidden">{{o['isbn']}}</label>
                </el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <el-dialog :title="bookInfo[0].value" :visible.sync="bookInfoShow" width="50%" center style="margin-top: -97px">
      <el-form>
        <el-container>
          <el-main style="width: 100px">
            <el-form-item v-for="i in 7" :label="bookInfo[i].label">{{bookInfo[i].value}}</el-form-item>
          </el-main>
          <el-main>
            <img :src="bookInfo[8].value" style="width: 250px">
          </el-main>
        </el-container>
      </el-form>
      <span slot="footer" class="dialog-footer" style="margin-right: -300px">
        <el-input-number v-model="Shop.sum" :min="1" :max="Number(bookInfo[9].value)" label="描述文字"></el-input-number>
        <el-button type="primary" @click="onSubmit">加入购物车</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: 'book',
    data() {
      return {
        currentDate: new Date().getFullYear(),
        headerPic:[
          'http://img60.ddimg.cn/upload_img/00087/geq/750x315_wzh_20181227-1546053939.jpg',
          'http://img63.ddimg.cn/ddreader/dangebook/1yxs_750x315.jpg',
          'http://img56.ddimg.cn/9002820078165566.jpg',
          'http://img63.ddimg.cn/upload_img/00316/by/pc-1546499357.jpg',
          'http://img63.ddimg.cn/upload_img/00087/geq/750x315_dl_0105-1546831362.jpg',
          'http://img60.ddimg.cn/upload_img/00570/tongshu/750x315_wqh_20190104-1546831455.jpg',
          'http://img59.ddimg.cn/9003330052417439.jpg',
          'http://img61.ddimg.cn/upload_img/00570/tongshu/750x315_djj_1227-1546049369.jpg',
          'http://img60.ddimg.cn/topic_img/gys_0014426/houniaochoujiang1750315.JPG',
          'http://img60.ddimg.cn/upload_img/00087/geq/750x310-1547175291.jpg'
        ],
        bookShelf:[],
        bookInfoShow:false,
        bookInfo:[
          {label:'书名',value:'',key:'title'},
          {label:'作者',value:'',key:'author'},
          {label:'ISBN',value:'',key:'isbn'},
          {label:'类型',value:'',key:'type'},
          {label:'出版社',value:'',key:'press'},
          {label:'出版日期',value:'',key:'date'},
          {label:'价格',value:'',key:'price'},
          {label:'备注',value:'',key:'others'},
          {label:'封面',value:'',key:'img'},
          {label:'库存',value:'',key:'booksum'}
        ],
        bookTypeText:[
          '推荐','文艺','科技','经管','教育','励志','生活','音像','计算机','人文社科'
        ],
        Shop:{
          id:null,
          title:null,
          sum:1
        }
      }
    },
    props:['type'],
    methods:{
      showBookInfo(e){
        this.bookInfoShow=true
        //console.log(e.currentTarget.querySelector('#isbn').innerHTML)
        let isbn=e.currentTarget.querySelector('#isbn').innerHTML
        this.$ajax.get('http://localhost:8080/book/findOneByIsbn/'+isbn)
          .then(response=>{
            //console.log(this.type);
            console.log(response.data);
            let book=response.data
            this.bookInfo.forEach((item)=>{
              item['value']=book[`${item['key']}`]
            })
            this.bookInfo[3]['value']=this.bookTypeText[`${Number(this.bookInfo[3]['value'])}`]
            //console.log(this.bookInfo)
          });
      },
      onSubmit(){
        if(!this.userId){
          this.$message.error({
            message:"未登录，请登陆后选购商品",
            showClose:true
          });
          this.bookInfoShow=false
          return
        }
        this.Shop.title=this.bookInfo[0]['value']
        this.Shop.id=this.userId
        let data = this.Shop;
        //console.log(data);
        this.$ajax.post('http://localhost:8080/shop/saveOne/', JSON.stringify(data),
          {
            headers: { 'Content-Type': 'application/json;charset=UTF-8'}
          }
        ).then(response=>{
          //console.log(response.data)
          if(response.data){
            this.$message.success({
              message:"加入购物车成功",
              showClose:true
            });
            this.bookInfoShow=false
          }
        })
      }
    },
    computed:{
      userId(){
        if(this.$store.state.loginStatus)
          return this.$store.state.user.id
        else
          return null
      }
    },
    mounted() {
      this.$ajax.get('http://localhost:8080/book/'+this.type)
        .then(response=>{
         //console.log(this.type);
        // console.log(response.data);
        this.bookShelf=response.data
      });
    },
    watch:{

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
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
    width: 210px;
    height: 210px;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
  .card{
    width: 210px;
    margin-left: 0px;
  }
  .el_col{
    margin-left: 0px;
    width: 218px;
  }
</style>

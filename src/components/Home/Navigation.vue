<template>
  <div class="navigation">
    <div style="width: 1170px;margin-left: 143px">
      <el-row :gutter="20" class="firstNav">
        <el-col :span="4" ><div class="log">
          <img style="width: 300px" src="http://img63.ddimg.cn/upload_img/00728/4/DangDanglogoNEW-1531446546.gif">
        </div></el-col>
        <el-col :span="16"><div class="search">
          <el-autocomplete placeholder="搜索商品" v-model="searchText"
                    :fetch-suggestions="querySearchAsync"
                    class="search-input">
            <el-button slot="append" icon="el-icon-search" @click="toSearchResult"></el-button>
          </el-autocomplete>
          <br>
          <label class="hotSearch">{{hotSearchText}}</label>
        </div></el-col>
      </el-row>
    </div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item v-for="(o,index) in searchTypeText" :index="index.toString()">{{o}}</el-menu-item>
    </el-menu>
    <div>
      <box :type="0" v-if="typeIndex==0"></box>
      <div v-for="k in [1,2,3,4,5,6,7,8,9]">
        <box :type="k" v-if="typeIndex==k"></box>
      </div>
    </div>
  </div>
</template>

<script>
  import box from  './Box'
  export default {
    name: "Navigation",
    data() {
      return {
        activeIndex: '0',
        hotSearchText:'热搜：软件工程导论 C++:从入门到放弃 JAVA 数据库结构设计',
        typeIndex:0,
        show:true,
        //搜索内容
        searchText:'',
        searchSuggestions:[],
        searchTypeText:[
          '推荐','文艺','科技','经管','教育','励志','生活','音像','计算机','人文社科'
        ],
        timeout:null
      };
    },
    components:{box},
    methods: {
      handleSelect(key, keyPath) {
        this.typeIndex = key
        //console.log(key, keyPath);
      },
      //实现搜索建议下拉的数据写入
      querySearchAsync(queryString, cb) {
        let searchSuggest = this.searchSuggestions;
        searchSuggest=this.unique(searchSuggest)
        let results = queryString ? searchSuggest.filter(this.createStateFilter(queryString)) : searchSuggest;
        //实现加载效果
        clearTimeout(this.timeout);
        this.timeout = setTimeout(() => {
          cb(results);
        }, 2700 * Math.random());
      },
      //实现输入过滤功能
      createStateFilter(queryString) {
        return (state) => {
          return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) !== -1);
        };
      },
      unique(arr) {
        const res = new Map();
        return arr.filter((arr) => !res.has(arr.value) && res.set(arr.value, 1))
      },
      //跳转搜索页面
      toSearchResult(){
        if(this.searchText===''){
          this.$message.error({
            message:"搜索内容不能为空",
            showClose:true
          });
          return
        }
        this.$router.push({
          name:'Search',
          params:{queryString:this.searchText}
        })
      },
      //加载热门书籍到搜索建议里
      loadSearchSuggestion(){
        let searchArray=new Array();
        this.$ajax.get('http://localhost:8080/book/0')
          .then(response=>{
            response.data.forEach(ob=>{
              let o={'value':ob['title'],'title':ob['value']}
              searchArray.push(o)
            })
            this.searchSuggestions=searchArray
            //console.log(this.searchSuggestions);
          });
      }
    },
    mounted() {
      //初始化热门书籍到搜索建议里
      this.loadSearchSuggestion()
    },
    watch:{
      //监控搜索框的内容
      searchText(){
        //如果为空则加载热门分类到searchSuggestions
        if(this.searchText===''){
          this.loadSearchSuggestion()
        }else {
          let searchArray=new Array();
          this.$ajax.get('http://localhost:8080/book/findAllSearch/'+this.searchText)
            .then(response=>{
              console.log(response.data)
              response.data.forEach(ob=>{
                let o={'value':ob['title'],'title':ob['value']}
                let b={'value':ob['author'],'title':ob['value']}
                searchArray.push(o)
                searchArray.push(b)
              })
              this.searchSuggestions=searchArray
              //console.log(this.searchSuggestions);
            });
        }
      }
    }
  }
</script>

<style scoped>
  .firstNav {
    margin-top: 10px;
    margin-left: 60px;
  }
  .el-menu-demo {
    margin: auto;
    width: 730px;
  }
  .log {
    width: 342px;
  }
  .search {
    margin-top: 18px;
    margin-left: 10px;
    width: auto;
  }
  .search-input{
    width: 840px;
  }
  .hotSearch{
    color:#969696;
    margin-left: 0px;
    margin-right: 238px;
    font-size: 5px;
  }
  .button{
    width: auto;
    background: red;
    border-color: red;
  }
</style>


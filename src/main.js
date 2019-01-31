// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import store from './vuex'
import router from './router'
import axios from 'axios'

// 引入element-ui样式
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// 使用element-ui样式
Vue.use(ElementUI);

Vue.config.productionTip = false
Vue.prototype.$ajax = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  // components: { App },
  // template: '<App/>'
  render: h => h(App)
})



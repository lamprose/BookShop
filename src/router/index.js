import Vue from 'vue'
import Router from 'vue-router'
import Header from '@/components/Public/Header'
import Footer from '@/components/Public/Footer'
import MainBox from '@/components/Home/MainBox'
import Navigation from '@/components/Home/Navigation'

import ForgotPassword from '@/components/Register/ForgotPassword'
import Register from '@/components/Register/Register'

import AdminTop from '@/components/Admin/AdminTop'

import MessageBoard from '@/components/MessageS/MessageBoard'
import ShoppingTrolley from '@/components/MessageS/ShoppingTrolley'

import SearchList from '@/components/SearchList/SearchList'

import UserInfoManage from '@/components/User/userInfoManage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      components: {
        'header': Header,
        'main': MainBox,
        'footer':Footer
      },
      redirect:'/home',
      children: [
        {
          path:'/home',
          name:'Home',
          component:Navigation,
        },
        {
          path:'/register',
          name:'Register',
          component: Register
        },
        {
          path:'/forgetpassword',
          name:'ForgotPassword',
          component:ForgotPassword
        },
        {
          path:'/user/admin',
          name:'Admin',
          component:AdminTop
        },
        {
          path:'/messageboard',
          name:'MessageBoard',
          component:MessageBoard
        },
        {
          path:'/user/shoppingtrolley',
          name:'ShoppingTrolley',
          component:ShoppingTrolley
        },
        {
          path:'/search',
          name:'Search',
          component:SearchList
        },
        {
          path:'/user/userInfoManage',
          name: 'UserInfoManage',
          component:UserInfoManage,
        }
      ]
    },
  ]

})

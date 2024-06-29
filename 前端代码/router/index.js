import Vue from 'vue'
import VueRouter from 'vue-router'
// import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  // 会员管理
  {
    path: '/member',
    name: 'member',
    component: () => import('../views/surpermarket/memberView.vue')
  },
  //重定向
  {
    path: '/',
    redirect:'/member' //表示重定向到/emp即可
  },
  //订单管理
  {
    path: '/order/:id',
    name: 'OrderPage',
    component: () => import('../views/surpermarket/orderView.vue')
  },
  //商品管理
  {
    path: '/product',
    name: 'product',
    component: () => import('../views/surpermarket/productView.vue')
  },
  //购物处理
  {
    path: '/shopping',
    name: 'shopping',
    component: () => import('../views/surpermarket/shoppingView.vue')
  },
    //订单管理
    {
      path: '/order',
      name: 'order',
      component: () => import('../views/surpermarket/orderView.vue')
    },
    //销售情况汇总
    {
      path: '/salesSummary',
      name: 'salesSummary',
      component: () => import('../views/surpermarket/salesSummaryView.vue')
    },
]

const router = new VueRouter({
  routes
})

export default router

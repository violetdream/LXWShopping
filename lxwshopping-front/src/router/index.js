import Vue from 'vue';
import Router from 'vue-router';
import HelloWorld from '@/page/HelloWorld';
import Login from '@/page/Login/Login';
import Register from '@/page/Login/Register'
const Index = () => import('/page/index.vue')
const Home = () => import('/page/Home/home.vue')
const GoodS = () => import('/page/Goods/goods.vue')
const product = () => import('/page/Goods/goodsDetails.vue')
const Cart = () => import('/page/Cart/cart.vue')
const order = () => import('/page/Order/order.vue')
const user = () => import('/page/User/user.vue')
const orderList = () => import('/page/User/children/order.vue')
const information = () => import('/page/User/children/information.vue')
const addressList = () => import('/page/User/children/addressList.vue')
const coupon = () => import('/page/User/children/coupon.vue')
const aihuishou = () => import('/page/User/children/aihuishou.vue')
const support = () => import('/page/User/children/support.vue')
const checkout = () => import('/page/Checkout/checkout.vue')
const payment = () => import('/page/Order/payment.vue')
const paysuccess = () => import('/page/Order/paysuccess.vue')
const Search = () => import('/page/Search/search.vue')
const RefreshSearch = () => import('/page/Refresh/refreshsearch.vue')
const RefreshGoods = () => import('/page/Refresh/refreshgoods.vue')
const orderDetail = () => import('/page/User/children/orderDetail.vue')
const Alipay = () => import('/page/Order/alipay.vue')
const Wechat = () => import('/page/Order/wechat.vue')
const QQpay = () => import('/page/Order/qqpay.vue')

Vue.use(Router)

export default new Router({
  base: "/",//项目名称 访问路由页面都需要加上这个，访问根路径为http://ip:port/shopping/
  // mode: "history",//消去#
  routes: [
    {
      path: '/helloworld',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
    path: '/',
    component: Index,
    name: 'index',
    redirect: '/home',
    children: [
    {path: 'home', component: Home},
    {path: 'goods', component: GoodS},
    {path: 'goods/cate/:cateId', component: GoodS},
    {path: 'product/:productId', name: 'product', component: product},
    {path: '/refreshgoods', name: 'refreshgoods', component: RefreshGoods}
    ]},
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {path: '/cart', name: 'cart', component: Cart},
    {path: '/refreshsearch', name: 'refreshsearch', component: RefreshSearch},
    {path: '/search', name: 'search', component: Search},
    {
      path: '/order',
      name: 'order',
      component: order,
      children: [
        {path: 'paysuccess', name: 'paysuccess', component: paysuccess},
        {path: 'payment/:orderId', name: 'payment', component: payment},
        {path: 'alipay', name: 'alipay', component: Alipay},
        {path: 'wechat', name: 'wechat', component: Wechat},
        {path: 'qqpay', name: 'qqpay', component: QQpay}
      ]
    },
    {
      path: '/user',
      name: 'user',
      component: user,
      redirect: '/user/orderList',
      children: [
        {path: 'orderList', name: '订单列表', component: orderList},
        {path: 'orderDetail', name: '订单详情', component: orderDetail},
        {path: 'information', name: '账户资料', component: information},
        {path: 'addressList', name: '收货地址', component: addressList},
        {path: 'coupon', name: '我的优惠', component: coupon},
        {path: 'support', name: '售后服务', component: support},
        {path: 'aihuishou', name: '以旧换新', component: aihuishou}
      ]
    },
    {path: '/checkout/:productId?/:num?', name: 'checkout', component: checkout},
    {path: '*', redirect: '/home'}
  ]
})

webpackJsonp([2],{"/yc9":function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=s("qsHl"),i=s("PgMb"),r=s("VKKs"),n=s("Gu7T"),o=s.n(n),l={data:function(){return{content:""}},props:{endTime:{type:String,default:""},endText:{type:String,default:"已结束"},callback:{type:Function,default:null}},methods:{countdowm:function(t){var e=this,s=setInterval(function(){var a=new Date,i=t-a.getTime();if(i>0){var r=Math.floor(i/864e5),n=Math.floor(i/36e5%24),o=Math.floor(i/6e4%60),l=Math.floor(i/1e3%60);n=n<10?"0"+n:n,o=o<10?"0"+o:o,l=l<10?"0"+l:l;var d="";r>0&&(d=r+" 天 "+n+" 小时 "+o+" 分 "+l+" 秒"),r<=0&&n>0&&(d=n+" 小时 "+o+" 分 "+l+" 秒"),r<=0&&n<=0&&(d=o+" 分 "+l+" 秒"),e.content=d}else clearInterval(s),e.content=e.endText,e._callback()},1e3)},_callback:function(){this.callback&&this.callback instanceof Function&&this.callback.apply(this,o()(this))}},mounted:function(){this.countdowm(this.endTime)}},d={render:function(){var t=this.$createElement;return(this._self._c||t)("span",{attrs:{endTime:this.endTime,callback:this.callback,endText:this.endText}},[this._t("default",[this._v("\n    "+this._s(this.content)+"\n  ")])],2)},staticRenderFns:[]};var c=s("VU/8")(l,d,!1,function(t){s("ZHLo")},"data-v-52d5f0c7",null).exports,u={data:function(){return{orderList:[0],userId:"",orderStatus:0,orderId:"",userName:"",tel:"",streetName:"",orderTitle:"",createTime:"",payTime:"",closeTime:"",finishTime:"",orderTotal:"",loading:!0,countTime:0}},methods:{message:function(t){this.$message.error({message:t})},orderPayment:function(t){window.open(window.location.origin+"#/order/payment?orderId="+t)},goodsDetails:function(t){window.open(window.location.origin+"#/product/"+t)},_getOrderDet:function(){var t=this,e={params:{orderId:this.orderId}};Object(a.q)(e).then(function(e){"0"===e.result.orderStatus?t.orderStatus=1:"1"===e.result.orderStatus?t.orderStatus=2:"4"===e.result.orderStatus?t.orderStatus=5:"5"===e.result.orderStatus?t.orderStatus=-1:"6"===e.result.orderStatus&&(t.orderStatus=6),t.orderList=e.result.goodsList,t.orderTotal=e.result.orderTotal,t.userName=e.result.addressInfo.userName,t.tel=e.result.addressInfo.tel,t.streetName=e.result.addressInfo.streetName,t.createTime=e.result.createDate,t.closeTime=e.result.closeDate,t.payTime=e.result.payDate,5===t.orderStatus?t.finishTime=e.result.finishDate:t.countTime=e.result.finishDate,t.loading=!1})},_cancelOrder:function(){var t=this;Object(a.f)({orderId:this.orderId}).then(function(e){!0===e.success?t._getOrderDet():t.message("取消失败")})}},created:function(){this.userId=Object(r.a)("userId"),this.orderId=this.$route.query.orderId,this.orderTitle="订单号："+this.orderId,this._getOrderDet()},components:{YShelf:i.a,countDown:c}},v={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[s("y-shelf",{attrs:{title:t.orderTitle}},[s("div",{attrs:{slot:"content"},slot:"content"},[t.orderList.length?s("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticStyle:{"min-height":"10vw"},attrs:{"element-loading-text":"加载中..."}},[-1!==t.orderStatus&&6!==t.orderStatus?s("div",{staticClass:"orderStatus"},[s("el-steps",{attrs:{space:200,active:t.orderStatus}},[s("el-step",{attrs:{title:"下单",description:t.createTime}}),t._v(" "),s("el-step",{attrs:{title:"付款",description:t.payTime}}),t._v(" "),s("el-step",{attrs:{title:"配货",description:""}}),t._v(" "),s("el-step",{attrs:{title:"出库",description:""}}),t._v(" "),s("el-step",{attrs:{title:"交易成功",description:t.finishTime}})],1)],1):t._e(),t._v(" "),-1===t.orderStatus?s("div",{staticClass:"orderStatus-close"},[s("el-steps",{attrs:{space:780,active:2}},[s("el-step",{attrs:{title:"下单",description:t.createTime}}),t._v(" "),s("el-step",{attrs:{title:"交易关闭",description:t.closeTime}})],1)],1):t._e(),t._v(" "),6===t.orderStatus?s("div",{staticClass:"orderStatus-close"},[s("el-steps",{attrs:{space:780,active:2}},[s("el-step",{attrs:{title:"下单",description:t.createTime}}),t._v(" "),s("el-step",{attrs:{title:"交易关闭",description:t.closeTime}})],1)],1):t._e(),t._v(" "),1===t.orderStatus?s("div",{staticClass:"status-now"},[s("ul",[s("li",{staticClass:"status-title"},[s("h3",[t._v("订单状态：待付款")])]),t._v(" "),s("li",{staticClass:"button"},[s("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(e){return t.orderPayment(t.orderId)}}},[t._v("现在付款")]),t._v(" "),s("el-button",{attrs:{size:"small"},on:{click:function(e){return t._cancelOrder()}}},[t._v("取消订单")])],1)]),t._v(" "),s("p",{staticClass:"realtime"},[s("span",[t._v("您的付款时间还有 ")]),t._v(" "),s("span",{staticClass:"red"},[s("countDown",{attrs:{endTime:t.countTime,endText:"已结束"}})],1),t._v(" "),s("span",[t._v("，超时后订单将自动取消。")])])]):t._e(),t._v(" "),2===t.orderStatus?s("div",{staticClass:"status-now"},[s("ul",[s("li",{staticClass:"status-title"},[s("h3",[t._v("订单状态：已支付，待系统审核确认")])])]),t._v(" "),s("p",{staticClass:"realtime"},[s("span",[t._v("请耐心等待审核，审核结果将发送到您的邮箱，并且您所填写的捐赠数据将显示在捐赠表中。")])])]):t._e(),t._v(" "),-1===t.orderStatus||6===t.orderStatus?s("div",{staticClass:"status-now"},[s("ul",[s("li",{staticClass:"status-title"},[s("h3",[t._v("订单状态：已关闭")])])]),t._v(" "),s("p",{staticClass:"realtime"},[s("span",[t._v("您的订单已关闭。")])])]):t._e(),t._v(" "),5===t.orderStatus?s("div",{staticClass:"status-now"},[s("ul",[s("li",{staticClass:"status-title"},[s("h3",[t._v("订单状态：已完成")])])]),t._v(" "),s("p",{staticClass:"realtime"},[s("span",[t._v("您的订单已交易成功，感谢您的惠顾！")])])]):t._e(),t._v(" "),s("div",{staticClass:"gray-sub-title cart-title"},[s("div",{staticClass:"first"},[s("div",[s("span",{staticClass:"order-id"},[t._v("商品名称")])]),t._v(" "),s("div",{staticClass:"f-bc"},[s("span",{staticClass:"price"},[t._v("单价")]),t._v(" "),s("span",{staticClass:"num"},[t._v("数量")]),t._v(" "),s("span",{staticClass:"operation"},[t._v("小计")])])])]),t._v(" "),s("div",{staticClass:"goods-table"},t._l(t.orderList,function(e,a){return s("div",{key:a,staticClass:"cart-items"},[s("a",{staticClass:"img-box",on:{click:function(s){return t.goodsDetails(e.productId)}}},[s("img",{attrs:{src:e.productImg,alt:""}})]),t._v(" "),s("div",{staticClass:"name-cell ellipsis"},[s("a",{attrs:{title:"",target:"_blank"},on:{click:function(s){return t.goodsDetails(e.productId)}}},[t._v(t._s(e.productName))])]),t._v(" "),s("div",{staticClass:"n-b"},[s("div",{staticClass:"price"},[t._v("¥ "+t._s(Number(e.salePrice).toFixed(2)))]),t._v(" "),s("div",{staticClass:"goods-num"},[t._v(t._s(e.productNum))]),t._v(" "),s("div",{staticClass:"subtotal"},[t._v(" ¥ "+t._s(Number(e.salePrice*e.productNum).toFixed(2)))])])])}),0),t._v(" "),s("div",{staticClass:"order-discount-line"},[s("p",{staticStyle:{"font-size":"14px","font-weight":"bolder"}},[s("span",{staticStyle:{"padding-right":"47px"}},[t._v("商品总计：")]),t._v(" "),s("span",{staticStyle:{"font-size":"16px","font-weight":"500","line-height":"32px"}},[t._v("¥ "+t._s(t.orderTotal))])]),t._v(" "),s("p",[s("span",{staticStyle:{"padding-right":"30px"}},[t._v("运费：")]),s("span",{staticStyle:{"font-weight":"700"}},[t._v("+ ¥ 0.00")])]),t._v(" "),s("p",{staticClass:"price-total"},[s("span",[t._v("应付金额：")]),s("span",{staticClass:"price-red"},[t._v("¥ "+t._s(t.orderTotal))])])]),t._v(" "),s("div",{staticClass:"gray-sub-title cart-title"},[s("div",{staticClass:"first"},[s("div",[s("span",{staticClass:"order-id"},[t._v("收货信息")])])])]),t._v(" "),s("div",{staticStyle:{height:"155px",padding:"20px 30px"}},[s("p",{staticClass:"address"},[t._v("姓名："+t._s(t.userName))]),t._v(" "),s("p",{staticClass:"address"},[t._v("联系电话："+t._s(t.tel))]),t._v(" "),s("p",{staticClass:"address"},[t._v("详细地址："+t._s(t.streetName))])])]):s("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],attrs:{"element-loading-text":"加载中..."}},[s("div",{staticStyle:{padding:"100px 0","text-align":"center"}},[t._v("\n          获取该订单信息失败\n        ")])])])])],1)},staticRenderFns:[]};var p=s("VU/8")(u,v,!1,function(t){s("XaX5")},"data-v-5f4d6571",null);e.default=p.exports},"5zde":function(t,e,s){s("zQR9"),s("qyJz"),t.exports=s("FeBl").Array.from},Gu7T:function(t,e,s){"use strict";e.__esModule=!0;var a,i=s("c/Tr"),r=(a=i)&&a.__esModule?a:{default:a};e.default=function(t){if(Array.isArray(t)){for(var e=0,s=Array(t.length);e<t.length;e++)s[e]=t[e];return s}return(0,r.default)(t)}},XaX5:function(t,e){},ZHLo:function(t,e){},"c/Tr":function(t,e,s){t.exports={default:s("5zde"),__esModule:!0}},fBQ2:function(t,e,s){"use strict";var a=s("evD5"),i=s("X8DO");t.exports=function(t,e,s){e in t?a.f(t,e,i(0,s)):t[e]=s}},qyJz:function(t,e,s){"use strict";var a=s("+ZMJ"),i=s("kM2E"),r=s("sB3e"),n=s("msXi"),o=s("Mhyx"),l=s("QRG4"),d=s("fBQ2"),c=s("3fs2");i(i.S+i.F*!s("dY0y")(function(t){Array.from(t)}),"Array",{from:function(t){var e,s,i,u,v=r(t),p="function"==typeof this?this:Array,_=arguments.length,f=_>1?arguments[1]:void 0,m=void 0!==f,h=0,g=c(v);if(m&&(f=a(f,_>2?arguments[2]:void 0,2)),void 0==g||p==Array&&o(g))for(s=new p(e=l(v.length));e>h;h++)d(s,h,m?f(v[h],h):v[h]);else for(u=g.call(v),s=new p;!(i=u.next()).done;h++)d(s,h,m?n(u,f,[i.value,h],!0):i.value);return s.length=h,s}})}});
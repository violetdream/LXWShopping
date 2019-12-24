package com.personal.order.dal.entitys;

import lombok.Data;


@Data
public class OrderItem {


    private String id;



    /**

     * 商品id

     */


    private Long itemId;



    /**

     * 订单id

     */


    private String orderId;



    /**

     * 商品购买数量

     */

    private Integer num;



    /**

     * 商品标题

     */

    private String title;



    /**

     * 商品单价

     */

    private Double price;



    /**

     * 商品总金额

     */


    private Double totalFee;



    /**

     * 商品图片地址

     */


    private String picPath;

    /**
     * 库存锁定状态 1库存已锁定 2库存已释放 3-库存减扣成功
     */
    private Integer status;

}

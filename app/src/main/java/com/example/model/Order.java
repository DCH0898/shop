package com.example.model;

/**
 * Created by Administrator on 2017/4/27.
 */

public class Order {
    private String shopName;// 商店名

    public String getShoppingfreight() {
        return shoppingfreight;
    }

    public void setShoppingfreight(String shoppingfreight) {
        this.shoppingfreight = shoppingfreight;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShoppingName() {
        return shoppingName;
    }

    public void setShoppingName(String shoppingName) {
        this.shoppingName = shoppingName;
    }

    public String getShoppingDetail() {
        return shoppingDetail;
    }

    public void setShoppingDetail(String shoppingDetail) {
        this.shoppingDetail = shoppingDetail;
    }

    public int getOrderStyle() {
        return orderStyle;
    }

    public void setOrderStyle(int orderStyle) {
        this.orderStyle = orderStyle;
    }

    public String getShoppingImageUrl() {
        return shoppingImageUrl;
    }

    public void setShoppingImageUrl(String shoppingImageUrl) {
        this.shoppingImageUrl = shoppingImageUrl;
    }

    public String getShoppingFlavor() {
        return shoppingFlavor;
    }

    public void setShoppingFlavor(String shoppingFlavor) {
        this.shoppingFlavor = shoppingFlavor;
    }

    public int getShoppingNum() {
        return shoppingNum;
    }

    public void setShoppingNum(int shoppingNum) {
        this.shoppingNum = shoppingNum;
    }

    public String getShoppingAccount() {
        return shoppingAccount;
    }

    public void setShoppingAccount(String shoppingAccount) {
        this.shoppingAccount = shoppingAccount;
    }

    public String getShoppingPrice() {
        return shoppingPrice;
    }

    public void setShoppingPrice(String shoppingPrice) {
        this.shoppingPrice = shoppingPrice;
    }

    public String getShoppingBeforePrice() {
        return shoppingBeforePrice;
    }

    public void setShoppingBeforePrice(String shoppingBeforePrice) {
        this.shoppingBeforePrice = shoppingBeforePrice;
    }

    private String shoppingName;// 商品名
    private String shoppingDetail;// 商品详情
    private int orderStyle;// 订单状态
    private String shoppingImageUrl;// 商品图片地址
    private String shoppingFlavor;// 商品口味
    private int shoppingNum;// 商品数量
    private String shoppingAccount;// 商品总价
    private String shoppingPrice;// 商品价格
    private String shoppingBeforePrice;// 商品优惠前价格
    private String shoppingfreight;// 运费

}

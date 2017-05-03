package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Li on 2017/4/27.
 */

public class Shopping implements Parcelable {
    private String shoppingName;
    private String shoppingDetail;
    private String shoppingFlavor;
    private String shoppingPrice;
    private String shoppingNum;
    private String shoppingBeforePrice;

    protected Shopping(Parcel in) {
        shoppingName = in.readString();
        shoppingDetail = in.readString();
        shoppingFlavor = in.readString();
        shoppingPrice = in.readString();
        shoppingNum = in.readString();
        shoppingBeforePrice = in.readString();
        isChoose = in.readByte() != 0;
        shoppingImgUrl = in.readString();
        shopName = in.readString();
    }

    public static final Creator<Shopping> CREATOR = new Creator<Shopping>() {
        @Override
        public Shopping createFromParcel(Parcel in) {
            return new Shopping(in);
        }

        @Override
        public Shopping[] newArray(int size) {
            return new Shopping[size];
        }
    };

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    private boolean isChoose;

    public Shopping() {
    }

    public String getShoppingBeforePrice() {
        return shoppingBeforePrice;
    }

    public void setShoppingBeforePrice(String shoppingBeforePrice) {
        this.shoppingBeforePrice = shoppingBeforePrice;
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

    public String getShoppingFlavor() {
        return shoppingFlavor;
    }

    public void setShoppingFlavor(String shoppingFlavor) {
        this.shoppingFlavor = shoppingFlavor;
    }

    public String getShoppingPrice() {
        return shoppingPrice;
    }

    public void setShoppingPrice(String shoppingPrice) {
        this.shoppingPrice = shoppingPrice;
    }

    public String getShoppingNum() {
        return shoppingNum;
    }

    public void setShoppingNum(String shoppingNum) {
        this.shoppingNum = shoppingNum;
    }

    public String getShoppingImgUrl() {
        return shoppingImgUrl;
    }

    public void setShoppingImgUrl(String shoppingImgUrl) {
        this.shoppingImgUrl = shoppingImgUrl;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    private String shoppingImgUrl;
    private String shopName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shoppingName);
        dest.writeString(shoppingDetail);
        dest.writeString(shoppingFlavor);
        dest.writeString(shoppingPrice);
        dest.writeString(shoppingNum);
        dest.writeString(shoppingBeforePrice);
        dest.writeByte((byte) (isChoose ? 1 : 0));
    }
}

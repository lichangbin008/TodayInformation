package com.lcb.todayinformation.main.shanghai.dto;

import java.util.ArrayList;

/**
 * Created by ${lichangbin} on 2019/10/27.
 */

public class ShanghaiBean {

    private boolean isShowImg;

    private int itemType = IShanghaiItemType.VERTICAL;

    private String dec;

    private ArrayList<ShanghaiBean> data;

    public boolean isShowImg() {
        return isShowImg;
    }

    public ShanghaiBean setShowImg(boolean showImg) {
        isShowImg = showImg;
        return this;
    }

    public int getItemType() {
        return itemType;
    }

    public ShanghaiBean setItemType(int itemType) {
        this.itemType = itemType;
        return this;
    }

    public String getDec() {
        return dec;
    }

    public ShanghaiBean setDec(String dec) {
        this.dec = dec;
        return this;
    }

    public ArrayList<ShanghaiBean> getData() {
        return data;
    }

    public ShanghaiBean setData(ArrayList<ShanghaiBean> data) {
        this.data = data;
        return this;
    }

    public interface IShanghaiItemType {
        int VERTICAL = 0;

        int HORIZONTAL = 1;
    }
}

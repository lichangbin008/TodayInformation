package com.lcb.todayinformation.main.shanghai.dto;

import java.util.ArrayList;

/**
 * Created by ${lichangbin} on 2019/10/27.
 */

public class ShanghaiDataManager {

    /**
     * 获取竖向数据
     *
     * @param len
     * @return
     */
    private static ArrayList<ShanghaiBean> getVerData(int len) {
        ArrayList<ShanghaiBean> data = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            ShanghaiBean shanghaiBean = new ShanghaiBean();
            shanghaiBean.setDec("上海欢迎您").setShowImg(false);
            data.add(shanghaiBean);
        }
        return data;
    }

    /**
     * 获取横向数据
     *
     * @param len
     * @return
     */
    private static ArrayList<ShanghaiBean> getHorData(int len) {
        ArrayList<ShanghaiBean> data = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            ShanghaiBean shanghaiBean = new ShanghaiBean();
            shanghaiBean.setDec("上海是魔都").setShowImg(true);
            data.add(shanghaiBean);
        }
        return data;
    }

    /**
     * 获取数据
     * @return
     */
    public static ArrayList<ShanghaiBean> getData(){
        ArrayList<ShanghaiBean> data = new ArrayList<>();
        data.addAll(getVerData(5));
        data.add(new ShanghaiBean().setData(
                getHorData(10)).setItemType(ShanghaiBean.IShanghaiItemType.HORIZONTAL));
        data.addAll(getVerData(5));
        data.add(new ShanghaiBean().setData(
                getHorData(10)).setItemType(ShanghaiBean.IShanghaiItemType.HORIZONTAL));
        return data;
    }
}

package com.lcb.todayinformation.main.hangzhou.adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.main.shanghai.dto.ShanghaiBean;
import com.lcb.todayinformation.main.shanghai.dto.ShanghaiDetailBean;
import com.lcb.todayinformation.main.shanghai.view.ShanghaiDetailActivity;

import java.util.ArrayList;

/**
 * Created by ${lichangbin} on 2019/10/26.
 */

public class ZhiHuAdapter extends RecyclerView.Adapter {

    private final ArrayList<ShanghaiDetailBean.XiaoHuaBean> data;

    public ZhiHuAdapter(ArrayList<ShanghaiDetailBean.XiaoHuaBean> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建View，然后进行缓存
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_fragment_shanghai, null);
        ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 绑定数据
        ShanghaiDetailBean.XiaoHuaBean shanghaiBean = data.get(position);
        ((ShanghaiViewHolder) holder).tvContent.setText(shanghaiBean.content);
        ((ShanghaiViewHolder) holder).ivShanghai.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ShanghaiViewHolder extends RecyclerView.ViewHolder {

        public TextView tvContent;

        public ImageView ivShanghai;

        public ShanghaiViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_shanghai_content);
            ivShanghai = itemView.findViewById(R.id.iv_item_shanghai);
        }
    }


}

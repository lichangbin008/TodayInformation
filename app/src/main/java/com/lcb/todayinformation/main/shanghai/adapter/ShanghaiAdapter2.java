package com.lcb.todayinformation.main.shanghai.adapter;

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
import com.lcb.todayinformation.main.shanghai.view.ShanghaiDetailActivity;

import java.util.ArrayList;

/**
 * Created by ${lichangbin} on 2019/10/26.
 */

public class ShanghaiAdapter2 extends RecyclerView.Adapter {


    public ShanghaiAdapter2() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建View，然后进行缓存
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_shanghai_cardview, null);
        ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class ShanghaiViewHolder extends RecyclerView.ViewHolder {

        public ShanghaiViewHolder(View itemView) {
            super(itemView);
        }
    }


}

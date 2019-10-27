package com.lcb.todayinformation.main.shanghai.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.main.shanghai.dto.ShanghaiBean;

import java.util.ArrayList;

/**
 * Created by ${lichangbin} on 2019/10/26.
 */

public class ShanghaiAdapter extends RecyclerView.Adapter {

    private ArrayList<ShanghaiBean> data;

    private Context context;

    public ShanghaiAdapter(Context context, ArrayList<ShanghaiBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建View，然后进行缓存
        if (viewType == ShanghaiBean.IShanghaiItemType.VERTICAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_fragment_shanghai, parent, false);
            ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(view);
            return viewHolder;
        } else if (viewType == ShanghaiBean.IShanghaiItemType.HORIZONTAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_fragment_shanghai_rv, null);
            ShanghaiViewHolderRv viewHolder = new ShanghaiViewHolderRv(view);
            return viewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 绑定数据
        ShanghaiBean shanghaiBean = data.get(position);
        if (holder instanceof ShanghaiViewHolder) {
            ((ShanghaiViewHolder) holder).tvContent.setText(shanghaiBean.getDec());
            ((ShanghaiViewHolder) holder).ivShanghai.setVisibility(
                    shanghaiBean.isShowImg() ? View.VISIBLE : View.GONE);
        } else if (holder instanceof ShanghaiViewHolderRv) {
            ((ShanghaiViewHolderRv) holder).recyclerView.setLayoutManager(
                    new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            ((ShanghaiViewHolderRv) holder).recyclerView.setAdapter(
                    new ShanghaiAdapter(context, shanghaiBean.getData()));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getItemType();
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

    public class ShanghaiViewHolderRv extends RecyclerView.ViewHolder {

        public RecyclerView recyclerView;

        public ShanghaiViewHolderRv(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv_item_shanghai);
        }
    }


}

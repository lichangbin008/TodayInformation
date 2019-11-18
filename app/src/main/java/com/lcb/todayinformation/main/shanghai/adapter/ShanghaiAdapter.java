package com.lcb.todayinformation.main.shanghai.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.main.shanghai.dto.ShanghaiBean;
import com.lcb.todayinformation.main.shanghai.view.ShanghaiDetailActivity;

import java.util.ArrayList;

/**
 * Created by ${lichangbin} on 2019/10/26.
 */

public class ShanghaiAdapter extends RecyclerView.Adapter {

    private boolean isHor;
    private ArrayList<ShanghaiBean> data;

    private Activity context;

    private RecyclerView.RecycledViewPool recycledViewPool;

    public ShanghaiAdapter(Activity context, ArrayList<ShanghaiBean> data, boolean isHor) {
        this.context = context;
        this.data = data;
        this.isHor = isHor;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建View，然后进行缓存
        if (viewType == ShanghaiBean.IShanghaiItemType.VERTICAL) {
            if (isHor) {
                Log.e("onCreateViewHolder", "viewType VERTICAL");
            }
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
            ((ShanghaiViewHolder) holder).itemView.setTag(position);
        } else if (holder instanceof ShanghaiViewHolderRv) {
            ((ShanghaiViewHolderRv) holder).recyclerView.setAdapter(
                    new ShanghaiAdapter(context, shanghaiBean.getData(), true));
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int position = (int) v.getTag();
//                    Toast.makeText(context, "我被点击" + position, Toast.LENGTH_SHORT).show();
                    ShanghaiDetailActivity.start_5_0(context, ivShanghai);
                }
            });
        }
    }

    public class ShanghaiViewHolderRv extends RecyclerView.ViewHolder {

        public RecyclerView recyclerView;

        public ShanghaiViewHolderRv(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv_item_shanghai);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    context, LinearLayoutManager.HORIZONTAL, false);
            linearLayoutManager.setRecycleChildrenOnDetach(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setRecycledViewPool(recycledViewPool);
        }
    }


}

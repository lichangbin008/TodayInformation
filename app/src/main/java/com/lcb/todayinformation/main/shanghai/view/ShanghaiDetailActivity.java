package com.lcb.todayinformation.main.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.base.BaseActivity;
import com.lcb.todayinformation.base.ViewInject;
import com.lcb.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.lcb.todayinformation.main.shanghai.manager.GetXiaoHuaTask;
import com.lcb.todayinformation.main.shanghai.module.ShanghaiDetailHttpTask;
import com.lcb.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;


import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ${lichangbin} on 2019/10/28.
 */

@ViewInject(mainlayoutid = R.layout.activity_shanghai_detail)
public class ShanghaiDetailActivity extends BaseActivity implements IShanghaiDetailContract.IView{

    IShanghaiDetailContract.IPresenter presenter = new ShanghaiDetailPresenter(this);

    private static String activityOptionsCompat = "ShanghaiDetailActivity";

    @BindView(R.id.iv_shanghai_detail)
    ImageView ivShanghaiDetail;

    @Override
    public void afterBindView() {
        initAnima();
        initGetNetData();
//        initPostNetData();
    }

    /**
     * 发送网络请求Post
     */
    private void initPostNetData() {
        OkHttpClient client = new OkHttpClient(); // okhttp 配置一些默认
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key", "77bab10f51911504dc087d876b620d7f");
        Request request = new Request.Builder().url("http://apis.juhe.cn/lottery/types").post(builder.build()).build(); // 建造者设计模式
        Call call = client.newCall(request);
        call.enqueue(new Callback() { // 异步请求
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("initPostNetData", "onFailure" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("initPostNetData", "onResponse" + response.body().string());
            }
        });
    }

    /**
     * 发送网络请求Get
     */
    private void initGetNetData() {

        presenter.getNetData();
//        GetXiaoHuaTask task = new GetXiaoHuaTask();
//        task.execute("desc", "1", "1");

//        Object desc = new ShanghaiDetailHttpTask().getXiaoHuaList("desc", "1", "1");
//        if (desc instanceof Response) {
//            Response response = (Response) desc;
//            Log.e("initGetNetData", "response:" + response.body().toString());
//        }
//        // 1.可以隔离
//        OkHttpClient client = new OkHttpClient(); // okhttp 配置一些默认
//
//        // 2.构建请求，url和参数
//        HttpUrl.Builder builder = HttpUrl.parse("http://v.juhe.cn/joke/content/list.php").newBuilder();
//        builder.addQueryParameter("sort", "desc");
//        builder.addQueryParameter("page", "1");
//        builder.addQueryParameter("pagesize", "2");
//        builder.addQueryParameter("time", "" + System.currentTimeMillis() / 1000);
//        builder.addQueryParameter("key", "51280ee59e42e870feb0012ea7fc5628");
//
//        // 3.构建Request
//        Request request = new Request.Builder().url(builder.build()).get().build(); // 建造者设计模式
//        // 4.构建Call
//        Call call = client.newCall(request);
//        // 5.网络请求
//        call.enqueue(new Callback() { // 异步请求
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("initGetNetData", "onFailure" + e);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.e("initGetNetData", "onResponse" + response.body().string());
//            }
//        });
    }

    private void initAnima() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            ViewCompat.setTransitionName(ivShanghaiDetail, activityOptionsCompat);
            // 启动转场动画
            startPostponedEnterTransition();
        }
    }

    /**
     * 用于Android系统5.0界面转场动画，共享元素动画
     */
    public static void start_5_0(Activity activity, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Pair pair = new Pair(view, activityOptionsCompat);
            Intent intent = new Intent(activity, ShanghaiDetailActivity.class);
            ActivityOptionsCompat activityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
            ActivityCompat.startActivity(activity, intent, activityOptionsCompat.toBundle());
        }

    }
}

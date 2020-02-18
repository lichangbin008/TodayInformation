package com.lcb.todayinformation.main.shanghai.view;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.base.BaseActivity;
import com.lcb.todayinformation.base.ViewInject;
import com.lcb.todayinformation.main.beijing.MainProcessService;
import com.lcb.todayinformation.main.shanghai.dto.ShanghaiDetailBean;
import com.lcb.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.lcb.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;
import com.web.god.ipc.ICallback;
import com.web.god.ipc.result.IResult;
import com.web.god.ipc.IpcManager;
import com.web.god.ipc.request.IpcRequest;


import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ${lichangbin} on 2019/10/28.
 */

@ViewInject(mainlayoutid = R.layout.activity_shanghai_detail)
public class ShanghaiDetailActivity extends BaseActivity implements IShanghaiDetailContract.IView {

    IShanghaiDetailContract.IPresenter presenter = new ShanghaiDetailPresenter(this);

    private static String activityOptionsCompat = "ShanghaiDetailActivity";

    @BindView(R.id.iv_shanghai_detail)
    ImageView ivShanghaiDetail;
//    private GetProcessReceiver getProcessReceiver;

    private Messenger messenger;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            Log.e(activityOptionsCompat, "processDec = " + data.getString("process"));
        }
    };

    private Messenger messengerClient = new Messenger(handler);

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Message msg = new Message();
            msg.what = MainProcessService.SHANGHAI_DETAIL;
            msg.replyTo = messengerClient;
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void afterBindView() {
//        initReceiver();
//        initProcessData();
        initAnima();
        initGetNetData();
        initIpc();
//        initProcessService();
//        initProviderData();
//        initPostNetData();
//        ivShanghaiDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String s = null;
//                s.toString();
//            }
//        });
    }

    private void initIpc() {
        Log.e("activityOptionsCompat", "initIpc");
        IpcRequest ipcRequest = new IpcRequest("shanghai_detail");
        IpcManager.getInstance(this).excuteAsync(ipcRequest, new ICallback() {
            @Override
            public void callback(IResult result) {
                String data = result.data();
                Log.e("activityOptionsCompat", data);
            }
        });
    }

    private void initProcessService() {
        Intent intent = new Intent(this, MainProcessService.class);
        bindService(intent, connection, Service.BIND_AUTO_CREATE);
    }


//    private void initProviderData() {
//        Uri insert = getContentResolver().insert(
//                Uri.parse("content://com.news.today.process.data"), new ContentValues());
//        Log.e(activityOptionsCompat, "processDec = " + insert.toString());
//    }

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
        ivShanghaiDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getNetData(1);
            }
        });

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

    @Override
    public void showData(ShanghaiDetailBean data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(getProcessReceiver);
    }

//    private void initReceiver() {
//        getProcessReceiver = new GetProcessReceiver();
//        registerReceiver(getProcessReceiver, new IntentFilter("beijing_post_process_data"));
//    }

//    private void initProcessData() {
//        Intent intent = new Intent("shanghai_get_process_data");
//        sendBroadcast(intent);
//    }

//    private class GetProcessReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String processDec = intent.getStringExtra("processDec");
//            Log.e(activityOptionsCompat, "processDec = " + processDec);
//        }
//    }
}

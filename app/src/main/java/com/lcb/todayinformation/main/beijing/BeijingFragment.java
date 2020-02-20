package com.lcb.todayinformation.main.beijing;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lcb.todayinformation.R;
import com.lcb.todayinformation.base.BaseFragment;
import com.lcb.todayinformation.base.ViewInject;
import com.lcb.todayinformation.main.shanghai.view.ShanghaiDetailActivity;

import butterknife.BindView;

/**
 * Created by ${lichangbin} on 2019/10/23.
 */

@ViewInject(mainlayoutid = R.layout.fragment_beijing)
public class BeijingFragment extends BaseFragment {

    private static final String TAG = "BeijingFragment";

    @BindView(R.id.bt_paly)
    Button tvClick;
    @BindView(R.id.perimision)
    Button btClick;

//    private ProcessDataReceiver processDataReceiver;

    @Override
    public void afterBindView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context, MainProcessService.class));
        } else {
            context.startService(new Intent(context,MainProcessService.class));
        }
        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去上海
                ProcessDataTest.getInstance().setProcessDec("你好，我来自北京");
                ShanghaiDetailActivity.start_5_0(getActivity(), tvClick);
            }
        });

        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int state = getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (state == PackageManager.PERMISSION_GRANTED) {
                        Log.e("BeiJingFragment", "权限已经申请");
                    } else {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                        Log.e("BeiJingFragment", "权限没有申请");
                    }
                }
            }
        });
//        processDataReceiver = new ProcessDataReceiver();
//        getActivity().registerReceiver(
//                processDataReceiver, new IntentFilter("shanghai_get_process_data"));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        getActivity().unregisterReceiver(processDataReceiver);
        context.stopService(new Intent(context, MainProcessService.class));
    }

//    private class ProcessDataReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String processDec = ProcessDataTest.getInstance().getProcessDec();
//            Intent postIntent = new Intent("beijing_post_process_data");
//            postIntent.putExtra("processDec", processDec);
//            getActivity().sendBroadcast(postIntent);
//        }
//    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e(TAG, grantResults[0] + " ");
    }
}

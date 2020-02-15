package com.lcb.todayinformation.main.beijing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
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

    @BindView(R.id.bt_paly)
    Button tvClick;

//    private ProcessDataReceiver processDataReceiver;

    @Override
    public void afterBindView() {
        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去上海
                ProcessDataTest.getInstance().setProcessDec("你好，我来自北京");
                ShanghaiDetailActivity.start_5_0(getActivity(), tvClick);
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
}

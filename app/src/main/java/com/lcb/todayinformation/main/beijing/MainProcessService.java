package com.lcb.todayinformation.main.beijing;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.lcb.todayinformation.R;

/**
 * Created by ${lichangbin} on 2020/2/15.
 */
public class MainProcessService extends Service {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHANGHAI_DETAIL:
                    Messenger replyTo = msg.replyTo;
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("process", ProcessDataTest.getInstance().getProcessDec());
                    message.setData(bundle);
                    try {
                        replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    public static final int SHANGHAI_DETAIL = 0;

    // Service跨进程通信的原理就是Messenger
    private Messenger messenger = new Messenger(handler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("mainProcess","test",NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(channel);

            Notification notification = new Notification.Builder(this,"mainProcess")
                    .setContentTitle("标题")
                    .setContentText("内容")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build();
            //前台服务，通知栏
            startForeground(1,notification);
        }
    }
}

package com.web.god.ipc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.web.god.ipc.result.IResult;
import com.web.god.ipc.result.IpcResult;
import com.web.god.ipc.request.IRequest;
import com.web.god.ipc.server.IpcService;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by ${lichangbin} on 2020/2/16.
 */
public class IpcManager {

    private static IpcManager mInstance;
    private final Context context;

    //只针对异步请求
    private Set<IRequest> mRequests = new TreeSet<IRequest>();
    private Set<IRequest> mWaitRequests = new TreeSet<IRequest>();
    private int mConnectStatus = IConnectStatus.STATUS_UNBIND;
    private ServiceConnection connection;

    private IServerInterface mServer;
    private IBinder.DeathRecipient mDeathRecipient;
    private IClientInterface.Stub mClientInterface;

    interface IConnectStatus {
        int STATUS_UNBIND = 0;
        int STATUS_BINDING = 1;
        int STATUS_BIND = 2;
    }

    private IpcManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public static synchronized IpcManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new IpcManager(context);
        }
        return mInstance;
    }

    // 同步执行
    public IResult excuteSync(IRequest request) {
        if (TextUtils.isEmpty(request.getRequestKey()) || mRequests.contains(request)) {
            return IpcResult.getErrorResult();
        }

        // 判断服务是否已经连接成功
        if (mConnectStatus != IConnectStatus.STATUS_BIND) {
            connectService();
            return IpcResult.getErrorResult();
        }
        return excute(request);
    }

    // TODO: 2019/9/8 提供给客户端  建立连接的一个方法
    public void initConnet() {
        // 大家可以自由完善
    }

    // 异步跨进
    public void excuteAsync(IRequest request, ICallback callback) {
        Log.e("activityOptionsCompat", "excuteAsync");
        if (TextUtils.isEmpty(request.getRequestKey()) || mRequests.contains(request)) {
            callback.callback(IpcResult.getErrorResult());
            return;
        }

        request.addCallback(callback);
        // 保存这个request
        mRequests.add(request);

        // 判断服务是否已经连接成功
        if (mConnectStatus != IConnectStatus.STATUS_BIND) {
            Log.e("activityOptionsCompat", "before connectService");
            connectService();
            mWaitRequests.add(request);
            return;
        }

        excute(request);
    }

    /**
     * 建立IPC通信连接
     */
    private void connectService() {
        if (connection == null) {
            connection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.e("activityOptionsCompat", "connectService onServiceConnected");
                    mConnectStatus = IConnectStatus.STATUS_BIND;
                    mServer = IServerInterface.Stub.asInterface(service);

                    // 往服务端注入接口
                    try {
                        mServer.registerCallback(mClientInterface);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    //Binder通信的死亡通，有重启逻辑
                    try {
                        service.linkToDeath(mDeathRecipient, 0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    // 连接成功之后去把等待的数据请求发送
                    for (IRequest request : mWaitRequests) {
                        excute(request);
                    }
                    mWaitRequests.clear();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    mConnectStatus = IConnectStatus.STATUS_UNBIND;
                }
            };
        }

        if (mDeathRecipient == null) {
            mDeathRecipient = new IBinder.DeathRecipient() {
                @Override
                public void binderDied() {
                    mConnectStatus = IConnectStatus.STATUS_UNBIND;
                    // 针对异步请求做Callback处理
                    for (IRequest request : mRequests) {
                        request.getCallback().callback(IpcResult.getErrorResult());
                    }
                    mRequests.clear();
                }
            };
        }

        if (mClientInterface == null) {
            mClientInterface = new IClientInterface.Stub() {
                @Override
                public void callback(String requestKey, String resultStr) throws RemoteException {
                    Iterator<IRequest> iterator = mRequests.iterator();
                    while (iterator.hasNext()) {
                        IRequest next = iterator.next();
                        if (TextUtils.equals(next.getRequestKey(), requestKey)) {
                            next.getCallback().callback(IpcResult.getSuccessResult(resultStr));
                            mRequests.remove(next);
                            return;
                        }
                    }
                }
            };
        }

        Intent intent = new Intent(context, IpcService.class);
        context.bindService(intent, connection, Service.BIND_AUTO_CREATE);
        mConnectStatus = IConnectStatus.STATUS_BINDING;
    }

    /**
     * 实际跨进程通信方法
     *
     * @param request
     */
    private IResult excute(IRequest request) {
        if (request.getCallback() != null) {
            try {
                // 这里面待解决 AIDL回调问题
                mServer.excuteAsync(request.getRequestKey(), request.getParams());
            } catch (RemoteException e) {
                request.getCallback().callback(IpcResult.getErrorResult());
            }
        } else {
            try {
                String result = mServer.excuteSync(request.getRequestKey(), request.getParams());
                return IpcResult.getSuccessResult(result);
            } catch (RemoteException e) {
                return IpcResult.getErrorResult();
            }
        }
        return IpcResult.getErrorResult();
    }
}

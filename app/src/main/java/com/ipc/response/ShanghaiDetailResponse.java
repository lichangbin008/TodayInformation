package com.ipc.response;

import android.os.RemoteException;

import com.web.god.ipc.IClientInterface;
import com.web.god.ipc.response.BaseResponse;

/**
 * Created by ${lichangbin} on 2020/2/19.
 */
public class ShanghaiDetailResponse extends BaseResponse {

    public ShanghaiDetailResponse(String requestKey, String params, IClientInterface iClientInterface) {
        super(requestKey, params, iClientInterface);
    }

    public void shanghaiDetail(){
        try {
            iClientInterface.callback(requestKey,"来自远方的祝福");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

// IServerInterface.aidl
package com.web.god.ipc;

// Declare any non-default types here with import statements
import com.web.god.ipc.IClientInterface;

interface IServerInterface {

    void excuteAsync(String requestKey,String requestParmas);

    String excuteSync(String requestKey,String requestParmas);

    void registerCallback(IClientInterface iClientInterface);
}

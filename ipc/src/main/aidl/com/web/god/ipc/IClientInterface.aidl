// IClientInterface.aidl
package com.web.god.ipc;

// Declare any non-default types here with import statements

interface IClientInterface {

    void callback(String requestKey, String resultStr);
}

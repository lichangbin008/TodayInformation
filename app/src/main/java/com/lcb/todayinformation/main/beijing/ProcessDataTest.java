package com.lcb.todayinformation.main.beijing;

/**
 * Created by ${lichangbin} on 2020/2/14.
 */
public class ProcessDataTest {

    private static ProcessDataTest mInstance;

    private String processDec;

    public static synchronized ProcessDataTest getInstance(){
        if (mInstance == null) {
            mInstance = new ProcessDataTest();
        }
        return mInstance;
    }

    public String getProcessDec() {
        return processDec;
    }

    public ProcessDataTest setProcessDec(String processDec) {
        this.processDec = processDec;
        return this;
    }
}

package com.lcb.todayinformation.splash;


import android.content.Context;

/**
 * Created by ${lichangbin} on 2020/2/3.
 */
public class MemoryTest {

    private static MemoryTest mInstance;

    private final Context context;

    public MemoryTest(Context context) {
        this.context=context;
    }

    public static MemoryTest getInstance(Context context){
        if (mInstance ==null){
            mInstance = new MemoryTest(context);
        }
        return mInstance;
    }

}

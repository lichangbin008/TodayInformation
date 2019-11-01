package com.lcb.http.annotation;

import android.support.annotation.IntDef;

import static com.lcb.http.annotation.RequestMothod.Get;
import static com.lcb.http.annotation.RequestMothod.Post;

/**
 * Created by ${lichangbin} on 2019/11/1.
 */

@IntDef({Get,Post})
public @interface RequestMothod {

    int Get = 1;
    int Post = 2;
}
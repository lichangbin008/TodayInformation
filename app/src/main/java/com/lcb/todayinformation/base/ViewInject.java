package com.lcb.todayinformation.base;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ${lichangbin} on 2019/10/20.
 */

@Retention(RUNTIME) //运行时注解
@Target(TYPE) // 类 接口 注解
public @interface ViewInject {
    int mainlayout() default -1;
}

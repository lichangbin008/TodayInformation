package com.lcb.todayinformation.main.tools;

import android.support.annotation.IntDef;

import static com.lcb.todayinformation.main.tools.MainConstantTool.BEIJING;
import static com.lcb.todayinformation.main.tools.MainConstantTool.HANGZHOU;
import static com.lcb.todayinformation.main.tools.MainConstantTool.SHANHAI;
import static com.lcb.todayinformation.main.tools.MainConstantTool.SHENZHEN;

/**
 * Created by ${lichangbin} on 2019/10/25.
 */

@IntDef({SHANHAI, HANGZHOU, BEIJING, SHENZHEN})
public @interface MainConstantTool {

    int SHANHAI = 0;
    int HANGZHOU = 1;
    int BEIJING = 2;
    int SHENZHEN = 3;

}

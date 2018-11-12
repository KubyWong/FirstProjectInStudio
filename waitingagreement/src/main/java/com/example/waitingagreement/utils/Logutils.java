package com.example.waitingagreement.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by kubyhuang on 2018/11/12.
 */

public class Logutils {
    public static String customTagPrefix = "kuby_log";

    private Logutils() {
    }

    private static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static void d(String content) {
        if (!Const.IS_DEBUG) return;
        String tag = generateTag();

        Log.d(tag, content);
    }
}

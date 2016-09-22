package com.example.kuby.firstproject.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/7/17.
 */
public class DebugLog {
    private static final String tag = "DebugLog";
    public static void d(String msg){
        Log.d(tag,msg);
    }
    public static void e(String msg){
        Log.e(tag,msg);
    }
}

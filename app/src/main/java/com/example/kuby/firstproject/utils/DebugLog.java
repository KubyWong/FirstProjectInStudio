package com.example.kuby.firstproject.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/17.
 */
public class DebugLog {
    private static final String tag = "DebugLog";
    public static void d(String msg){
        Log.i(tag,msg);
    }
    public static void e(String msg){
        Log.e(tag,msg);
    }
    public static void t(Context a, String msg){
        Toast.makeText(a, msg, Toast.LENGTH_SHORT).show();
    }
}

package com.example.kuby.firstproject.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.kuby.firstproject.utils.DebugLog;

/**
 * Created by Administrator on 2016/7/17.
 */
public class ActivityControler implements IActivtyControler {

    @Override
    public void onActivityJump(Activity activity, Intent intent) {
        String targetClassName = intent.getComponent().getClassName();
        DebugLog.d(targetClassName);
        activity.startActivity(intent);
    }

    @Override
    public void onActivityJump(Activity activity, Intent intent, int animInId, int animOutId) {
        String targetClassName = intent.getComponent().getClassName();
        DebugLog.d(targetClassName);
        activity.startActivity(intent);
        activity.overridePendingTransition(animInId,animOutId);
    }
}

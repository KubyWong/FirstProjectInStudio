package com.example.kuby.firstproject.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/7/17.
 */
public interface IActivtyControler {
    void onActivityJump(Activity context, Intent intent);
    void onActivityJump(Activity context, Intent intent, int animInId, int animOutId);
}

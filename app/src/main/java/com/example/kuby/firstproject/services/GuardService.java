package com.example.kuby.firstproject.services;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.kuby.firstproject.IMyProcessConnectInterface;

import org.xutils.common.util.LogUtil;

public class GuardService extends Service {
    private int GuardId = 2;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.d("连接成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.d("连接断开");
            startService(new Intent(GuardService.this, GuardService.class));
            bindService(new Intent(GuardService.this, GuardService.class), this, BIND_IMPORTANT);
        }
    };

    public GuardService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("GuardService onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new IMyProcessConnectInterface.Stub() {
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(GuardId, new Notification());
        //绑定建立连接
        bindService(new Intent(this,MessageService.class), conn, Context.BIND_IMPORTANT);
        return Service.START_STICKY;
    }
}

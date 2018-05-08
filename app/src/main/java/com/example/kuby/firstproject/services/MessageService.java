package com.example.kuby.firstproject.services;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;

import com.example.kuby.firstproject.IMyProcessConnectInterface;

import org.xutils.common.util.LogUtil;

public class MessageService extends Service {
    private final int MessageId = 1;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接上
            LogUtil.d("MessageService connected suc");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //掉线,断开连接，重新启动
            LogUtil.d("MessageService connected fal");
            startService(new Intent(MessageService.this, GuardService.class));
            bindService(new Intent(MessageService.this, GuardService.class), this, BIND_IMPORTANT);
        }
    };

    public MessageService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            private long lastTime = System.currentTimeMillis();
            @Override
            public void run() {

                try {
                    while (true) {
                        LogUtil.d("心跳,时间差：" + (System.currentTimeMillis() - lastTime));
                        lastTime = System.currentTimeMillis();
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new IMyProcessConnectInterface.Stub() {
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //提高优先级
        startForeground(MessageId, new Notification());
        //绑定建立连接
        bindService(new Intent(this,GuardService.class), conn, Context.BIND_IMPORTANT);
        return Service.START_STICKY;
    }
}

package com.example.waitingagreement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.waitingagreement.utils.Const;
import com.example.waitingagreement.utils.Logutils;

import cn.bmob.v3.Bmob;

public class SplashActivity extends Activity {

    private TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        tv_time = findViewById(R.id.tv_time);

        CountDownTimer countDownTimer = new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long l) {
                tv_time.setText(l/1000+"s");
                Logutils.d(l+" <-- ");
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        };
        countDownTimer.start();
    }
}

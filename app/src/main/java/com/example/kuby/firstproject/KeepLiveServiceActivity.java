package com.example.kuby.firstproject;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kuby.firstproject.services.GuardService;
import com.example.kuby.firstproject.services.JobWakeUpService;
import com.example.kuby.firstproject.services.MessageService;

public class KeepLiveServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_live_service);

        startService(new Intent(this, MessageService.class));
        startService(new Intent(this, GuardService.class));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startService(new Intent(this, JobWakeUpService.class));
        }


    }
}

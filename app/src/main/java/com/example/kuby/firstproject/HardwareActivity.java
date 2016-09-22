package com.example.kuby.firstproject;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kuby.firstproject.utils.DebugLog;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class HardwareActivity extends AppCompatActivity {

    @ViewInject(value = R.id.iv)
    private ImageView iv;

    private SensorManager sensorManager;
    private SensorEventListener sensorLis = new SensorLis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        x.view().inject(this);

        iv.setKeepScreenOn(true);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//取得传感器管理对象

    }

    @Override
    protected void onResume() {
//        sensorManager.getOrientation()
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);//取得方向传感器
        sensorManager.registerListener(sensorLis, sensor, SensorManager.SENSOR_DELAY_GAME);//参数(监听器,sensor硬件,采样率)
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(sensorLis);
        super.onPause();
    }

    private final class SensorLis implements SensorEventListener{
        private float preDegree = 90;

        //当测量出来数据之后就会调用该方法
        @Override
        public void onSensorChanged(SensorEvent event) {
            float degree = event.values[0];//event.values[0]得到的是手机旋转的角度,一共360度(也是0度 = 360度方向)
            RotateAnimation animation = new RotateAnimation(preDegree,-degree, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(200);
            animation.setFillAfter(true);
            iv.startAnimation(animation);
            DebugLog.d("degree: "+degree);
            preDegree = -degree;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}

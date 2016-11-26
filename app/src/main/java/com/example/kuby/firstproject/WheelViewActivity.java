package com.example.kuby.firstproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bigkoo.pickerview.TimePickerView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WheelViewActivity extends AppCompatActivity {

    @ViewInject(value = R.id.tv_show)
    private TextView tv_show;
    @ViewInject(value = R.id.btn_shwo_wheel)
    private Button btn_shwo_wheel;

    private TimePickerView pvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_view);
        x.view().inject(this);

        initTimepick();

    }

    private void initTimepick() {
        pvTime = new TimePickerView(this, TimePickerView.Type.HOURS_MINS);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tv_show.setText(getTime(date));
            }
        });
        //弹出时间选择器
    }

    @Event(value = {R.id.btn_shwo_wheel})
    private void onclickLis(View view) {
        switch (view.getId()) {
            case R.id.btn_shwo_wheel:

                pvTime.show();
                break;
        }
    }


    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }
}

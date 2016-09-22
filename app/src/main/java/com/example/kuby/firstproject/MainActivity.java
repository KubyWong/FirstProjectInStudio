package com.example.kuby.firstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kuby.firstproject.impl.ActivityControler;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MainActivity extends AppCompatActivity {

    @ViewInject(value = R.id.lv_main)
    private ListView lvMain;

    private String[] arrStr = {
            "HardwareActivity",
            "ViewFlipper",
            "TouchActivity",
            "aaa4",
            "aaa5",
            "aaa6",
    };

    private ActivityControler controler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        controler = new ActivityControler();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_main_list, R.id.tv_title, arrStr);
        lvMain.setAdapter(adapter);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.tv_title);
                String title = textView.getText().toString();
                switch (title){
                    case  "HardwareActivity":
                        controler.onActivityJump(MainActivity.this,new Intent(MainActivity.this,HardwareActivity.class),R.anim.r2l_in,R.anim.r2l_out);
                        break;
                    case  "ViewFlipper":
                        controler.onActivityJump(MainActivity.this,new Intent(MainActivity.this,ViewFlipperActivity.class),R.anim.r2l_in,R.anim.r2l_out);
                        break;
                    case  "TouchActivity":
                        controler.onActivityJump(MainActivity.this,new Intent(MainActivity.this,TouchActivity.class),R.anim.r2l_in,R.anim.r2l_out);
                        break;
                    case  "ActivityAnimation3":
                        break;
                }
            }
        });
    }
}

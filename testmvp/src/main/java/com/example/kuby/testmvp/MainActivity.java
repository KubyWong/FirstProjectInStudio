package com.example.kuby.testmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kuby.testmvp.mvp.view.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView{

    private TextView tv_show;
    private Button btn_get;
    private RelativeLayout loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        loadingbar = (RelativeLayout) findViewById(R.id.loadingbar);
        loadingbar.setVisibility(View.GONE);
        tv_show = (TextView) findViewById(R.id.tv_show);
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public void showLoadingBar(boolean isShow) {
        loadingbar.setVisibility(isShow ? View.VISIBLE:View.GONE);
    }

    @Override
    public void changeTextView(String name) {
        tv_show.setText(name);
    }
}

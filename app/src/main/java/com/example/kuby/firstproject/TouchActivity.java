package com.example.kuby.firstproject;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.kuby.firstproject.utils.DebugLog;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class TouchActivity extends AppCompatActivity {

    @ViewInject(value = R.id.iv)
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        x.view().inject(this);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            public int my;
            public int mx;
            PointF pointF = new PointF();
            Matrix matrix = new Matrix();
            Matrix current = new Matrix();
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()  & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        DebugLog.d("ACTION_DOWN");
                        current.set(imageView.getImageMatrix());//记录当前的imageview移动位置
                        pointF.set(event.getX(),event.getY());

                        break;
                    case MotionEvent.ACTION_MOVE:
                        DebugLog.d("ACTION_MOVE");
                        float dx = event.getX() - pointF.x;
                        float dy = event.getY() - pointF.y;
                        DebugLog.d("dx:dy = "+dx+":"+dy);
                        matrix.set(current);
                        matrix.postTranslate(dx, dy);

                       /* mx = (int)(event.getX() - pointF.x);
                        my = (int)(event.getRawY() - 50);

                        v.layout(mx - imageView.getWidth()/2, my - imageView.getHeight()/2, mx + imageView.getWidth()/2, my + imageView.getHeight()/2);*/
                        break;
                    case MotionEvent.ACTION_UP:
                        DebugLog.d("ACTION_UP");
                        break;
                }
                imageView.setImageMatrix(matrix);
                return true;
            }
        });
    }
}

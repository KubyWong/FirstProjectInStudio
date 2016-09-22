package com.example.kuby.firstproject;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.example.kuby.firstproject.utils.DebugLog;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class ViewFlipperActivity extends AppCompatActivity {

    @ViewInject(value = R.id.view_flipper)
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        x.view().inject(this);

        final GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(this,new MyGstureDelector());

        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetectorCompat.onTouchEvent(event);
                return true;
            }
        });
    }

    class MyGstureDelector extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            DebugLog.d("velocityX: "+velocityX);
            if(velocityX > 0){
                Animation in = AnimationUtils.loadAnimation(ViewFlipperActivity.this,R.anim.r2l_in);
                Animation out = AnimationUtils.loadAnimation(ViewFlipperActivity.this,R.anim.r2l_out);
                viewFlipper.setInAnimation(in);
                viewFlipper.setOutAnimation(in);
                viewFlipper.showNext();
                return true;
            }else if(velocityX < 0){
                Animation in = AnimationUtils.loadAnimation(ViewFlipperActivity.this,R.anim.l2r_in);
                Animation out = AnimationUtils.loadAnimation(ViewFlipperActivity.this,R.anim.l2r_out);
                viewFlipper.setInAnimation(in);
                viewFlipper.setOutAnimation(in);
                viewFlipper.showPrevious();
                return true;
            }else {
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        }
    }
}

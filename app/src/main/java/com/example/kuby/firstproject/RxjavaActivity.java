package com.example.kuby.firstproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kuby.firstproject.bean.Course;
import com.example.kuby.firstproject.bean.Student;
import com.example.kuby.firstproject.utils.DebugLog;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxjavaActivity extends AppCompatActivity {

    private static final String TAG = "RxjavaActivity";
    @ViewInject(value = R.id.btn_simple1)
    private Button btn_simple1;
    @ViewInject(value = R.id.btn_simple2)
    private Button btn_simple2;
    @ViewInject(value = R.id.btn_sync1)
    private Button btn_sync1;
    @ViewInject(value = R.id.btn_sync2)
    private Button btn_sync2;
    @ViewInject(value = R.id.btn_map1)
    private Button btn_map1;
    @ViewInject(value = R.id.btn_flatmap)
    private Button btn_flatmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        x.view().inject(this);

    }

    @Event(value = {R.id.btn_simple1,R.id.btn_simple2,R.id.btn_sync1,R.id.btn_sync2,R.id.btn_map1,R.id.btn_flatmap},type = View.OnClickListener.class)
    private void onclickLis(View view){
        switch (view.getId()) {
            case R.id.btn_simple1:
                simple1();
                break;
            case R.id.btn_simple2:
                simple2();
                break;
            case R.id.btn_sync1:
                sync1();
                break;
            case R.id.btn_sync2:
                sync2();
                break;
            case R.id.btn_map1:
                exampleMap1();
                break;
            case R.id.btn_flatmap:
                exampleMapflat();
                break;
        }
    }

    /**
     * 方式1---生成观察者，再生成被观察者，用被观察者订阅观察者.实际上观察者在被观察者的类里面。
     */
    private void simple1() {
       /* Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                DebugLog.t(RxjavaActivity.this, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                DebugLog.d("onError: simple1");
            }

            @Override
            public void onNext(String s) {
                DebugLog.d("onNext---> "+s);
            }

        };*/

        //创建“观察者”，上面的方法也行，只是这个方法功能完善一点
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
                DebugLog.d("onStart");
            }

            @Override
            public void onCompleted() {
                DebugLog.d("onCompleted:simple1");
                DebugLog.t(RxjavaActivity.this, "onCompleted in simple1");
            }

            @Override
            public void onError(Throwable e) {
                DebugLog.d("onError: simple1");
            }

            @Override
            public void onNext(String s) {
                DebugLog.d("onNext---> "+s);
            }
        };

        //创建“被观察者”
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                DebugLog.d("Observable.OnSubscribe.call()");
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        //令观察者和被观察者产生订阅关系，一旦订阅
        observable.subscribe(subscriber);
    }

    /**
     * 方式2---通过一个数组生成被观察者，再用一个可以自动生成观察者的订阅方法来一气呵成。
     *
     * // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
     observable.subscribe(onNextAction);
     // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
     observable.subscribe(onNextAction, onErrorAction);
     // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
     observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
     */
    private void simple2() {
        /*
        just(T...): 将传入的参数依次发送出来。
        Observable observable = Observable.just("Hello", "Hi", "Aloha");
        // 将会依次调用：
        // onNext("Hello");
        // onNext("Hi");
        // onNext("Aloha");
        // onCompleted();
        */


        String[] strings = {"Hello","Hi","Aloha"};
        // 	from(T[]): 实质上就是from(Iterable<? extends T>) : 将传入的数组或 Iterable 拆分成具体对象后，依次发送出来。
       Observable.from(strings).subscribe(new Action1<String>() {
           @Override
           public void call(String s) {
               DebugLog.d("onNext---> "+s);
           }
       }, new Action1<Throwable>() {
           @Override
           public void call(Throwable throwable) {
               DebugLog.d("onError: simple2");
           }
       }, new Action0() {
           @Override
           public void call() {
               DebugLog.t(RxjavaActivity.this, "onCompleted in simple2");
           }
       });

    }

    private void sync1() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                DebugLog.d("in Observable.OnSubscribe.call thread is "+Thread.currentThread().getName());
                subscriber.onNext("one");
                subscriber.onNext("two");
                subscriber.onNext("three");
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        DebugLog.t(RxjavaActivity.this,"sync1 onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        DebugLog.e("onError");
                    }

                    @Override
                    public void onNext(String s) {
                        DebugLog.d("onNext---> "+s + "  ,thread is:"+Thread.currentThread().getName());
                    }
                });

    }

    private void sync2() {
        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                DebugLog.d("sync2()  ---  number="+integer+ ", this Subscriber is in "+Thread.currentThread().getName());

            }
        });
    }

    /**
     * RxJava 提供了对事件序列进行变换的支持，这是它的核心功能之一，也是大多数人说『RxJava 真是太好用了』的最大原因。所谓变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列。概念说着总是模糊难懂的，来看 API。
     */
    private void exampleMap1() {
        Student student1 = new Student("stu1");
        Student student2 = new Student("stu2");
        Student[] students = {student1,student2};
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                DebugLog.d("exampleMap1 student name onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                DebugLog.d("exampleMap1 student name is : "+s);
            }
        };
        Observable.from(students)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                })
                .subscribe(subscriber);
    }

    /**
     * 一对多
     */
    private void exampleMapflat() {
        final Student student1 = new Student("stu1");
        List<Course> listCourse1 = new ArrayList<>();
        listCourse1.add(new Course("Chinese"));
        listCourse1.add(new Course("English"));
        student1.setCourses(listCourse1);

        Student student2 = new Student("stu2");
        List<Course> listCourse2 = new ArrayList<>();
        listCourse2.add(new Course("Math"));
        listCourse2.add(new Course("Music"));
        student2.setCourses(listCourse2);

        Student[] students = {student1,student2};
        Subscriber<Course> subscriber = new Subscriber<Course>() {
            @Override
            public void onStart() {
                super.onStart();
                DebugLog.d("exampleMapflat onStart()");
            }

            @Override
            public void onCompleted() {
                DebugLog.d("exampleMapflat onCompleted()");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                DebugLog.d("exampleMapflat onNext()");
                DebugLog.d("course: "+course.getName());

            }
        };

        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        DebugLog.d("student---> "+student.getName());
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(subscriber);
    }

    @Override
    public void onBackPressed() {
//        finish();
        startActivity(new Intent(this,MainActivity.class));
        overridePendingTransition(R.anim.l2r_in,R.anim.l2r_out);
    }
}

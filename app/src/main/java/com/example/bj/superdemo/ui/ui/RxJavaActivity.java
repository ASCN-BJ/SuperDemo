package com.example.bj.superdemo.ui.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.bean.Course;
import com.example.bj.superdemo.ui.bean.Student;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 观察者模式实践(RxJava)
 */
public class RxJavaActivity extends BaseActivity {
    private final String TAG = RxJavaActivity.this.getClass().getSimpleName();
    private String[] folders = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    private ImageView iv_picture;
    private Button btn_show_picture;
    private TextView tv_rx_content;
    private Student[] ss;

    @Override
    public void initData() {
        setContentView(R.layout.activity_rx_java);
        iv_picture = (ImageView) findViewById(R.id.iv_picture);
        btn_show_picture = (Button) findViewById(R.id.btn_show_picture);
        btn_show_picture.setOnClickListener(this);
        tv_rx_content = (TextView) findViewById(R.id.tv_rx_content);
        Log.i(TAG, "initData: heiehi");
        Observable.from(folders).subscribe(new Action1<String>() {
            @Override
            public void call(final String s) {
//                Log.i("RxJavaActivity", s);
            }
        });
        initStudent();
//        onObserver();
//        onObserver1();
//        onObserver2();
//        onObserver3();
//        onObserver4();
        onObserver5();

    }

    /* thread synchronization */
    private void onObserver5() {

    }

    /*show student 1 by flatMap*/
    private void onObserver4() {
        Subscriber<Course> sub = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context, "this's error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Course course) {
                Log.e(TAG, "onNext: ___" + course.getCourseId() + "name__" + course.getCourseNum());
            }
        };

        Observable.from(ss).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getStudentCourse());
            }
        }).subscribe(sub);
    }


    /*show student1 by map*/
    private void onObserver3() {
        Subscriber<Student> sub = new Subscriber<Student>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context, "error_student", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Student student) {
                List<Course> studentCourse = student.getStudentCourse();
                for (Course course : studentCourse) {
                    Log.e(TAG, "onNext: +" + course.getCourseNum() + course.getCourseId());
                }
            }
        };
        Observable.from(ss).subscribe(sub);
    }

    /*show picture*/
    private void onObserver2() {
        Observable.just(R.drawable.x0).subscribeOn(Schedulers.io()).map(new Func1<Integer, Drawable>() {

            @Override
            public Drawable call(Integer s) {
                return context.getResources().getDrawable(s);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Drawable bitmap) {
                iv_picture.setImageDrawable(bitmap);
            }
        });
    }

    /*show picture*/
    private void onObserver1() {
        Observable.create(new Observable.OnSubscribe<Drawable>() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void call(final Subscriber<? super Drawable> subscriber) {
                int id = R.drawable.fengjing1;
//                int id = 0x000000;
                final Drawable d = RxJavaActivity.this.getTheme().getDrawable(id);
                SystemClock.sleep(5000L);//模拟网络延迟
                subscriber.onNext(d);
                subscriber.onCompleted();
            }/*读取图片是耗时操作放在子线程中*/
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context, "this is error ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Drawable drawable) {
                iv_picture.setImageDrawable(drawable);
            }
        });
    }

    /*observer*/
    private void onObserver() {
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                Log.e(TAG, "onCompleted:onCompleted ");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e(TAG, "onNext:" + s);
//            }
//        };
        Observable<String> observable = Observable.just("I ", "have", "dream");
//        Observable os = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("I");
//                subscriber.onNext("have");
//                subscriber.onNext("a");
//                subscriber.onNext("pen!");
//                subscriber.onCompleted();
//
//            }
//        });
//        os.subscribe(observer);
        /*next*/
        Action1<String> nextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "call: " + s);
            }
        };
        /*completed*/
        Action0 completeAction = new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "call: " + "completed");
            }
        };
        /*error*/
        Action1<Throwable> errorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        };
        observable.subscribe(nextAction, errorAction, completeAction);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_picture:
                startActivity(new Intent(this, RxJava2Activity.class));
                break;
            default:

                break;
        }
    }

    private void initStudent() {
        Student s1 = new Student();
        Course c1 = new Course();
        c1.setCourseId("1");
        c1.setCourseNum("chinese");

        Course c2 = new Course();
        c1.setCourseId("2");
        c1.setCourseNum("math");
        List<Course> mLists = new ArrayList<>();
        mLists.add(c1);
        mLists.add(c2);
        s1.setStudentCourse(mLists);
        s1.setStudentNam("hello");

        Student s2 = new Student();
        Course c3 = new Course();
        c3.setCourseId("1");
        c3.setCourseNum("chinese");

        Course c4 = new Course();
        c4.setCourseId("2");
        c4.setCourseNum("math");
        List<Course> mLists2 = new ArrayList<>();
        mLists2.add(c3);
        mLists2.add(c4);
        s2.setStudentCourse(mLists2);
        s2.setStudentNam("hello");
        ss = new Student[]{s1, s2};
    }
}

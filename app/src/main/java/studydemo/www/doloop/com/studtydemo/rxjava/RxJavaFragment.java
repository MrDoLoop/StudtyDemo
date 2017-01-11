package studydemo.www.doloop.com.studtydemo.rxjava;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import studydemo.www.doloop.com.studtydemo.BaseFragmnet;
import studydemo.www.doloop.com.studtydemo.R;

/**
 * Created by zhaonan on 17/1/10.
 */

public class RxJavaFragment extends BaseFragmnet {

    private TextView textView1;
    private TextView textView2;
    private Observer<String> mySubscriber1;
    private Observer<String> mySubscriber2;
    private Button btn;
    private ImageView img;
    private Observable<String> myObservable;
    private String[] strs = new String[]{"1111", "2222", "3333"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rxjava, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img = (ImageView) view.findViewById(R.id.img);
        textView1 = (TextView) view.findViewById(R.id.txt1);
        textView2 = (TextView) view.findViewById(R.id.txt2);
        btn = (Button) view.findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myObservable = Observable.from(strs);
//                myObservable.subscribe(mySubscriber1);
//                myObservable.subscribe(mySubscriber2);
//            }
//        });

        RxView.longClicks(btn).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Toast.makeText(getContext(), "long点击了", Toast.LENGTH_SHORT).show();
            }
        }) ;
        RxView.clicks(btn)
                .throttleFirst(1, TimeUnit.SECONDS )   //1秒钟之内只取一个点击事件，防抖操作
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(getContext(), "点击了", Toast.LENGTH_SHORT).show();
                        myObservable = Observable.from(strs);
                        myObservable.subscribe(mySubscriber1);
                        myObservable.subscribe(mySubscriber2);
                    }
                }) ;


//        myObservable = Observable.create(
//                new Observable.OnSubscribe<String>() {
//                    @Override
//                    public void call(Subscriber<? super String> sub) {
//                        sub.onNext("赵楠 Hello, world!");
//                        sub.onCompleted();
//                    }
//                }
//        );


        myObservable = Observable.from(strs);
        //myObservable = Observable.just("赵楠 Hello, world!" + System.currentTimeMillis());
        //myObservable = Observable.just("111", "222", "333");
//        myObservable = Observable.just("Hello, world!" + System.currentTimeMillis())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        System.out.println(s);
//                    }
//                });


        mySubscriber1 = new Observer<String>() {

//            @Override
//            public void onStart() {
//                textView1.append("mySubscriber1 onStart " + System.currentTimeMillis() + "\n");
//            }

            @Override
            public void onNext(String s) {
                textView1.append("txt1: " + s + "\n");
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
                textView1.append("mySubscriber1 onCompleted\n");
            }

            @Override
            public void onError(Throwable e) {
                textView1.append("mySubscriber1 onError");
            }
        };

        mySubscriber2 = new Observer<String>() {
            @Override
            public void onNext(String s) {
                textView2.append("txt2: " + s + "\n");
            }

            @Override
            public void onCompleted() {
                textView2.append("mySubscriber2 onCompleted\n");
            }

            @Override
            public void onError(Throwable e) {
            }
        };

        // 单一回调
        myObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("ttt", "单一回调 " + s);
            }
        });
        myObservable.subscribe(mySubscriber1);
        myObservable.subscribe(mySubscriber2);


        //////////////map 一对一的映射
        textView1.postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        Observable.create(new Observable.OnSubscribe<Drawable>() {
//                            @Override
//                            public void call(Subscriber<? super Drawable> subscriber) {
//                                Drawable drawable = getResources().getDrawable(R.drawable.fragment);
//                                subscriber.onNext(drawable);
//                                subscriber.onCompleted();
//                            }
//                        }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//                                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
//                                .subscribe(new Observer<Drawable>() {
//                                    @Override
//                                    public void onNext(Drawable drawable) {
//                                        img.setImageDrawable(drawable);
//                                    }
//
//                                    @Override
//                                    public void onCompleted() {
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//
//                                    }
//                                });
                        Observable.just(R.drawable.ic_launcher) // 输入类型 String
                                .map(new Func1<Integer, Drawable>() {
                                    @Override
                                    public Drawable call(Integer resId) { // 参数类型 String
                                        if(isAdded()) {
                                            return getResources().getDrawable(resId); // 返回类型 drawable
                                        }
                                        return null;
                                    }
                                })
                                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                                .subscribe(new Action1<Drawable>() {
                                    @Override
                                    public void call(Drawable drawable) { // 参数类型 drawable
                                        img.setImageDrawable(drawable);
                                    }
                                });
                    }
                }).start();
            }
        }, 1000);


        //////////////flatMap 一对多的映射
        Observable.from(new Student[]{Student.genTestStudent1(), Student.genTestStudent2()})
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        Log.i("ttt", "student name " + student.getName());
                        return Observable.from(student.getCourseList());
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Log.i("ttt", "course name " + course.getName());
                    }
                });
    }
}
package studydemo.www.doloop.com.studtydemo.multithread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import studydemo.www.doloop.com.studtydemo.R;
import studydemo.www.doloop.com.studtydemo.base.BaseFragmnet;

/**
 * Created by zhaonan on 17/1/11.
 */

public class MultiThreadFragment extends BaseFragmnet {

    private TextView mTv;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTv = $(R.id.text_view);
        mTv.setText("模板Fragment");
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //MultiThreadTest();
        ExecutorTest();
    }

    private void ExecutorTest() {
        //http://blog.csdn.net/nk_tf/article/details/51959276
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            try {
//                Log.i("ttt", "index sleep: "+index * 1000);
//                Thread.sleep(index * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            cachedThreadPool.execute(new Runnable() {
//
//                @Override
//                public void run() {
//                    Log.i("ttt", "index: "+index);
//                }
//            });
//        }

//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
//        for (int i = 0; i < 100; i++) {
//            final int index = i;
//            fixedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Log.i("ttt", "index: "+index);
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }

        //创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//        scheduledThreadPool.schedule(new Runnable() {
//
//            @Override
//            public void run() {
//                Log.i("ttt", "delay 3 seconds");
//            }
//        }, 3, TimeUnit.SECONDS);

//        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
//
//            @Override
//            public void run() {
//                Log.i("ttt", "delay 1 seconds, and excute every 3 seconds");
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }, 1, 3, TimeUnit.SECONDS);


//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            singleThreadExecutor.execute(new Runnable() {
//
//                @Override
//                public void run() {
//                    try {
//                        Log.i("ttt", ""+index);
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }

    }


    private void MultiThreadTest() {

        int mThreadNum = 100;

        CountDownLatch mCDL = new CountDownLatch(mThreadNum);
        //List<Object> list = new Vector<>();
        List<Object> list = new ArrayList<>();

        for (int i = 0; i < mThreadNum; i++) {
            Thread thread = new Thread(new MyRunnable(list, mCDL));
            thread.start();
        }

        try {
            mCDL.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("ttt", "list长度: "+list.size());

        //mTv.setText("list长度: "+list.size());
    }

}

package studydemo.www.doloop.com.studtydemo.multithread;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhaonan on 17/1/16.
 */

public class MyRunnable implements Runnable {

    private List<Object> mList;

    private CountDownLatch mCountDownLatch;


    public MyRunnable(List list, CountDownLatch countDownLatch) {
        mList = list;
        mCountDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            mList.add(new Object());
        }

        mCountDownLatch.countDown();
    }
}

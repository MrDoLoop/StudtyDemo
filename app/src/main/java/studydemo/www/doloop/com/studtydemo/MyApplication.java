package studydemo.www.doloop.com.studtydemo;

import android.app.Application;

import com.antfortune.freeline.FreelineCore;

import studydemo.www.doloop.com.studtydemo.hook.AmsUtils;

/**
 * Created by zhaonan on 17/1/10.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        FreelineCore.init(this);
        AmsUtils.initHook();
        mInstance = this;
    }

    public static Application getAppInstance() {
        return mInstance;
    }
}

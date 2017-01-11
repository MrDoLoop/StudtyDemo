package studydemo.www.doloop.com.studtydemo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
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
        mInstance = this;
        FreelineCore.init(this);
        AmsUtils.initHook();
        ARouter.init(this);
        ARouter.openDebug();
    }

    public static Application getAppInstance() {
        return mInstance;
    }
}

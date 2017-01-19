package studydemo.www.doloop.com.studtydemo.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.antfortune.freeline.FreelineCore;

import cn.campusapp.router.Router;
import io.realm.Realm;
import studydemo.www.doloop.com.studtydemo.hook.AmsUtils;
import timber.log.Timber;

/**
 * Created by zhaonan on 17/1/10.
 */

public class BaseApplication extends Application {
    private static BaseApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        // freeline 编译
        FreelineCore.init(this);

        // 这样的hook有问题
        AmsUtils.initHook();

        // 阿里路由
        ARouter.init(this);
        ARouter.openDebug();

        // 路由
        Router.initActivityRouter(this);

        Timber.plant(new Timber.DebugTree());

        // realm初始化
        Realm.init(this);
    }

    public static Application getAppInstance() {
        return mInstance;
    }
}

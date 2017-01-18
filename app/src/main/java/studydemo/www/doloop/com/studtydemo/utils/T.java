package studydemo.www.doloop.com.studtydemo.utils;

import android.widget.Toast;

import studydemo.www.doloop.com.studtydemo.base.BaseApplication;

/**
 * Created by zhaonan on 17/1/16.
 */

public class T {

    public static Toast mToast;

    public static void show(CharSequence txt) {
        init();
        mToast.setText(txt);
        mToast.show();
    }

    private static void init() {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getAppInstance(), "", Toast.LENGTH_SHORT);
        }
    }

}

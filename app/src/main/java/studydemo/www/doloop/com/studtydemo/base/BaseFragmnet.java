package studydemo.www.doloop.com.studtydemo.base;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by zhaonan on 17/1/10.
 */

public class BaseFragmnet extends Fragment {

    protected View mRootView;

    public <T extends View> T $(int id) {

        if(mRootView == null) {
            mRootView =  getView();
        }

        if (mRootView != null) {
            return (T) getView().findViewById(id);
        }
        return null;
    }
}

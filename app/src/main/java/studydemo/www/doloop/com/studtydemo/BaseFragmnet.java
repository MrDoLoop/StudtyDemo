package studydemo.www.doloop.com.studtydemo;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by zhaonan on 17/1/10.
 */

public class BaseFragmnet extends Fragment {
    public <T extends View> T $(int id) {
        if (getView() != null) {
            return (T) getView().findViewById(id);
        }
        return null;
    }
}

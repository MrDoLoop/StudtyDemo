package studydemo.www.doloop.com.studtydemo;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by zhaonan on 17/1/11.
 */

public class BaseActivity extends AppCompatActivity {

    public <T extends View> T $(int id) {
        return (T) super.findViewById(id);
    }

}

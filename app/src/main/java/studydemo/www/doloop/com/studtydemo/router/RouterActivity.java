package studydemo.www.doloop.com.studtydemo.router;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import cn.campusapp.router.annotation.RouterMap;
import studydemo.www.doloop.com.studtydemo.base.BaseActivity;
import studydemo.www.doloop.com.studtydemo.R;

/**
 * Created by zhaonan on 17/1/11.
 */
//@Route(path = "/test/1")
@RouterMap({"activity://RouterActivity", "activity://RouterActivity/:{txt}"})
public class RouterActivity extends BaseActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view);
        mTv = $(R.id.text_view);
        mTv.setText("RouterActivity");
        setTitle("RouterActivity");

        String txt = getIntent().getStringExtra("txt");
        if(!TextUtils.isEmpty(txt)) {
            mTv.setText(txt);
        }
    }
}

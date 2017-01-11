package studydemo.www.doloop.com.studtydemo.router;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import studydemo.www.doloop.com.studtydemo.BaseActivity;
import studydemo.www.doloop.com.studtydemo.R;

/**
 * Created by zhaonan on 17/1/11.
 */
@Route(path = "/test/1")
public class RouterActivity1 extends BaseActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view);
        mTv = $(R.id.text_view);
        mTv.setText("RouterActivity");
    }
}

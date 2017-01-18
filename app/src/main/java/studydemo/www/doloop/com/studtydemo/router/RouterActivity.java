package studydemo.www.doloop.com.studtydemo.router;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.campusapp.router.annotation.RouterMap;
import studydemo.www.doloop.com.studtydemo.R;
import studydemo.www.doloop.com.studtydemo.base.BaseActivity;

/**
 * Created by zhaonan on 17/1/11.
 */
@RouterMap({"activity://RouterActivity", "activity://RouterActivity/:{txt}"})
@Route(path = "/test/1")
public class RouterActivity extends BaseActivity {

    private TextView mTv;

    public String key1;
    private int key2;
    private boolean key3;

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

        key1 = getIntent().getStringExtra("key1");
        key2 = getIntent().getIntExtra("key2", -1);
        key3 = getIntent().getBooleanExtra("key33", false);


        String msg = "key1:" + key1 + "\nkey2:" + key2 + "\nkey3:" + key3;
        Toast.makeText(this, msg ,Toast.LENGTH_SHORT).show();
    }
}

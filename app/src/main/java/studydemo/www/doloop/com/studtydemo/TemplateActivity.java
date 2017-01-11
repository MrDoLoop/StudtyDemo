package studydemo.www.doloop.com.studtydemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by zhaonan on 17/1/11.
 */
@Route(path = "/router/activity1")
public class TemplateActivity extends BaseActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view);
        mTv = $(R.id.text_view);
        mTv.setText("模板Activity");
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

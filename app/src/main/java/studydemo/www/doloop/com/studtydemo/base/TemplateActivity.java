package studydemo.www.doloop.com.studtydemo.base;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import studydemo.www.doloop.com.studtydemo.R;

/**
 * Created by zhaonan on 17/1/11.
 */
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

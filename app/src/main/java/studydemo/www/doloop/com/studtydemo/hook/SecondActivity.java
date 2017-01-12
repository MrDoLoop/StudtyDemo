package studydemo.www.doloop.com.studtydemo.hook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.TextView;

import studydemo.www.doloop.com.studtydemo.R;

// AppCompatActivity/ActionBarActivity 崩溃
public class SecondActivity extends FragmentActivity {

    private static final String ARG_TAG = "ARG_TAG";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook_second);
        TextView text = (TextView) findViewById(R.id.text);

        String txt = getIntent().getStringExtra(ARG_TAG);
        if(TextUtils.isEmpty(txt)) {
            text.setText("传递过来的参数为null");
        }
        else {
            text.setText(txt);
        }
	}

    public static void startMe(Context ctx) {
        Intent intent = new Intent(ctx, SecondActivity.class);
        intent.putExtra(ARG_TAG,"赵楠的测试消息");
        ctx.startActivity(intent);
    }
}

package studydemo.www.doloop.com.studtydemo.hook;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import studydemo.www.doloop.com.studtydemo.R;

// AppCompatActivity/ActionBarActivity 崩溃
public class SecondActivity extends FragmentActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook_second);
	}
}

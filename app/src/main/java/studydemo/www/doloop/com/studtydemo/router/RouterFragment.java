package studydemo.www.doloop.com.studtydemo.router;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;

import studydemo.www.doloop.com.studtydemo.BaseFragmnet;
import studydemo.www.doloop.com.studtydemo.R;

/**
 * Created by zhaonan on 17/1/11.
 */

public class RouterFragment extends BaseFragmnet {
    private TextView mTv;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTv = $(R.id.text_view);
        mTv.setText("模板Fragment");
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/test/1").navigation();
                //startActivity(new Intent(getActivity(), RouterActivity1.class));
            }
        });
    }
}

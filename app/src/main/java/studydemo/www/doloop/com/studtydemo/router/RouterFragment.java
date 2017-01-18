package studydemo.www.doloop.com.studtydemo.router;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import studydemo.www.doloop.com.studtydemo.R;
import studydemo.www.doloop.com.studtydemo.base.BaseFragmnet;
import studydemo.www.doloop.com.studtydemo.utils.T;

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
//                ActivityRoute activityRoute = (ActivityRoute) Router.getRoute("activity://RouterActivity");
//                activityRoute.withParams("txt", "传递进来的msg").setAnimation(getActivity(), android.R.anim.fade_in, android.R.anim.fade_out).open();
                //Router.open("activity://RouterActivity/传递进来的msg");
                // 统一路径的冲突怎么解决?
                //ARouter.getInstance().build("/test/1").navigation();

                ARouter.getInstance().build("/test/1")
                        .withString("key1", "KKKEEEYYY111")
                        .withInt("key2", 666)
                        .withBoolean("key33", true)
                        .withString("txt", "888")
                        .navigation(getContext(), new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                T.show("路由成功");
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                T.show("路由失败");
                            }
                        });

            }
        });
    }
}

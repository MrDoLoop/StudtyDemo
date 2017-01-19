package studydemo.www.doloop.com.studtydemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import org.joor.Reflect;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;
import studydemo.www.doloop.com.studtydemo.base.BaseActivity;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private List<Fragment> mFragments;
    private BottomNavigationBar mBottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 启动白屏解决
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        mFragments = getmFragments();

        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);

        FragmentsDef.FRAGMENT_DEMO.appendBottomBarItems(mBottomNavigationBar);
        mBottomNavigationBar.setFirstSelectedPosition(0).initialise();
        setDefaultFragment();
        mBottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, mFragments.get(0));
        transaction.commit();
    }

    private List<Fragment> getmFragments() {
        return FragmentsDef.FRAGMENT_DEMO.getFragments();
    }

    @Override
    @DebugLog
    public void onTabSelected(int position) {
        if (mFragments != null) {
            if (position < mFragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = mFragments.get(position);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                if (fragment.isAdded()) {
                    ft.replace(R.id.layFrame, fragment);
                } else {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commitAllowingStateLoss();

                setBarColor(position);
            }
        }

    }

    @Override
    public void onTabUnselected(int position) {
        if (mFragments != null) {
            if (position < mFragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = mFragments.get(position);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }


    private void setBarColor(int position) {
        int color = getBottomItemActiveColor(position);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }


    private int getBottomItemActiveColor(int pos) {
        try {
            // 通过工具实现
            ArrayList<BottomNavigationItem> itemsList = Reflect.on(mBottomNavigationBar).get("mBottomNavigationItems");
            BottomNavigationItem bottomNavigationItem = itemsList.get(pos);
            return (int) Reflect.on(bottomNavigationItem).call("getActiveColor", this).get();
// 自己实现
//            Field mBottomNavigationItemsField = BottomNavigationBar.class.getDeclaredField("mBottomNavigationItems");
//            mBottomNavigationItemsField.setAccessible(true);
//            ArrayList<BottomNavigationItem> itemsList = (ArrayList<BottomNavigationItem>) mBottomNavigationItemsField.get(mBottomNavigationBar);
//            BottomNavigationItem bottomNavigationItem = itemsList.get(pos);
//            if (bottomNavigationItem != null) {
//                Method getActiveColorMethod = BottomNavigationItem.class.getDeclaredMethod("getActiveColor", Context.class);
//                getActiveColorMethod.setAccessible(true);
//                return (int) getActiveColorMethod.invoke(bottomNavigationItem, this);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Color.TRANSPARENT;
    }
}


package studydemo.www.doloop.com.studtydemo;

import android.graphics.Color;
import android.support.v4.app.Fragment;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import studydemo.www.doloop.com.studtydemo.hook.HookFragment;
import studydemo.www.doloop.com.studtydemo.router.RouterFragment;
import studydemo.www.doloop.com.studtydemo.rxjava.RxJavaFragment;

/**
 * Created by zhaonan on 17/1/10.
 */

public class FragmentsDef {

    public enum FRAGMENT_DEMO {
        ROUTER(new RouterFragment(), "Router", R.drawable.ic_home_white_24dp, R.color.colorPrimary),
        RXJAVA(new RxJavaFragment(), "RxJava", R.drawable.ic_home_white_24dp, R.color.teal),
        HOOK(new HookFragment(), "Hook", R.drawable.ic_book_white_24dp, R.color.blue);

        private Fragment mFragment;
        private int mColorRes;
        private int mIconRes;
        private String mTitle;

        // 构造方法
        FRAGMENT_DEMO(Fragment fragment, String title, int iconRes, int colorRes) {
            this.mFragment = fragment;
            this.mTitle = title;
            this.mColorRes = colorRes;
            this.mIconRes = iconRes;
        }

        public static List<Fragment> getFragments() {
            List<Fragment> retList = new ArrayList<>();
            for (FRAGMENT_DEMO fragDemo : FRAGMENT_DEMO.values()) {
                retList.add(fragDemo.mFragment);
            }
            return retList;
        }

        public static void appendBottomBarItems(BottomNavigationBar bottomNavigationBar) {

            BottomNavigationItem btmNavItem;
            FRAGMENT_DEMO[] vals = FRAGMENT_DEMO.values();

            for (int i = 0; i < vals.length; i++) {
                FRAGMENT_DEMO fragDemo = vals[i];
                btmNavItem = new BottomNavigationItem(fragDemo.mIconRes, fragDemo.mTitle).setActiveColorResource(fragDemo.mColorRes);
                if(i == 0) {
                    BadgeItem numberBadgeItem = new BadgeItem()
                            .setBorderWidth(4)
                            .setBackgroundColor(Color.RED)
                            .setText("55")
                            .setHideOnSelect(true);
                    btmNavItem.setBadgeItem(numberBadgeItem);
                }
                bottomNavigationBar.addItem(btmNavItem);
            }
        }
    }
}

package studydemo.www.doloop.com.studtydemo.hook;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import studydemo.www.doloop.com.studtydemo.R;
import studydemo.www.doloop.com.studtydemo.base.BaseFragmnet;
import studydemo.www.doloop.com.studtydemo.utils.L;
import timber.log.Timber;

/**
 * Created by zhaonan on 17/1/10.
 */

public class HookFragment extends BaseFragmnet {

    private TextView tv;
    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hook, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hookClipboardManager();

        tv = $(R.id.text);
        btn = $(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SecondActivity.startMe(getActivity());
            }
        });

        ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText("data", "剪贴板里面原始的数据"));
        ClipData cd = cm.getPrimaryClip();
        String msg = cd.getItemAt(0).getText().toString();
        tv.setText(msg);
    }

    private void hookClipboardManager(){
        try{
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            Method getService = serviceManager.getDeclaredMethod("getService", String.class);
            IBinder rawBinder = (IBinder) getService.invoke(null, Context.CLIPBOARD_SERVICE);

            IBinder hookedBinder = (IBinder) Proxy.newProxyInstance(serviceManager.getClassLoader(),
                    new Class<?>[]{IBinder.class},
                    new IClipboardHookBinderHandler(rawBinder));
            Field cacheField = serviceManager.getDeclaredField("sCache");
            cacheField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<String, IBinder> cache = (Map<String, IBinder>)cacheField.get(null);
            cache.put(Context.CLIPBOARD_SERVICE, hookedBinder);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static class HookBinderInvocationrHandler implements InvocationHandler {
        private Object base;
        public HookBinderInvocationrHandler(IBinder base, Class<?> subClass){
            try{
                Method asInterface = subClass.getDeclaredMethod("asInterface", IBinder.class);
                this.base = asInterface.invoke(null, base);
            }catch(Exception e){
                e.printStackTrace();
                L.e(e.getLocalizedMessage());
            }
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {

            Timber.i("method.getName "+method.getName());

            if("hasPrimaryClip".equals(method.getName())) {
                return false;
            }

            if("getPrimaryClip".equals(method.getName())) {
                //return ClipData.newPlainText(null, "hook剪贴板getPrimaryClip");
            }

            if("setPrimaryClip".equals(method.getName())) {
                for(int i = 0; i < args.length;i++) {
                    if(args[i] instanceof ClipData) {
                        ClipData cData = (ClipData) args[i] ;
                        String originalStr = cData.getItemAt(0).getText().toString();
                        args[i] = ClipData.newPlainText(null, "hook剪贴板setPrimaryClip 原始str为: "+originalStr);
                    }
                }
            }

            return method.invoke(base, args);
        }

    }

    private static class IClipboardHookBinderHandler implements InvocationHandler {

        private IBinder base;
        private Class<?> stub;
        private Class<?> iinterface;

        public IClipboardHookBinderHandler(IBinder base)
        {
            this.base = base;
            try{
                this.stub = Class.forName("android.content.IClipboard$Stub");
                this.iinterface = Class.forName("android.content.IClipboard");
            }catch(Exception e) {
                Log.i("ttt", "eee "+e.getLocalizedMessage());
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            Log.i("ttt","method " + method.getName());
            // TODO Auto-generated method stub
            if("queryLocalInterface".equals(method.getName())) {
                return Proxy.newProxyInstance(base.getClass().getClassLoader(),
                        new Class<?>[]{this.iinterface},
                        new HookBinderInvocationrHandler(base, stub));
            }

            return method.invoke(base, args);
        }
    }

}

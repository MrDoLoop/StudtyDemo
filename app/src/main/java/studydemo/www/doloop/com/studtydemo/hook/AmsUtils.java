package studydemo.www.doloop.com.studtydemo.hook;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import studydemo.www.doloop.com.studtydemo.MyApplication;

public class AmsUtils {
	//https://android.googlesource.com/platform/frameworks/base/+/742a67127366c376fdf188ff99ba30b27d3bf90c/core/java/android/app/ActivityManagerNative.java
	//https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/util/Singleton.java
	//https://android.googlesource.com/platform/frameworks/base/+/0e40462e11d27eb859b829b112cecb8c6f0d7afb/core/java/android/app/ActivityThread.java
	
	private static final String TARGET_INTENT_ARG = "TARGET_INTENT_ARG";
	private static int LAUNCH_ACTIVITY_MSG = 100;
	
	
	public static void initHook() {
		hookAMS();
		hookSystemHandler();
	}
	
	private static void hookAMS() {
		try {
			Class<?> activityManagerNativeClass = Class.forName("android.app.ActivityManagerNative");
			Field gDefaultField = activityManagerNativeClass.getDeclaredField("gDefault");
			gDefaultField.setAccessible(true);
			Object gDefault = gDefaultField.get(null);
			
			Class<?> singleton = Class.forName("android.util.Singleton");
			Field mInstanceField = singleton.getDeclaredField("mInstance");
			mInstanceField.setAccessible(true);
			
			Object rawIActivityManager = mInstanceField.get(gDefault);
			Class<?> iActivityManagerInterface = Class.forName("android.app.IActivityManager");
			
			Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
					new Class<?>[]{iActivityManagerInterface},
					new AmsHookBinderInvocationHandler(rawIActivityManager));
			mInstanceField.set(gDefault, proxy);
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("ttt", Log.getStackTraceString(e));
		}
	}

	private static void hookSystemHandler() {
		try {
			Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
			Field currentActivityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
			currentActivityThreadField.setAccessible(true);
			Object currentActivityThread = currentActivityThreadField.get(null);
			Field mHField = activityThreadClass.getDeclaredField("mH");
			mHField.setAccessible(true);
			Handler mH = (Handler) mHField.get(currentActivityThread);
			Field mCallBackField = Handler.class.getDeclaredField("mCallback");
			mCallBackField.setAccessible(true);
			mCallBackField.set(mH, new ActivityThreadHandlerCallback(mH));
		} catch (Exception e) {
			Log.i("ttt", Log.getStackTraceString(e));
		}
	}
	
	private static class ActivityThreadHandlerCallback implements Handler.Callback {
		private Handler base;
		public ActivityThreadHandlerCallback (Handler base) {
			this.base = base;
			try {
				Class<?> clazz = Class.forName("android.app.ActivityThread$H");
				Field field = clazz.getField("LAUNCH_ACTIVITY");
				LAUNCH_ACTIVITY_MSG = field.getInt(null);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(msg.what == LAUNCH_ACTIVITY_MSG) {
				handleLaunchActivity(msg);
			}
			base.handleMessage(msg);
			return true;
		}
		
		private void handleLaunchActivity(Message msg) {
			Object obj = msg.obj;
			try{
				Field intent = obj.getClass().getDeclaredField("intent");
				intent.setAccessible(true);
				Intent raw = (Intent) intent.get(obj);
				Intent targetIntent = raw.getParcelableExtra(TARGET_INTENT_ARG);
				raw.setComponent(targetIntent.getComponent());
			}catch(Exception e){
				
			}
		}
	}
	
	
	private static class AmsHookBinderInvocationHandler implements InvocationHandler {

		private Object base;
		public AmsHookBinderInvocationHandler(Object base) {
			this.base = base;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Log.i("ttt", "method "+method.getName());
			if("startActivity".equals(method.getName())) {
				// replace targetClass in original Intent
				
				int index = 0;
				for(int i = 0; i < args.length;i++) {
					Log.i("ttt", "args "+args[i].getClass());
					if(args[i] instanceof Intent) {
						index = i;
						break;
					}
				}
				
				Intent rawIntent = (Intent) args[index];
				Intent newIntent = new Intent();
				String targetPkg = MyApplication.getAppInstance().getPackageName();
				ComponentName componentName = new ComponentName(targetPkg, ProxyActivity.class.getName());
				newIntent.setComponent(componentName);
				newIntent.putExtra(TARGET_INTENT_ARG,rawIntent);
				args[index] = newIntent;
			}
			
			return method.invoke(base, args);
		}
		
	}
	
	
}

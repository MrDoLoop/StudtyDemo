package studydemo.www.doloop.com.studtydemo.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import studydemo.www.doloop.com.studtydemo.utils.L;

/**
 * Created by zhaonan on 17/1/18.
 */
// http://www.jianshu.com/p/bb9343e8af17
@Aspect
public class MethodTracer {

    @Before("execution (protected void studydemo.www.doloop.com.studtydemo.MainActivity.onCreate(android.os.Bundle))")
    public void adviceOnExecution(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        L.i("@Before execution "+methodSignature.toString());
    }

    @Before("call (protected void studydemo.www.doloop.com.studtydemo.MainActivity.onCreate(android.os.Bundle))")
    public void adviceOnCall(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        L.i("@Before call "+methodSignature.toString());
    }
}

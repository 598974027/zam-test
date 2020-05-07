package agent.dynamicAgent;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 功能描述: Cglib动态代理，实现MethodInterceptor接口
 *
 * @author zhangam
 * @time 2019/3/17 11:33
 * @see
 **/
public class CglibProxy implements MethodInterceptor {

    private Object target;

    @Override
    public Object intercept(Object obj, Method method, Object[] arr, MethodProxy proxy) throws Throwable {
        System.out.println("Cglib动态代理，监听开始！");
        Object invoke = method.invoke(target, arr);
        System.out.println("Cglib动态代理，监听结束！");
        return invoke;
    }

    /**
     * 定义获取代理对象方法
     *
     * @param objectTarget
     * @return
     */
    public Object getCglibProxy(Object objectTarget) {
        //为目标对象target赋值
        this.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        //设置回调
        enhancer.setCallback(this);
        //创建并返回代理对象
        Object result = enhancer.create();
        return result;
    }

    public static void main(String[] args) {
        //实例化CglibProxy对象
        CglibProxy cglib = new CglibProxy();
        //获取代理对象
        UserManager user = (UserManager) cglib.getCglibProxy(new UserManagerImpl());
        user.delUser("admin");
    }

}
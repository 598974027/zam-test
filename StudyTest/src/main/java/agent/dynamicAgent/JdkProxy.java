package agent.dynamicAgent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 功能描述: JDK动态代理实现InvocationHandler接口
 *
 * @author zhangam
 * @time 2019/3/17 11:33
 * @see
 **/
public class JdkProxy implements InvocationHandler {

    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理，监听开始！");
        Object result = method.invoke(target, args);
        System.out.println("JDK动态代理，监听结束！");
        return result;
    }

    /**
     * 定义获取代理对象方法
     *
     * @param targetObject
     * @return
     */
    private Object getJDKProxy(Object targetObject) {
        //为目标对象target赋值
        this.target = targetObject;
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        //实例化JDKProxy对象
        JdkProxy jdkProxy = new JdkProxy();
        //获取代理对象
        UserManager user = (UserManager) jdkProxy.getJDKProxy(new UserManagerImpl());
        user.addUser("admin", "123123");
    }

}

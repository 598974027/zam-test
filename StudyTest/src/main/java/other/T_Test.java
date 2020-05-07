package other;

/**
 * 功能描述: 泛型测试
 *
 * @author zhangam
 * @time 2019/3/19 16:55
 * @see
 **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//虽然Test<T1,T2>，但类名字仍为Test。T1,T2只是告诉我们new的时候要加入泛型，更方便通用
//T1,T2可以任意定义，比如改成K,V都可以
//他们不会关联到其他类，只是在本类中通用
public class T_Test<T1, T2> {

    public static void main(String[] args) {
        System.out.println(new T_Test().getT1());
        System.out.println(new T_Test<String, Integer>().getT2());
        new T_Test<String, String>().getClassName(2);//T1,T2在new的时候用到，这里都为String
        new T_Test().getClassAndList(Integer.class);
        //HashMap的括号里面不能是T,E,T1,T2等不确定的东西,但可以是?
        HashMap<?, ?> map = new HashMap<Object, String>();
        List<?> list = new ArrayList<String>();
    }

    public T1 getT1() {
        //这里的“T1”替换成1或1.0都会报错
        return (T1) "T1";
    }

    T2 getT2() {
        //将自动转型为String
        return (T2) "T2";
    }

    public <T> void getClassName(T x) {
        //<T>必须放在void或者返回类型的前面
        System.out.println(x.getClass().getName());
    }

    public <T> Class<?> getClassAndList(Class<?> a) {
        //Class<T>前面缺少<T>必须定义，否则将出现编译错误
        //T改成其他字母都可以，但一定要声明
        // 返回类型和参数中的类型：Class<T>和Class<?>都可以。因为返回的a的类型为Class<T>,Class<?>可以通配
        //当两个都是Class<?>，参数中的?自动通配成T
        System.out.println(a.toString());
        System.out.println(a.getClass().getName());//传入的a是一个类，Class类型
        //参数里面的Class<T>最大的好处是如果方法里面定义了泛型，可以自动获取类型值，比如如下的List<T>可以自动获取到a的类型，不必强调死
        List<?> aa = new ArrayList<T>();
        //List<?> aa = new ArrayList<?>();会报错，以为ArrayList必须是一种类型，List<>起到通配的作用
        //List<T> aa = new ArrayList<T>();当然正确
        System.out.println(aa);
        return a;
    }

}
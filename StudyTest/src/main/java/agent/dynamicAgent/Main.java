package agent.dynamicAgent;

import agent.staticAgent.Java3y;
import agent.staticAgent.Programmer;

import java.lang.reflect.Proxy;

/**
 * 功能描述: 动态代理
 *
 * @author zhangam
 * @time 2019/3/17 11:35
 * @see
 **/
public class Main {

//    很明显的是：
//    静态代理需要自己写代理类-->代理类需要实现与目标对象相同的接口
//    而动态代理不需要自己编写代理类--->(是动态生成的)
//    使用静态代理时：
//    如果目标对象的接口有很多方法的话，那我们还是得一一实现，这样就会比较麻烦
//    使用动态代理时：
//    代理对象的生成，是利用JDKAPI，动态地在内存中构建代理对象(需要我们指定创建 代理对象/目标对象 实现的接口的类型)，并且会默认实现接口的全部方法

    public static void main(String[] args) {
        // 被代理对象
        Java3y java3y = new Java3y();
//        这里写图片描述
//        参数一：生成代理对象使用哪个类装载器【一般我们使用的是被代理类的装载器】
//        参数二：生成哪个对象的代理对象，通过接口指定【指定要被代理类的接口】
//        参数三：生成的代理对象的方法里干什么事【实现handler接口，我们想怎么实现就怎么实现】
        //代理对象
        Programmer programmerWaterArmy = (Programmer) Proxy.newProxyInstance(java3y.getClass().getClassLoader(), java3y.getClass().getInterfaces(), (proxy, method, args1) -> {
            if (method.getName().equals("coding")) {
                System.out.println("调用前");
                method.invoke(java3y, args1);
                System.out.println("调用后");
                return 0;
            } else {
                method.invoke(java3y, args1);
                return 0;
            }
        });
        //每当Java3y写完文章，水军都会点赞
        programmerWaterArmy.coding();
    }

}

package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:
 * 方法区和永久代又有着本质的区别，前者是 JVM 的规范，而后者则是 JVM 规范的一种实现；
 * 只有HotSpot才有 “PermGen space”（永久代）
 * <p>
 * JDK1.7中，存储在永久代的部分数据就已经转移到了Java Heap或者是 Native Heap。
 * 但永久代仍存在于JDK1.7中，并没完全移除，譬如符号引用(Symbols)转移到了native heap；
 * 字面量(interned strings)转移到了java heap；
 * 类的静态变量(class statics)转移到了java heap；
 * JDK 1.8中已经不存在永久代,被元空间替换
 * <p>
 * JDK1.7
 * -XX:PermSize=10m -XX:MaxPermSize=10m -XX:+HeapDumpOnOutOfMemoryError
 * 方法区溢出
 * <p>
 * JDK1.8
 * -XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=2m
 * 元空间溢出
 *
 * @author zhangam
 * @time 2020/5/22 15:10
 * @see
 **/
public class Test3 {

    public static void main(String[] args) {
        List list = new ArrayList();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

}

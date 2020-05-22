package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 堆溢出
 *
 * @author zhangam
 * @time 2020/5/22 15:10
 * @see
 **/
public class Test1 {

    static class A {
    }

    public static void main(String[] args) {
        List<A> list = new ArrayList<>();
        while (true) {
            list.add(new A());
        }
    }

}

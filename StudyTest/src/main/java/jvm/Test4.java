package jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 功能描述:
 * -Xmx20m -XX:MaxDirectMemorySize=10m
 * 直接内存溢出
 *
 * @author zhangam
 * @time 2020/5/22 15:10
 * @see
 **/
public class Test4 {

    private static final int _1MB = 1024 * 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            //unsafe直接想操作系统申请内存
            unsafe.allocateMemory(_1MB);
        }
    }

}

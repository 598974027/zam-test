package jvm;

/**
 * 功能描述:
 * -Xss128k
 * 栈溢出
 *
 * @author zhangam
 * @time 2020/5/22 15:10
 * @see
 **/
public class Test2 {

    static class A {

        private static int stackLength = 1;

        public void stackLeak() {
            stackLength++;
            stackLeak();
        }

    }

    public static void main(String[] args) {
        A a = new A();
        try {
            a.stackLeak();
        } catch (Exception e) {
            System.out.println("Stack deep : " + a.stackLength);
            e.printStackTrace();
        }
    }

}

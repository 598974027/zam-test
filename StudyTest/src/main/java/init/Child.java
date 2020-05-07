package init;

/**
 * 功能描述: Child
 *
 * @author zhangam
 * @time 2020/4/28 14:49
 * @see
 **/
public class Child extends Parent {

    /* 静态变量 */
    public static String s_StaticField = "子类--静态变量";

    /* 变量 */
    public String s_Field = "子类--变量";

    /* 静态初始化块 */
    static {
        System.out.println("子类--静态初始化块");
    }

    /* 初始化块 */ {
        System.out.println("子类--初始化块");
    }

    /* 构造器 */
    public Child() {
        System.out.println("子类--构造器");
    }

    /* 程序入口 */
    public static void main(String[] args) {
        System.out.println("子类main方法");
        new Child();
    }

}

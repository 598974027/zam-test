package init;

/**
 * 功能描述: Parent
 *
 * @author zhangam
 * @time 2020/4/28 14:49
 * @see
 **/
public class Parent {

    /* 静态变量 */
    public static String p_StaticField = "父类--静态变量";

    /* 变量 */
    public String p_Field = "父类--变量";
    protected int i = 1;

    /* 静态初始化块 */
    static {
        System.out.println("父类--静态初始化块--上");
        System.out.println(p_StaticField);
        p_StaticField = "lbj";
    }

    /* 初始化块 */ {
        System.out.println("父类--初始化块--上");
        System.out.println(i);
        i = 2;
    }

    /* 静态初始化块 */
    static {
        System.out.println("父类--静态初始化块--下");
        System.out.println(p_StaticField);
        p_StaticField = "zam";
    }

    /* 构造器 */
    public Parent() {
        System.out.println("父类--构造器");
        System.out.println(i);
        i = 3;
    }

    /* 初始化块 */ {
        System.out.println("父类--初始化块--下");
        System.out.println(i);
        i = 4;
    }

}

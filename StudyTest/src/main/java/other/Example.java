package other;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/3/21 20:34
 * @see
 **/
public class Example {
    int a = 1;
    Integer b = new Integer(2);
    int[] c = {1, 2, 3};
    char d = 'a';
    char[] e = {'a', 'b'};
    String f = new String("hello");
    Student g = new Student();

    public static void main(String[] args) {
/*        String a = "123";
        String b = new String("123");
        String c = "123";
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(c));
        change(a);
        change2(a);*/

        int a = 1;
        Integer b = 1;
        Integer c = new Integer(1);
        Integer d = new Integer(1);
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(c));
        System.out.println(System.identityHashCode(d));
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(b == c);
        System.out.println(c == d);

//        Example ex = new Example();
//        System.out.println(ex.g.id);
//        ex.change(ex.a, ex.b, ex.c, ex.d, ex.e, ex.f, ex.g);
//        System.out.println(ex.g.id);
    }

    public static void change(String str){
        System.out.println(System.identityHashCode(str));
        str = "haha";
        System.out.println(System.identityHashCode(str));
    }

    public static void change2(String str) {
        System.out.println(System.identityHashCode(str));
        str = new String("hehe");
        System.out.println(System.identityHashCode(str));
    }

    private void change(int a, Integer b, int[] c, char d, char[] e, String f, Student g) {
    a = 11;
    b = 22;
    c[0] = 44;
    d = 'd';
    e[0] = 'e';
    f = "hi";
    g.id=2;
    g.name="haha";
}


/*    第一个例子：基本类型
    void foo(int value) {
        value = 100;
    }
    foo(num); // num 没有被改变

    第二个例子：没有提供改变自身方法的引用类型
    void foo(String text) {
        text = "windows";
    }
    foo(str); // str 也没有被改变

    第三个例子：提供了改变自身方法的引用类型
    StringBuilder sb = new StringBuilder("iphone");
    void foo(StringBuilder builder) {
        builder.append("4");
    }
    foo(sb); // sb 被改变了，变成了"iphone4"。

    第四个例子：提供了改变自身方法的引用类型，但是不使用，而是使用赋值运算符。
    StringBuilder sb = new StringBuilder("iphone");
    void foo(StringBuilder builder) {
        builder = new StringBuilder("ipad");
    }
    foo(sb); // sb 没有被改变，还是 "iphone"。*/

}

/**
 * 功能描述:
 * 对于双引号""直接声明的字符串, 比如String a = "aa",会直接在scp中创建对象
 * 对于两个声明的字符串使用 " + " 拼接, 因为jvm的优化,会将拼接后的结果放入常量池.但是两个声明的字符串不会,(String s = “abc”+ “def”, 会直接生成“abcdef"字符串常量 而不把 “abc” "def"放进常量池)
 * 对于其中有一个不是声明的字符串,用变量相加的,编译器无法得知结果,会用StringBuilder进行创建新对象,不会将结果放到scp中
 *
 * @author zhangam
 * @time 2020/5/8 15:03
 * @see
 **/
public class StringTest {

    public static void main(String[] args) {
//        String str = "aa";
//        String str2 = new String("aa");
//        System.out.println(str == str2);
//        String str3 = str2.intern();
//        System.out.println(str == str3);

//        String s = new String("a") + new String("b");
//        String s1 = "ab" + "cd";//两个声明字符串相加,可知jvm会优化,直接在scp中创建"abcd"
//        String s2 = s.intern();//调用intern方法,发现scp没有"ab",将s在堆中的引用地址给s2
//        String s3 = "ab";//java先去scp找"ab",发现有,直接将其地址返回给s3
//        System.out.println(s == s2);//true
//        System.out.println(s3 == s2);//true

//        String s = new String("a") + new String("b");
//        String s1 = "ab" + "cd";//两个声明字符串相加,可知jvm会优化,直接在scp中创建"abcd"
//        String s3 = "ab";//java发现scp没有"ab",在scp创建新对象,然后返回地址给s3
//        String s2 = s.intern();//调用intern方法,发现scp有"ab",将s在scp中的引用地址给s2
//        System.out.println(s == s2);//false
//        System.out.println(s3 == s2);//true

//        String s = new String("a") + new String("b");
//        String s1 = "ab" + s;//String s1 = new StringBuilder("ab").append("ab").toString();
//        String s2 = s.intern();
//        String s3 = "ab";
//        System.out.println(s == s2);//false
//        System.out.println(s3 == s2);//true

        String ss0 = new String("zam") + new String("b");//常量池有zam和b,堆中有zam对象b对象zamb对象
        System.out.println("zam" == (new String("zam")).intern());//true
        System.out.println(ss0 == ss0.intern());//true

        String ss1 = new String("lbj");
        String ss2 = ss1 + "ok";//常量池有lbj和ok,堆中有lbj对象lbjok对象
        System.out.println(ss1 == "lbj".intern());//false
        System.out.println(ss1 == ss1.intern());//false
        System.out.println("ok" == (new String("ok")).intern());//true

    }


}

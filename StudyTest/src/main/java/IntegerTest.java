/**
 * 功能描述:
 * java中的基本类型的包装类基本都实现了常量池技术.
 * 即Byte,Short,Integer,Long,Character,Boolean。这5种包装类默认创建了数值[-128，127]的相应类型的缓存数据
 * 但是超出此范围仍然会去创建新的对象。 两种浮点数类型的包装类Float,Double并没有实现常量池技术。
 *
 * @author zhangam
 * @time 2020/5/8 15:04
 * @see
 **/
public class IntegerTest {

    public static void main(String[] args) {
        Integer a = 10;
        Integer b = 10;
        System.out.println(a == b);
        Integer c = 200;
        Integer d = 200;
        System.out.println(c == d);
        Long e = 200L;
        Long f = 200L;
        System.out.println(e == f);
        Long g = 20L;
        Long h = 20L;
        System.out.println(g == h);
        Double i = 20.0;
        Double j = 20.0;
        System.out.println(i == j);
    }

}

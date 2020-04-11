package lombok;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/7/30 9:10
 * @see
 **/
@Data
public class Class1 {

    public String att1;

    private String att2;

    public static void main(String[] args) {
        Class1 c = new Class1();
        c.setAtt1("hello");
    }

}

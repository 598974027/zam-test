package jcommander;

import com.beust.jcommander.JCommander;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/8 18:34
 * @see
 **/
public class Test {

    public static void main(String[] args) {
        ClientOptions co = new ClientOptions();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(co)
                .build();
        jCommander.parse(args);
        //打印参数
//        jCommander.usage();

        System.out.println(co.level);
        System.out.println(co.help);

    }

}

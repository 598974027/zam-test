package lombok;

import lombok.extern.log4j.Log4j2;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/7/30 9:10
 * @see
 **/

//@Log4j
//@Slf4j
@Log4j2
public class Class2 {

    public String att1;

    @Getter
    @Setter
    private String att2;

    public static void main(String[] args) {
        Class2 c = new Class2();
        c.setAtt2("hello");
        log.info(c.getAtt2());
        log.trace(c.getAtt2());
    }

}

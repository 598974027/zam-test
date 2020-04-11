package shell;

import org.apache.commons.io.IOUtils;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/12/23 13:59
 * @see
 **/
public class WindowTest {

    public static void main(String[] args) {
        exec("java -version");
    }

    public static int exec(String cmds) {
        Process process = null;
        //0正常 1异常
        int ret = -1;
        try {
            process = Runtime.getRuntime().exec(cmds);
            process.waitFor(1000 * 30, TimeUnit.SECONDS);
            IOUtils.readLines(process.getInputStream(), Charset.defaultCharset().toString()).forEach(o -> System.out.println("normal----" + o));
            IOUtils.readLines(process.getErrorStream(), Charset.defaultCharset().toString()).forEach(o -> System.out.println("error----" + o));
            ret = process.exitValue();
            System.out.println("exitValue----" + ret);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            process.destroy();
        }
        return ret;
    }

}

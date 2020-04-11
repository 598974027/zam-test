package shell;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/12/23 14:00
 * @see
 **/
public class LinuxTest {

    public static void main(String[] args) {
//        exec("java -version");
//        exec("jps");
        exec("pwd");
    }

    public static int exec(String cmds) {
        Connection conn = null;
        InputStream stdOut = null;
        InputStream stdErr = null;
        //0正常 1异常
        int ret = -1;
        try {
            conn = new Connection("192.168.2.110", 22);
            conn.connect();
            if (conn.authenticateWithPassword("root", "intest@yw")) {
                Session session = conn.openSession();
                session.execCommand(cmds);
                stdOut = session.getStdout();
                IOUtils.readLines(stdOut, Charset.defaultCharset().toString()).forEach(o -> System.out.println("outStr----" + o));
                stdErr = session.getStderr();
                IOUtils.readLines(stdErr, Charset.defaultCharset().toString()).forEach(o -> System.out.println("outErr----" + o));
                session.waitForCondition(ChannelCondition.EXIT_STATUS, 1000 * 60);
                ret = session.getExitStatus();
                System.out.println("ret=" + ret);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            IOUtils.closeQuietly(stdOut);
            IOUtils.closeQuietly(stdErr);
        }
        return ret;
    }

}

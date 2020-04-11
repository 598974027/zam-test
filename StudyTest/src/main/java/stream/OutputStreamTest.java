package stream;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/5 14:31
 * @see
 **/
public class OutputStreamTest {

    public static void main(String[] args) throws Exception {
        //FileOutputStream
//        OutputStream os = new FileOutputStream("src/main/java/stream/test.txt", true);
//        PrintStream ps = new PrintStream(os);
//        ps.println("123456789");
//        ps.flush();
//        ps.close();
//        os.close();

        //BufferedOutputStream
//        OutputStream os = new FileOutputStream("src/main/java/stream/test.txt");
//        BufferedOutputStream bos = new BufferedOutputStream(os);
//        bos.write("我是缓冲输出流测试数据".getBytes());
//        bos.flush();
//        bos.close();
//        os.close();

        //ObjectInputStream
        OutputStream os = new FileOutputStream("src/main/java/stream/user.txt");//创建文件字节输出流对象
        ObjectOutputStream oos = new ObjectOutputStream(os);
        User user = new User("123", "456");
        oos.writeObject(user);
        oos.flush();
        oos.close();
        os.close();
    }

}

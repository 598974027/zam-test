package stream;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/5 14:31
 * @see
 **/
public class WriterTest {

    public static void main(String[] args) throws Exception {
        FileWriter fw = new FileWriter("");
        BufferedWriter bw = new BufferedWriter(new FileWriter(""));
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(""));
    }

}

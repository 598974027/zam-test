package stream;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/5 14:31
 * @see
 **/
public class ReaderTest {

    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("");
        BufferedReader br = new BufferedReader(new FileReader(""));
        InputStreamReader isr = new InputStreamReader(new FileInputStream(""));
    }

}

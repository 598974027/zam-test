package stream;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/5 14:31
 * @see
 **/
public class InputStreamTest {

    public static void main(String[] args) throws Exception {
        //FileInputStream
//        InputStream is = new FileInputStream("src/main/java/stream/test.txt");
//        int size = is.available();
//        for (int i = 0; i < size; i++) {
//            System.out.print(is.read() + "  ");
//        }
//        is.close();

        //BufferedInputStream
//        InputStream is = new FileInputStream("src/main/java/stream/test.txt");
//        BufferedInputStream bis = new BufferedInputStream(is);
//        bis.mark(10);
//        System.out.println((char) bis.read());
//        System.out.println((char) bis.read());
//        System.out.println((char) bis.read());
//        bis.reset();
//        System.out.println((char) bis.read());
//        bis.close();
//        is.close();

        //DataInputStream
//        InputStream is = new FileInputStream("src/main/java/stream/test.txt");
//        DataInputStream dis =new DataInputStream(is);
//        System.out.printf("readBoolean():%s\n", dis.readBoolean());
//        System.out.printf("readByte():0x%s\n", dis.readByte());
//        System.out.printf("readChar():0x%s\n", dis.readChar());
//        System.out.printf("readShort():0x%s\n", dis.readShort());
//        System.out.printf("readInt():0x%s\n", dis.readInt());
//        System.out.printf("readLong():0x%s\n", dis.readLong());
//        dis.close();
//        is.close();

        //ObjectInputStream
//        FileInputStream is = new FileInputStream("src/main/java/stream/user.txt");
//        ObjectInputStream ois = new ObjectInputStream(is);
//        User user = (User)ois.readObject();
//        System.out.println(user);
//        ois.close();
//        is.close();
    }

}

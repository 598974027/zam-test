package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * 功能描述: Test
 *
 * @author zhangaomin
 * @time 2021/7/15 16:21
 **/
public class Test {

    public static void main(String[] args) {
//        ByteBuf buf = Unpooled.buffer(10);
//        buf.writeBytes("csc".getBytes());
//        byte[] b = new byte[3];
//        buf.readBytes(b);
//        buf.clear();
////        buf.discardReadBytes();
//        buf.writerIndex(3);
//        byte[] b2 = new byte[3];
//        buf.readBytes(b2);
//        System.out.println(buf.readableBytes());
//        System.out.println(new String(b));
//        System.out.println(buf.writerIndex());
//        System.out.println(buf.readerIndex());

        ByteBuf buf = Unpooled.buffer(5);
        buf.writeBytes("HHHHH".getBytes(CharsetUtil.UTF_8));
        System.out.println(buf);
        buf.readerIndex(4);
        buf.discardReadBytes();
//        buf.clear();
        buf.writeBytes("AAA".getBytes(CharsetUtil.UTF_8));
        System.out.println(buf);

        ByteBuf buf2 = Unpooled.copiedBuffer("HHHHH".getBytes(CharsetUtil.UTF_8));
        System.out.println(buf2);
    }
}

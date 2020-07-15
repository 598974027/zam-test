package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import util.ByteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: MyCustomMessageDecoder
 *
 * @author zhangaomin
 * @time 2020/7/2 14:09
 **/
public class MyCustomMessageDecoder extends ByteToMessageDecoder {

    private final static int HEAD_LENGTH = 4;

    private final static int MAX_LENGTH = 4096;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        //数据包太小
        if (in.readableBytes() < HEAD_LENGTH) {
            System.out.println("客户端一次性发送数据包大小" + in.readableBytes() + "，应该大于头部长度值" + HEAD_LENGTH);
            return;
        }
        //数据包太大
        if (in.readableBytes() > MAX_LENGTH) {
            System.out.println("客户端一次性发送数据包大小" + in.readableBytes() + "，超过了最大值" + MAX_LENGTH);
            //清除数据，关闭会话
            in.clear();
            in.discardReadBytes();
            ctx.channel().close();
            ctx.close();
            return;
        }
        if (in.isReadable()) {
            //标记当前位置
            in.markReaderIndex();
            //头部信息
            byte one = in.readByte();
            byte two = in.readByte();
            //读取数据长度
            int dataLength = in.readShort();
            if (dataLength <= 0 || dataLength >= MAX_LENGTH) {
                System.out.println("消息体长度值有问题" + dataLength);
                in.clear();
                in.discardReadBytes();
                ctx.channel().close();
                ctx.close();
                return;
            }
            //消息体长度大于所读数据包长度，继续等待数据
            if (dataLength > in.readableBytes()) {
                //标记回退
                in.resetReaderIndex();
                return;
            } else {
                List<Byte> data = new ArrayList<>();
                data.add(one);
                data.add(two);
                ByteUtil.arrayAddToList(data, ByteUtil.short2BigEndianBytes((short) dataLength));
                byte[] body = new byte[dataLength];
                in.readBytes(body);
                ByteUtil.arrayAddToList(data, body);
                out.add(ByteUtil.listToArray(data));
            }
        }
    }

}
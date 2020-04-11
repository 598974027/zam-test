package com.example.web_demo.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/30 19:23
 * @see
 **/
@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    ServerChannelHandler serverChannelHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("idleStateHandler",
                // 设置客户端多长时间没有数据后就自动断开
                new IdleStateHandler(1, 0, 0, TimeUnit.MINUTES));
        ch.pipeline().addLast(
                // （2） maxFrameLength - 发送的数据包最大长度； 1024 * 1024
                // （3） lengthFieldOffset - 长度域偏移量，指的是长度域位于整个数据包字节数组中的下标； 8+4+12
                // （4） lengthFieldLength - 长度域的自己的字节数长度。1
                // （5） lengthAdjustment – 长度域的偏移量矫正。 如果长度域的值，除了包含有效数据域的长度外，还包含了其他域（如长度域自身）长度，那么，就需要进行矫正。矫正的值为：包长 - 长度域的值 – 长度域偏移 – 长度域长。
                // （6） initialBytesToStrip – 丢弃的起始字节数。丢弃处于有效数据前面的字节数量。比如前面有4个节点的长度域，则它的值为4。
                new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN,//默认大端模式
                        1024 * 1024,
                        8 //固定报头
                                + 4//业务数据长度
                                + 12,//业务前面部分
                        4, 0, 0, true));
        ch.pipeline().addLast(serverChannelHandler);
    }

}

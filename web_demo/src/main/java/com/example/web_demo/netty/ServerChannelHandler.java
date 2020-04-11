package com.example.web_demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.springframework.stereotype.Component;

import java.net.SocketAddress;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/30 19:24
 * @see
 **/

@Component
@ChannelHandler.Sharable
public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        if (buf.readableBytes() > 0) {
            byte[] data = new byte[buf.readableBytes()];
            buf.readBytes(data);
            assert buf.refCnt() == 0;

        }
        ReferenceCountUtil.release(buf);
    }

    // 异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext session, Throwable ex) throws Exception {
        session.close();
    }

}
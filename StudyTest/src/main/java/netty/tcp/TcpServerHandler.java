package netty.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.time.LocalDateTime;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/19 10:29
 * @see
 **/
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.readBytes(bytes);
//        System.out.println(ctx.channel().localAddress().toString() + "服务端收到数据**********" + msg + " " + ctx.channel().isOpen());
        System.out.println(ctx.channel().remoteAddress().toString() + "服务端收到数据**********" + msg + " " + ctx.channel().isActive());
        ctx.write("hahaha");
//        ctx.writeAndFlush(ctx.toString() + "  " + ctx.name());//.addListener(ChannelFutureListener.CLOSE);
        ReferenceCountUtil.release(msg);
        ctx.fireChannelRead("hello");
    }

//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) {
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
//    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("服务端" + ctx.channel().localAddress() + "上线" + " " + ctx.channel().isOpen());
        System.out.println("服务端" + ctx.channel().remoteAddress() + "上线" + " " + ctx.channel().isActive());
        try {
            super.handlerAdded(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("服务端" + ctx.channel().localAddress() + "下线" + " " + ctx.channel().isOpen());
        System.out.println("服务端" + ctx.channel().remoteAddress() + "下线" + " " + ctx.channel().isActive());
        try {
            super.handlerRemoved(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("服务端连接可用**********" + ctx.channel().localAddress().toString() + " " + ctx.channel().isOpen());
        System.out.println("服务端连接可用**********" + ctx.channel().remoteAddress().toString() + " " + ctx.channel().isActive());
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("服务端连接不可用**********" + ctx.channel().localAddress().toString() + " " + ctx.channel().isOpen());
        System.out.println("服务端连接不可用**********" + ctx.channel().remoteAddress().toString() + " " + ctx.channel().isActive());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        e.printStackTrace();
        System.out.println("服务端连接异常1**********" + ctx.channel().localAddress().toString() + " " + ctx.channel().isOpen());
        System.out.println("服务端连接异常1**********" + ctx.channel().remoteAddress().toString() + " " + ctx.channel().isActive());
        ctx.close();
        System.out.println("服务端连接异常2**********" + ctx.channel().localAddress().toString() + " " + ctx.channel().isOpen());
        System.out.println("服务端连接异常2**********" + ctx.channel().remoteAddress().toString() + " " + ctx.channel().isActive());
    }

}
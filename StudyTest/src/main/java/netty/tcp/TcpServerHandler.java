package netty.tcp;

import io.netty.channel.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import javax.net.ssl.SSLSession;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/19 10:29
 * @see
 **/
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    private final Map<ChannelHandlerContext, Object> session = new ConcurrentHashMap();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.readBytes(bytes);
//        System.out.println(ctx.channel().localAddress().toString() + "服务端收到数据**********" + msg + " " + ctx.channel().isOpen());
        System.out.println(ctx.channel().remoteAddress().toString() + "服务端收到数据**********" + msg + " " + ctx.channel().isActive());
        ReferenceCountUtil.release(msg);
        ctx.write("write");
        ctx.writeAndFlush("ok").addListener(future -> {
            System.out.println(future.isDone());
            System.out.println(future.isSuccess());
            System.out.println(session.containsKey(ctx));
        });
        ChannelFuture channelFuture = null;
        try {
            channelFuture = ctx.writeAndFlush("123").sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.fireChannelRead("fireChannelRead");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
//        System.out.println("服务端" + ctx.channel().localAddress() + "上线" + " " + ctx.channel().isOpen());
//        System.out.println("服务端" + ctx.channel().remoteAddress() + "上线" + " " + ctx.channel().isActive());
        try {
            session.put(ctx, "ctx");
            super.handlerAdded(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
//        System.out.println("服务端" + ctx.channel().localAddress() + "下线" + " " + ctx.channel().isOpen());
//        System.out.println("服务端" + ctx.channel().remoteAddress() + "下线" + " " + ctx.channel().isActive());
        try {
            session.remove(ctx);
            super.handlerRemoved(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        e.printStackTrace();
//        System.out.println("服务端连接异常1**********" + ctx.channel().localAddress().toString() + " " + ctx.channel().isOpen());
//        System.out.println("服务端连接异常1**********" + ctx.channel().remoteAddress().toString() + " " + ctx.channel().isActive());
        ctx.close();
        session.remove(ctx);
//        System.out.println("服务端连接异常2**********" + ctx.channel().localAddress().toString() + " " + ctx.channel().isOpen());
//        System.out.println("服务端连接异常2**********" + ctx.channel().remoteAddress().toString() + " " + ctx.channel().isActive());
    }

}
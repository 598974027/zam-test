package netty.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/19 10:29
 * @see
 **/
public class TcpServerHandler2 extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress().toString() + "服务端2收到数据**********" + msg + " " + ctx.channel().isActive());
        ctx.write("writeAndFlush2");
        ctx.fireChannelRead("fireChannelRead2");
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        System.out.println(ctx.channel().remoteAddress().toString() + "服务端2收到数据**********" + msg + " " + ctx.channel().isActive());
//        ReferenceCountUtil.release(msg);
//        ctx.write("writeAndFlush2");
//        ctx.fireChannelRead("fireChannelRead2");
//    }

}
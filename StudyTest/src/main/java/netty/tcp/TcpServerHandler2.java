package netty.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/19 10:29
 * @see
 **/
public class TcpServerHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(msg);
        ctx.writeAndFlush("lbj");
//        ctx.fireChannelRead("ok");
        ReferenceCountUtil.release(msg);
    }

}
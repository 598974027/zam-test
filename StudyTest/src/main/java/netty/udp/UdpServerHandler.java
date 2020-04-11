package netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/19 10:29
 * @see
 **/
public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) {
        System.out.println("客户端信息**********" + msg.sender().toString());
        System.out.println(ctx.channel().localAddress().toString() + "服务端收到数据**********" + msg.content().toString(CharsetUtil.UTF_8));
//        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("hello".toCharArray(), CharsetUtil.UTF_8), msg.sender()));
        UdpServer.channelHandlerContext.writeAndFlush(new DatagramPacket(msg.content(), msg.sender()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("成功开启UDP服务channelHandlerContext");
        UdpServer.channelHandlerContext = ctx;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("服务端连接可用**********" + ctx.channel().localAddress().toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("服务端连接不可用**********" + ctx.channel().localAddress().toString());
    }

}
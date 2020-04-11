package netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;


/**
 * 功能描述: -Djavax.net.debug=ssl,handshake
 *
 * @author zhangam
 * @time 2019/4/19 10:27
 * @see
 **/
public class UdpServer {

    public static ChannelHandlerContext channelHandlerContext;

    public static void main(String[] args) throws InterruptedException {
        new UdpServer().start(6666);
    }

    public void start(int port) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group);
        bootstrap.channel(NioDatagramChannel.class);
        bootstrap.option(ChannelOption.SO_BROADCAST, true);
        bootstrap.handler(new ChannelInitializer<NioDatagramChannel>() {
            @Override
            protected void initChannel(NioDatagramChannel ch) throws Exception {
                ch.pipeline().addLast(new UdpServerHandler());
            }
        });
        ChannelFuture channelFuture = bootstrap.bind("127.0.0.1", port).sync();
        System.out.println("UDP服务端启动完毕, channel =" + channelFuture.channel().localAddress());
        channelFuture.channel().closeFuture().await();
    }

}

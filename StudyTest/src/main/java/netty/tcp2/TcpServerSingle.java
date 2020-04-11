package netty.tcp2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * 功能描述: -Djavax.net.debug=ssl,handshake
 *
 * @author zhangam
 * @time 2019/4/19 10:27
 * @see
 **/
public class TcpServerSingle {

    public static void main(String[] args) throws InterruptedException {
        new TcpServerSingle().start(8990);
    }

    public void start(int port) throws InterruptedException {
        SSLContext sslContext = MySSLContext.getSSLContext();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                SSLEngine sslEngine = sslContext.createSSLEngine();
                //服务器端模式
                sslEngine.setUseClientMode(false);
                //需要验证客户端，false为单向认证，true为双向认证
                sslEngine.setNeedClientAuth(false);
                ch.pipeline().addLast(new SslHandler(sslEngine));
                ch.pipeline().addLast(new StringEncoder());
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new TcpServerHandler());
            }
        });
        bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        ChannelFuture channelFuture = bootstrap.bind(port).sync();
        System.out.println("TCP服务端启动完毕, channel =" + channelFuture.channel().localAddress());
        channelFuture.channel().closeFuture().sync();
    }

}

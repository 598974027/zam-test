package netty.tcp2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.net.InetSocketAddress;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/19 10:27
 * @see
 **/
public class TcpClientSingle {

    public static void main(String[] args) throws InterruptedException {
        new TcpClientSingle().start("127.0.0.1", 8990);
    }

    public void start(String ip, int port) throws InterruptedException {
        SSLContext sslContext = MySSLContext.getSSLContext2();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(bossGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                SSLEngine sslEngine = sslContext.createSSLEngine();
                sslEngine.setUseClientMode(true);
                ch.pipeline().addLast(new SslHandler(sslEngine));
                ch.pipeline().addLast(new StringEncoder());
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new TcpClientHandler());
            }
        });
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(ip, port)).sync();
        Channel channel = future.awaitUninterruptibly().channel();
        System.out.println("TCP客户端启动完毕, channel =" + channel.remoteAddress());
        while (true) {
//            System.out.println("aaaa:---" + channel.isActive());
//            System.out.println("bbbb:---" + channel.isOpen());
            channel.writeAndFlush("123456789");
            Thread.sleep(5000);
        }
    }

}

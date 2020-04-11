package netty.tcp1;

import com.intest.base.util.ByteUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
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
public class TcpClient2 {

    public static void main(String[] args) throws InterruptedException {
        new TcpClient2().start("10.7.52.154", 63022);
    }

    public void start(String ip, int port) throws InterruptedException {
        SSLContext sslContext = MySSLContext.getSSLContext3();
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
//                ch.pipeline().addLast(new StringEncoder());
//                ch.pipeline().addLast(new FixedLengthFrameDecoder(5));
//                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new TcpClientHandler());
            }
        });
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(ip, port)).sync();
        Channel channel = future.awaitUninterruptibly().channel();
        System.out.println("TCP客户端启动完毕, channel =" + channel.remoteAddress());
        while (true) {
            System.out.println("aaaa:---" + channel.isActive());
            System.out.println("bbbb:---" + channel.isOpen());
            channel.writeAndFlush(Unpooled.copiedBuffer(ByteUtil.hexStringToBytes("4F11DE00000001012E00000041020814031A0A00309A0111011D0000000A180A01011201141A010322011A2A010A3201003A013040B406120100")));
            Thread.sleep(5000);
        }
    }

}

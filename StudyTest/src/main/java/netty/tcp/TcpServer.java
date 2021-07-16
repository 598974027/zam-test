package netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 功能描述: -Djavax.net.debug=ssl,handshake
 *
 * @author zhangam
 * @time 2019/4/19 10:27
 * @see
 **/
public class TcpServer {

    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    public static void main(String[] args) throws InterruptedException {
        TcpServer tcpServer = new TcpServer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                tcpServer.stop();
            }
        }).start();
        tcpServer.start(1234);
    }

    public void start(int port) throws InterruptedException {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new StringEncoder());//out
                    ch.pipeline().addLast(new FixedLengthFrameDecoder(5));//in
                    ch.pipeline().addLast(new StringDecoder());//in
                    ch.pipeline().addLast(new TcpServerHandler());//in
                    ch.pipeline().addLast(new TcpServerHandler2());//in
                }
            });
            bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
//            bootstrap.bind(port).addListener(future -> {
//                if (future.isSuccess()) {
//                    System.out.println("TCP服务端启动成功, port =" + port);
//                } else {
//                    System.out.println("TCP服务端启动失败" + future.cause());
//                    System.exit(0);
//                }
//            });
            ChannelFuture f = bootstrap.bind(port).sync();
            System.out.println("TCP服务端启动成功, port =" + port);
//            f.channel().closeFuture().sync();//阻塞当前进程
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TCP服务端启动失败");
            System.exit(0);
        }
    }

    public void stop() {
        try {
            if (bossGroup != null) {
                bossGroup.shutdownGracefully().sync();
            }
            if (workerGroup != null) {
                workerGroup.shutdownGracefully().sync();
            }
            System.out.println("TCP服务端已经停止");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("TCP服务端停止异常");
        }
    }

}

package com.example.web_demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/30 19:18
 * @see
 **/
@Component
public class NettyServer {

    //boss事件轮询线程组
    private EventLoopGroup boss = new NioEventLoopGroup();

    //worker事件轮询线程组
    private EventLoopGroup worker = new NioEventLoopGroup();

    @Value("${netty.port:2222}")
    private Integer port;

    @Autowired
    private ServerChannelInitializer serverChannelInitializer;

    /**
     * 开启Netty服务
     *
     * @return
     */
    public void start() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker);
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        serverBootstrap.channel(NioServerSocketChannel.class).childHandler(serverChannelInitializer);
        System.out.println("TCP服务启动完毕,port=" + this.port);
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止Netty服务
     */
    public void destroy() {
        worker.shutdownGracefully();
        boss.shutdownGracefully();
    }

}

package netty.tcp2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import javax.net.ssl.SSLSession;
import java.security.cert.X509Certificate;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/19 10:29
 * @see
 **/
public class TcpClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(ctx.channel().localAddress().toString() + "客户端收到数据**********" + msg.toString());
        System.out.println(ctx.channel().remoteAddress().toString() + "客户端收到数据**********" + msg.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接可用**********" + ctx.channel().localAddress().toString());
        System.out.println("客户端连接可用**********" + ctx.channel().remoteAddress().toString());
        ctx.pipeline().get(SslHandler.class).handshakeFuture().addListener(
                new GenericFutureListener<Future<Channel>>() {
                    @Override
                    public void operationComplete(Future<Channel> future) {
                        if (future.isSuccess()) {
                            System.out.println("握手成功");
                            SSLSession sslSession = ctx.pipeline().get(SslHandler.class).engine().getSession();
                            try {
                                X509Certificate[] arr = (X509Certificate[]) sslSession.getPeerCertificates();
                                for (int i = 0; i < arr.length; i++) {
//                                    System.out.println(i + "-域名标识：" + arr[i].getIssuerDN().toString());
//                                    System.out.println(i + "-主体标识：" + arr[i].getSubjectDN().toString());
//                                    System.out.println(i + "-序列号：" + arr[i].getSerialNumber().toString());
//                                    System.out.println(i + "-公钥：" + Base64.getEncoder().encodeToString(arr[i].getPublicKey().getEncoded()));
//                                    System.out.println(i + "-摘要算法：" + arr[i].getSigAlgName());
//                                    System.out.println(i + "-签名：" + Base64.getEncoder().encodeToString(arr[i].getSignature()));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("握手失败");
                        }
                    }
                });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接不可用**********" + ctx.channel().localAddress().toString());
        System.out.println("客户端连接不可用**********" + ctx.channel().remoteAddress().toString());
    }

}
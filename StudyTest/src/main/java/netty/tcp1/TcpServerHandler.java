package netty.tcp1;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.apache.hadoop.hbase.shaded.org.apache.commons.httpclient.util.DateUtil;

import javax.net.ssl.SSLSession;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/19 10:29
 * @see
 **/
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.readBytes(bytes);
        System.out.println(ctx.channel().localAddress().toString() + "服务端收到数据**********" + msg + " " + ctx.channel().isOpen());
        System.out.println(ctx.channel().remoteAddress().toString() + "服务端收到数据**********" + msg + " " + ctx.channel().isActive());
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("服务端" + ctx.channel().localAddress() + "上线" + " " + ctx.channel().isOpen());
        System.out.println("服务端" + ctx.channel().remoteAddress() + "上线" + " " + ctx.channel().isActive());
        try {
            super.handlerAdded(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("服务端" + ctx.channel().localAddress() + "下线" + " " + ctx.channel().isOpen());
        System.out.println("服务端" + ctx.channel().remoteAddress() + "下线" + " " + ctx.channel().isActive());
        try {
            super.handlerRemoved(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("服务端连接可用**********" + ctx.channel().localAddress().toString() + " " + ctx.channel().isOpen());
        System.out.println("服务端连接可用**********" + ctx.channel().remoteAddress().toString() + " " + ctx.channel().isActive());
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
                                    if (!MySSLContext.caSerialNumber.equals(arr[i].getSerialNumber().toString())) {
                                        System.out.println(i + "-域名标识：" + arr[i].getIssuerDN().toString());
                                        System.out.println(i + "-主体标识：" + arr[i].getSubjectDN().toString());
                                        System.out.println(i + "-序列号：" + arr[i].getSerialNumber().toString());
//                                    System.out.println(i + "-公钥：" + Base64.getEncoder().encodeToString(arr[i].getPublicKey().getEncoded()));
//                                    System.out.println(i + "-摘要算法：" + arr[i].getSigAlgName());
//                                    System.out.println(i + "-签名：" + Base64.getEncoder().encodeToString(arr[i].getSignature()));
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            future.cause().printStackTrace();
                            if (future.cause().getMessage().contains("Received fatal alert: certificate_unknown")) {
                                System.out.println("握手失败，服务端证书异常：" + future.cause());
                            } else if (future.cause().getMessage().contains("handshake timed out")) {
                                System.out.println("握手失败，终端无证书：" + future.cause());
                            } else if (future.cause().getMessage().contains("General SSLEngine problem")) {
                                System.out.println("握手失败，终端证书异常：" + future.cause());
                            } else {
                                System.out.println("握手失败，其它异常：" + future.cause());
                            }
                        }
                    }
                });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("服务端连接不可用**********" + ctx.channel().localAddress().toString() + " " + ctx.channel().isOpen());
        System.out.println("服务端连接不可用**********" + ctx.channel().remoteAddress().toString() + " " + ctx.channel().isActive());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
//        System.out.println("服务端连接异常1**********" + ctx.channel().localAddress().toString() + " " + ctx.channel().isOpen());
//        System.out.println("服务端连接异常1**********" + ctx.channel().remoteAddress().toString() + " " + ctx.channel().isActive());
//        if (e.getMessage().contains("javax.net.ssl.SSLException: Received fatal alert: certificate_unknown")) {
//            System.out.println("服务端证书异常：" + e.getMessage());
//        } else if (e.getMessage().contains("javax.net.ssl.SSLHandshakeException: General SSLEngine problem")) {
//            System.out.println("终端证书异常：" + e.getMessage());
//        } else {
//            e.printStackTrace();
//        }
        //有的异常若不关闭连接还可以接受数据
        ctx.close();
//        System.out.println("服务端连接异常2**********" + ctx.channel().localAddress().toString() + " " + ctx.channel().isOpen());
//        System.out.println("服务端连接异常2**********" + ctx.channel().remoteAddress().toString() + " " + ctx.channel().isActive());
    }

}
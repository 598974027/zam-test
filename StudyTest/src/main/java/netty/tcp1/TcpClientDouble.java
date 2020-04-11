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
public class TcpClientDouble {

    public static void main(String[] args) throws InterruptedException {
        new TcpClientDouble().start("127.0.0.1", 8989);
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
//            System.out.println("aaaa:---" + channel.isActive());
//            System.out.println("bbbb:---" + channel.isOpen());
            channel.writeAndFlush(Unpooled.copiedBuffer(ByteUtil.hexStringToBytes("4F11DE00000002036F020000D90C0A130C1E0A29282A0311015E020000AB0A740A180A01011201131A010C22011E2A010A3201293A010F40B808122B0A09051C38011A010120D00F32010038EE2942150514F04A001A01012201012A01003201003A01001ACB090A104116A7980A11C837AAF9E6871CF100331000100010D00F10E0D4031000100010E05D100010F02E10001090A52F100010E8D80310D00F0122011E190440A01F10904E10C0B80210A0AD2510E8E5020D1C24CE2810E8DF0410B0872C0D1118F0AB011080FA01050E04D00F015D04A80209610000091019543E0A0024D00F10E0DA011090BC06013101074CC03E10E0F72B100010A0E50A10B4CC141080A4E801B8016530C0BB0110C0F20610B03C10CA3D050E011608F8F42B05A624904E10A096031090B608015709D214D00F10B0EA010107117A017200D405F118C09D0B10E0CE05011210F83C10A03D0D03090C150604F83C0D0310C83D10D03C0D062E2D0000D0053C1527091B2E0F00090C04A03D0D09155A092A5206001524463C0009120921523C0000A80590091B092D157B1509153F15091530090904A03D353131395E080025952199FE04006A040021E74E60005E1800112C4E2000111408B0A219FE040005045E18015E18003480EB0610F09C0610A09C0110C0AC190808E0D702051808D00F100123013F04B0E7091341D708E0CB066123013201DE08B0DB060523626203014F0D34012C41F710CB06109843615A001F051A1907018F017008C0B802051A0D2D04807D051A04D00F012B08B8D60401070145012B6156110C01102E040004F02E051B0D078512758E1D0E05BB0919320B00050D010554221408D60715C3F55E4120012A010032003A03420100")));
            Thread.sleep(5000);
        }
    }

}

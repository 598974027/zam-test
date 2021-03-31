/*
 * 文 件 名: UdpReceive.java
 * 创 建 人: zhangam
 * 创建时间: 2018-9-19下午3:12:25
 * 描    述:
 *
 * 修 改 人:
 * 修改时间:
 * 修改内容:
 */
package socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpReceive {

    public static void main(String[] args) throws Exception {
        // 1.创建udp socket服务，建立端点
        DatagramSocket ds = new DatagramSocket(6666);

        while (true) {
            // 2.定义数据包
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);

            // 3.通过服务的receive方法将接收到的数据存入到数据包中
            ds.receive(dp);// 阻塞式方法

            // 4.通过数据包的方法获得数据包中的数据
            String ip = dp.getAddress().getHostAddress();

            // 获取到有效的数据
            String data = new String(dp.getData(), 0, dp.getLength());
            int port = dp.getPort();

            System.out.println(ip + "..." + data + "...." + port);

            dp.setData("zam".getBytes());
            ds.send(dp);

            byte[] buf2 = "zam2".getBytes();
            DatagramPacket dp2 = new DatagramPacket(buf2, buf2.length, InetAddress.getByName(ip), port);
            ds.send(dp2);

            DatagramSocket ds2 = new DatagramSocket();
            byte[] buf3 = "zam3".getBytes();
            DatagramPacket dp3 = new DatagramPacket(buf3, buf3.length, InetAddress.getByName(ip), port);
            ds2.send(dp3);
            ds2.close();

        }
        // 5 关闭资源
        // ds.close();
    }
}

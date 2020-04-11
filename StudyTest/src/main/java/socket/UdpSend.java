/*
 * 文 件 名: UdpSend.java
 * 创 建 人: zhangam
 * 创建时间: 2018-9-19下午3:12:07
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

public class UdpSend {
	public static void main(String[] args) throws Exception {
		// 1.创建socket服务 通过DatagramSocket对象
		DatagramSocket ds = new DatagramSocket(8888);// 指定用8888端口发送
//		DatagramSocket ds = new DatagramSocket(8888);// 随机指定端口发送
		
		// 2.确定数据，并封装成数据包 通过DatagramPacket对象
		String str = "你好 ";
		byte[] buf = str.getBytes();
		DatagramPacket dp = new DatagramPacket(buf, buf.length,
				InetAddress.getByName("127.0.0.1"), 10000);

		// 3.通过socket服务 将已有的数据包发送出去 通过send()方法
		ds.send(dp);

		// 4.关闭资源
		ds.close();
	}
}

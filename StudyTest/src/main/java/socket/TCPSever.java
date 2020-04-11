/*
 * 文 件 名: TCPSever.java
 * 创 建 人: zhangam
 * 创建时间: 2018-9-19下午3:14:27
 * 描    述:
 *
 * 修 改 人:
 * 修改时间:
 * 修改内容:
 */
package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2018-9-19下午3:14:27
 * @version
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TCPSever {

	public static void main(String[] args) throws Exception {
		try {
			// 1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
			ServerSocket serverSocket = new ServerSocket(8989);
			Socket socket = null;
			// 记录客户端的数量
			int count = 0;
			System.out.println("***服务器即将启动，等待客户端的连接***");
			// 循环监听等待客户端的连接
			while (true) {
				// 调用accept()方法开始监听，等待客户端的连接
				socket = serverSocket.accept();

				//获取输入流，并读取客户端信息
				InputStream  is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String info=null;
				while((info=br.readLine())!=null){//循环读取客户端的信息
					System.out.println("我是服务器，客户端说："+info);
				}
				socket.shutdownInput();//关闭输入流

				//获取输出流，响应客户端的请求
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				pw.write("欢迎您！");
				pw.flush();//调用flush()方法将缓冲输出
				socket.shutdownOutput();// 关闭输出流

				count++;// 统计客户端的数量
				System.out.println("客户端的数量：" + count);
				InetAddress address = socket.getInetAddress();
				System.out.println("当前客户端的IP：" + address.getHostAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

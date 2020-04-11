/*
 * 文 件 名: TCPClient.java
 * 创 建 人: zhangam
 * 创建时间: 2018-9-19下午3:15:25
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
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2018-9-19下午3:15:25
 * @version
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TCPClient {

	public static void main(String[] args) throws Exception {
		try {
			// 1.创建客户端Socket，指定服务器地址和端口
			Socket socket = new Socket("localhost", 1234);

			// 2.获取输出流，向服务器端发送信息
			OutputStream os = socket.getOutputStream();// 字节输出流
			PrintWriter pw = new PrintWriter(os);// 将输出流包装为打印流
			pw.write("用户名：zam;密码：123456");
			pw.flush();
			socket.shutdownOutput();// 关闭输出流

			// 3.获取输入流，并读取服务器端的响应信息
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("我是客户端，服务器说：" + info);
			}
			socket.shutdownInput();//关闭输入流

			// 4.关闭资源
			br.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

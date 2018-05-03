package Netty连接步骤;

public class Netty连接步骤 {
	
	/**
	 * Netty实现通信的步骤
	 * @param args
	 * 1.创建两个的NIO线程组，一个用于网络事件处理（接受客户端的连接），另一个则进行网络通信读写。
	 * 2.创建一个ServerBootStrap对象，配置Netty的一系列参数，例如接受传出数据的缓存大小等等
	 * 3.创建一个实际处理数据的类ChannelInitializer，进行初始化的准备工作，比如设置接受传出数据的字符集，格式，已经实际处理数据的接口
	 * 4.绑定端口，执行同步阻塞方法等待服务器端启动即可
	 * 
	 */
	public static void main(String[] args) {
		
	}

}

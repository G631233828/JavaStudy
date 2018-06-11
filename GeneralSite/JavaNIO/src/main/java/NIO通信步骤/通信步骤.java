 package NIO通信步骤;

public class 通信步骤 {

	
	/**
	 * 关于NIO的通信步骤
	 * @param args
	 * 1.创建ServerSocketChannel，为它配置非阻塞模式
	 * 2.绑定监听，配置TCP参数，录入backlog大小等
	 * 3.创建一个独立的IO线程，用于轮询多路复用器Selector
	 * 4.创建Selector，将之前创建的ServerSocketChannel注册到Selector上，并且设置监听标识位SelectionKey.ACCEPT
	 * 5.启动IO线程，在循环体中执行Selector.select()方法，轮询就绪的通道
	 * 6.当轮询到处于就绪的通道时，需要进行判断操作位，如果是ACCEPT状态，说明是新的客户端接入，则调用accept方法接受新的客户端
	 * 7.设置新接入客户端的一些参数，如非阻塞，并将其通道继续注册到Selector之中，设置监听标识位等
	 * 8.如果轮询的通道操作位是READ，则进行读取，构造Buffer对象等
	 * 9.更细节的还有数据没法送完成继续发送的问题。。
	 * 
	 * 
	 */
	public static void main(String[] args) {
		
	}
}

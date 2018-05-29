package com.java.aio01;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ServerCompletionHander implements CompletionHandler<AsynchronousSocketChannel, Server> {

	public void completed(AsynchronousSocketChannel result, Server attachment) {
		// 当有下一个客户端接入的时候直接调用Server的accept方法，这样反复执行下去，保证多高客户端都可以阻塞
		attachment.assc.accept(attachment, this);
		read(result);

	}

	private void read(final AsynchronousSocketChannel result) {
		// 读取数据
		ByteBuffer buf = ByteBuffer.allocate(1024);
		result.read(buf, buf, new CompletionHandler<Integer, ByteBuffer>() {

			public void completed(Integer resultSize, ByteBuffer attachment) {
				// 进行读取之后，重置标识位
				attachment.flip();
				// 获取读取数据的字节数
				System.out.println("Server->" + "收到客户端的数据长度为：" + resultSize);
				// 获取读取的数据
				String data = new String(attachment.array()).trim();
				System.out.println("Server->" + "收到客户端数据信息为：" + data);
				String response = "服务器响应，收到客户端你发来的数据：" + data;
				write(result, response);
			}

			public void failed(Throwable exc, ByteBuffer attachment) {
				exc.printStackTrace();
			}
		});

	}

	protected void write(AsynchronousSocketChannel result, String response) {
		try {
			ByteBuffer buf = ByteBuffer.allocate(1024);
			buf.put(response.getBytes());
			buf.flip();//复位
			result.write(buf).get();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void failed(Throwable exc, Server attachment) {
		exc.printStackTrace();
	}

}

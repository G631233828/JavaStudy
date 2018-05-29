package com.java.aio01;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ServerCompletionHander implements CompletionHandler<AsynchronousSocketChannel, Server> {

	public void completed(AsynchronousSocketChannel result, Server attachment) {
		// ������һ���ͻ��˽����ʱ��ֱ�ӵ���Server��accept��������������ִ����ȥ����֤��߿ͻ��˶���������
		attachment.assc.accept(attachment, this);
		read(result);

	}

	private void read(final AsynchronousSocketChannel result) {
		// ��ȡ����
		ByteBuffer buf = ByteBuffer.allocate(1024);
		result.read(buf, buf, new CompletionHandler<Integer, ByteBuffer>() {

			public void completed(Integer resultSize, ByteBuffer attachment) {
				// ���ж�ȡ֮�����ñ�ʶλ
				attachment.flip();
				// ��ȡ��ȡ���ݵ��ֽ���
				System.out.println("Server->" + "�յ��ͻ��˵����ݳ���Ϊ��" + resultSize);
				// ��ȡ��ȡ������
				String data = new String(attachment.array()).trim();
				System.out.println("Server->" + "�յ��ͻ���������ϢΪ��" + data);
				String response = "��������Ӧ���յ��ͻ����㷢�������ݣ�" + data;
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
			buf.flip();//��λ
			result.write(buf).get();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void failed(Throwable exc, Server attachment) {
		exc.printStackTrace();
	}

}
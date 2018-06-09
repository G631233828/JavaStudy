package com.java.TCP拆包粘包;

import java.util.Arrays;

/**
 * 
 * @author fliay
 * 自己定义的协议  协议封装
 * 数据包格式
 * ————————|——————————|——————————|
 * 协议开始标志|   长度             |      数据    |
 * ————————|——————————|——————————|
 * 1.协议开始标志head_data,为int类型的数据，16进制标志为0x76
 * 2.传输数据的长度contentLength ,int类型
 * 3.要传输的数据，长度不应该超过2014，防止socket留攻击
 */
public class SmartCarProtocol {
	//消息开头信息标志
	private int head_data = ConstantValue.HEAD_DATA;
	
	//消息的长度
	private int contentLength;
	
	//消息的内容
	private byte[] content;
	
	public SmartCarProtocol(int contentLength,byte[] content){
		this.content= content;
		this.contentLength= contentLength;
	}

	public int getHead_data() {
		return head_data;
	}

	public void setHead_data(int head_data) {
		this.head_data = head_data;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "SmartCarProtocol [head_data=" + head_data + ", contentLength=" + contentLength + ", content="
				+ Arrays.toString(content) + "]";
	}
	
	
	
}

package com.java.TCP拆包粘包;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

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
 * 3.要传输的数据，长度不应该超过2014，防止socket流攻击
 */
public class SmartCarDecoder extends ByteToMessageDecoder{

	/**
	 * 协议开始的标准 head_data ,int 类型占据4个字节
	 * 表示数据的长度contentLength,int类型占据4个字节
	 */
	public final int BASE_LENGTH = 4+4;
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		
		//刻度长度必须大于基本长度
		if(in.readableBytes()>= BASE_LENGTH){
			//防止socket字节流攻击
			//防止，客户端传来的数据过大
			//因为，太大的数据是不合理的
			if(in.readableBytes()>2048){
				in.skipBytes(in.readableBytes());
			}
			//记录包头开始的index
			int  beginReader;
			while(true){
				//获取包头开始的index
				beginReader =in.readerIndex();
				//标记包头开始的index
				in.markReaderIndex();
				//督导了协议的开始标志，结束while循环
				if(in.readInt()==ConstantValue.HEAD_DATA){
					break;
				}
				
				//未读到包头，略过一个字节
				//每次略过，一个字节，去读取，包头信息的开始标记
				in.resetReaderIndex();
				in.readByte();
				
				//当略过，一个字节之后
				//数据包的长度，又变得不满足
				//此时，应该结束等待后面的数据到达
				if(in.readableBytes()<BASE_LENGTH){
					return;
				}
				
			}
			
			//消息的长度
			int length = in.readInt();
			//判断请求数据包数据是否到齐
			if(in.readableBytes()<length){
				//还原读指针
				in.readerIndex(beginReader);
				return;
			}
			//读取data数据
			byte[] data = new byte[length];
			in.readBytes(data);
			
			SmartCarProtocol protocol = new SmartCarProtocol(data.length, data);
			
			out.add(protocol);
			
			
		}
		
	}
	
	
	
	
	
}

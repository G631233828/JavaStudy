package com.java.TCP拆包粘包;


/**
 * 
 * @author fliay
 * 自己定义的协议 
 * 数据包格式
 * ————————|——————————|——————————|
 * 协议开始标志|   长度             |      数据    |
 * ————————|——————————|——————————|
 * 1.协议开始标志head_data,为int类型的数据，16进制标志为0x76
 * 2.传输数据的长度contentLength ,int类型
 * 3.要传输的数据，长度不应该超过2014，防止socket留攻击
 */
public class ConstantValue {

	//自定义协议开始的标志
	public static final int HEAD_DATA= 0x76;
}

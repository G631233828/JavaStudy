package ±àÂë½âÂëÆ÷.Protobuf;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		for (int i = 0; i < 10; i++) {
//			ctx.write(subReq(i));
//		}
//		ctx.flush();
ctx.writeAndFlush("Hello".getBytes());
	}

	private SubscribeReqProto.SubscribeReq subReq(int i) {

		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqId(i);
		builder.setUserName("Lilinfeng");
		builder.setProductName("Netty book for protobuf");
		List<String> address = new ArrayList<String>();
		address.add("NanJin YuHuaTai");
		address.add("sdfSD FSS");
		address.add("FSDFG GFDGDF");
		builder.addAllAddress(address);
		return builder.build();

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Receive server response:["+msg+"]");
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

}

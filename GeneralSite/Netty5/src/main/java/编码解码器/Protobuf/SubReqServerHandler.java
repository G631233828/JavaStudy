package ±àÂë½âÂëÆ÷.Protobuf;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import ±àÂë½âÂëÆ÷.Protobuf.SubscribeRespProto.SubscribeResp;

public class SubReqServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	
		System.out.println((String)msg);
		ctx.writeAndFlush("return hello");
		
//		SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq)msg;
//		if("LinlinFen".equalsIgnoreCase(req.getUserName())){
//			System.out.println("Service accept client subscribe req:["+req.toString()+"]");
//			ctx.writeAndFlush(resp(req.getSubReqId()));
//		}
	}

	private SubscribeResp resp(int subReqId){
		SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
		builder.setSubReqId(subReqId);
		builder.setRespCode(0);
		builder.setDesc("Netty book order succeed, 3 days later,sent to the designated address");
		return builder.build();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		
		cause.printStackTrace();
		ctx.close();
		
		
	}
	
	
	
	
	
	
	
	
}

package ±àÂë½âÂëÆ÷.Marshalling;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter {

	 @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	       
		 System.out.println("server:"+msg);
		 
	    }

	    @Override
	    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	       
	        ctx.writeAndFlush("hello server");
	    }

	    @Override
	    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	        System.out.println("Client is close");
	    }
	
}

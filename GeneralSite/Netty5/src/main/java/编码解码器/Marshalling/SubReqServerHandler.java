package 编码解码器.Marshalling;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter {

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      System.out.println("client:"+msg);
    }

	
	
    @Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush("hello client");
	}



	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

}

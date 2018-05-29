package webSocket;


import java.util.logging.Level;
import java.util.logging.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFlushPromiseNotifier;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

public class WebsoketServerHandler extends SimpleChannelInboundHandler<Object> {

	private static final Logger logger = Logger.getLogger(WebsoketServerHandler.class.getName());

	private static final HttpVersion HTTP_1_1 = null;

	private static final HttpResponseStatus BAD_REQUEST = null;

	private WebSocketServerHandshaker handshaker;
	
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		//��ͳ��http����
		if(msg instanceof FullHttpRequest){
			handleHttpRequest(ctx, (FullHttpRequest)msg);;
		}
		//webSocket����
		else if(msg instanceof WebSocketFrame){
			handleWebSocketFram(ctx,(WebSocketFrame)msg);
		}
		
		
		

	}
	
	
	public void channelReadComplete(ChannelHandlerContext ctx){
		ctx.flush();
	}
	
	
	
	
	
	private void handleWebSocketFram(ChannelHandlerContext ctx, WebSocketFrame msg) {
		//�ж��Ƿ�ر���·��ָ��
		if(msg instanceof CloseWebSocketFrame){
			handshaker.close(ctx.channel(), (CloseWebSocketFrame)msg.retain());
			return;
		}
		if(msg instanceof PingWebSocketFrame){
			ctx.channel().write(new PongWebSocketFrame(msg.content().retain()));
			return;
		}
		//������ֻ֧���ı���Ϣ����֧�ֶ�������Ϣ
		if(!(msg instanceof TextWebSocketFrame)){
			throw new UnsupportedOperationException(String.format("%s frame types not supported", msg.getClass().getName()));
		}
		String request = ((TextWebSocketFrame)msg).text();
		if(logger.isLoggable(Level.FINE)){
			logger.fine(String.format("%s received %s",ctx.channel(),request));
		}
		ctx.channel().write(new TextWebSocketFrame(request+",��ӭʹ�� Netty WebSocket����,����ʱ�̣�"+new java.util.Date().toString()));
		
	}





	private void handleHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req){
		
		//���HTTP����ʧ�ܷ���HTTP�쳣
		if(!req.decoderResult().isSuccess()||(!"websocket".equals(req.headers().get("Upgrade")))){
			sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HTTP_1_1,BAD_REQUEST));
			return;
		}
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8888/websocket", 
				null, false);
		handshaker= wsFactory.newHandshaker(req);
		if(handshaker == null){
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		}else{
			handshaker.handshake(ctx.channel(), req);
		}
		
	}

	
	




	private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req,
			DefaultFullHttpResponse res) {
		//����Ӧ����ͻ���
		if(res.status().code()!=200){
			ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(),CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
			setContentLength(res,res.content().readableBytes());
		}
		
		//����Ƿ�Keep-Alive�ر�����
		ChannelFuture  f = ctx.channel().writeAndFlush(res);
		if(!isKeepAlive(req)||res.status().code()!=200){
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}


	private boolean isKeepAlive(FullHttpRequest req) {
		// TODO Auto-generated method stub
		return false;
	}


	private void setContentLength(DefaultFullHttpResponse res, int readableBytes) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	

}
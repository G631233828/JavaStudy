package 编码解码器.Protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class SubReqServer {
	
	public void bind(int port){
		//配置服务端的NIO线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 100)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// TODO Auto-generated method stub
//					ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
//					ch.pipeline().addLast(new ProtobufDecoder(SubscribeReqProto.SubscribeReq.getDefaultInstance()));
//					ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
					ch.pipeline().addLast(new SubReqServerHandler());
					
					
					
				}
			});
			
			//绑定端口，同步等待成功
			ChannelFuture f = b.bind(port).sync();
			//等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		int port = 8888;
		if(args!=null&&args.length>0){
			try{
				port = Integer.valueOf(args[0]);
			}catch(Exception e){
				
			}
		}
		
		new SubReqServer().bind(port);
	}
	
	

}

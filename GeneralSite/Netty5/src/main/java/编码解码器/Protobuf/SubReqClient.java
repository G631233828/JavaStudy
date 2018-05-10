package ±àÂë½âÂëÆ÷.Protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFlushPromiseNotifier;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class SubReqClient {

	public void connect(int port, String host) {
		EventLoopGroup cgroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(cgroup).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							// TODO Auto-generated method stub

//							ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
//							ch.pipeline()
//									.addLast(new ProtobufDecoder(SubscribeReqProto.SubscribeReq.getDefaultInstance()));
//							ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
							ch.pipeline().addLast(new SubReqClientHandler());

						}
					});

			ChannelFuture c = b.connect(host, port).sync();
			c.channel().closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cgroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		int port=8888;
		if(args!=null&&args.length>0){
			try{
				port = Integer.valueOf(args[0]);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		new SubReqClient().connect(port, "127.0.0.1");
	}
	
	
}

package 编码解码器.MessagePack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import pojo.Student;
import pojo.User;
import 序列化.UserInfo;

public class EchoClientHandler extends ChannelHandlerAdapter {

	 // sendNumber为写入发送缓冲区的对象数量
    private int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    /**
     * 构建长度为userNum的User对象数组
     * @param userNum
     * @return
     */
//    private User[] getUserArray(int userNum) {
//        User[] users = new User[userNum];
//        User user = null;
//        for(int i = 0; i < userNum; i++) {
//            user = new User();
//            user.setName("ABCDEFG --->" + i);
//            user.setAge(i);
//            users[i] = user;
//        }
//        return users;
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        User[] users = getUserArray(sendNumber);
//        for (User user : users) {
//            ctx.writeAndFlush(user);
//        }
    	
//    	for(int i=0;i<sendNumber;i++){
//    		Student s = new Student();
//    		s.setAge(21);
//    		s.setCode(" "+i);
//    		s.setId(i);
//    		s.setName("fliay-----"+i);
//    		ctx.write(s);
//    	}
    	ctx.flush();
    	
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client receive the msgpack message : " + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
package 序列化;

import java.io.Serializable;
import java.nio.ByteBuffer;


/**
 * UserInfo是一个普通的POJO对象，它实现了Serializable接口，宾给钱生成了一个默认的序列号
 * @author fliay
 *
 */
public class UserInfo implements Serializable {

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String name;
	private int userId;
	
	public UserInfo bulidUserID(int userId){
		this.userId = userId;
		return this;
	}
	public UserInfo bulidUserName(String userName){
		this.name = userName;
		return this;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	/**
	 * 试用基于ByteBuffer的通用二进制解码技术对UserInfo对象进行编码
	 * 编码的结果仍然是byte数组，可以与传统的JDK序列号后的码流大小进行对比
	 * @return
	 */
	public byte[] codeC(){
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		byte[] value  = this.name.getBytes();
		buffer.putInt(value.length);
		buffer.put(value);
		buffer.putInt(this.userId);
		buffer.flip();
		value = null;
		byte[] result = new byte[buffer.remaining()];
		buffer.get(result);
		return result;
		
		
		
	}
	
	
	
	
}

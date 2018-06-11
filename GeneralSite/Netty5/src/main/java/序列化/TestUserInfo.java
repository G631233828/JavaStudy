package 序列化;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestUserInfo {

	
	public static void main(String[] args) throws IOException {
		
		UserInfo info = new UserInfo();
		info.bulidUserID(100).bulidUserName("welcome to netty");
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(info);
		oos.flush();
		oos.close();
		byte[] b = bos.toByteArray();
		System.out.println("The jdk serializable length is :"+b.length);
		bos.close();
		System.out.println("--------------------------------------");
		System.out.println("this byte array serializable length is :"+info.codeC().length);
		
//		The jdk serializable length is :99
//		--------------------------------------
//		this byte array serializable length is :24
	}
	
	
}

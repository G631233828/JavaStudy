package 编码解码器.MessagePack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

public class MessagePackTest {

	
	public static void main(String[] args) throws IOException {
		
		//创建需要编码的对象
		List<String> src = new ArrayList<String>();
			src.add("msgpack");
			src.add("kumofs");
			src.add("viver");
		
		MessagePack msgpack = new MessagePack();	
		//进行编码
		byte[] raw = msgpack.write(src);
		System.out.println(raw.toString());
	
		
		//进行解码
		List<String> dst1= msgpack.read(raw,Templates.tList(Templates.TString));
		System.out.println(dst1.get(0));
		System.out.println(dst1.get(1));
		System.out.println(dst1.get(2));
		
		
		
	}
	
	
}

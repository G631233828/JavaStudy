package test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class test2 {
	
	
	public static void main(String[] args) {
		// Student2 s = new Student2("2018-04-22 23:17:28","fliay","��У֪ͨ","�Ϻ��ֶ�","��ȫ�ﵽѧУ","ooiMKv7cqR-2EgkeC9LdATpr-mbY");
	      /*  s.setDateTime("2018-04-22 23:17:28");
	        s.setName("fliay");
	        s.setRemark("��У֪ͨ");
	        s.setSchoolAddress("�Ϻ��ֶ�");
	        s.setTitle("��ȫ�ﵽѧУ");
	        s.setWeChatToken("ooiMKv7cqR-2EgkeC9LdATpr-mbY");
	        */
	/*       List<Student2> list = new ArrayList<Student2>(); 
	       list.add(s);
	        
	       JSONArray json =  JSONArray.fromObject(list);*/
		/* JSONObject json = JSONObject.fromObject(s);
		 
	      
	      	System.out.println(json.toString());*/
		
		teacher s = new teacher();
		s.setDateTime("2018-04-22 23:17:28");
        s.setName("fliay");
        s.setRemark("��У֪ͨ");
        s.setSchoolAddress("�Ϻ��ֶ�");
        s.setTitle("��ȫ�ﵽѧУ");
        s.setWeChatToken("ooiMKv7cqR-2EgkeC9LdATpr-mbY");
		
		JSONObject json = JSONObject.fromObject(s);
		
		System.out.println(json);
		
		
		
		
		
		
	}
	

}
	
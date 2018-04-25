package test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class test2 {
	
	
	public static void main(String[] args) {
		// Student2 s = new Student2("2018-04-22 23:17:28","fliay","到校通知","上海浦东","安全达到学校","ooiMKv7cqR-2EgkeC9LdATpr-mbY");
	      /*  s.setDateTime("2018-04-22 23:17:28");
	        s.setName("fliay");
	        s.setRemark("到校通知");
	        s.setSchoolAddress("上海浦东");
	        s.setTitle("安全达到学校");
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
        s.setRemark("到校通知");
        s.setSchoolAddress("上海浦东");
        s.setTitle("安全达到学校");
        s.setWeChatToken("ooiMKv7cqR-2EgkeC9LdATpr-mbY");
		
		JSONObject json = JSONObject.fromObject(s);
		
		System.out.println(json);
		
		
		
		
		
		
	}
	

}
	

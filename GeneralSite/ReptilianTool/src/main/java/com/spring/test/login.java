package com.spring.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class login {
	/**
	     * 模拟登陆Iteye
	     *
	     * @param userName 用户名
	     * @param pwd 密码
	     *
	     * *
	     */
	    public void tologin(String userName, String pwd) throws Exception {
	
	        //第一次请求
	        Connection con = Jsoup.connect("http://www.fushanedu.cn/jxq/jxq.aspx");//获取连接
	        con.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");//配置模拟浏览器
	        Response rs = (Response) con.execute();//获取响应
	        Document d1 = Jsoup.parse(rs.body());//转换为Dom树
	        List<Element> et = d1.select("#_ctl0");//获取form表单，可以通过查看页面源码代码得知
	        
	        if(et.isEmpty()){
	            System.out.println("获取form表单失败");
	            return;
	        }
	
	        //获取，cooking和表单属性，下面map存放post时的数据
	        Map<String, String> datas = new HashMap<String, String>();
	        for (Element e : et.get(0).getAllElements()) {
	        	
	            if (e.attr("name").equals("login:tbxUserName")) {
	                e.attr("value", userName);//设置用户名
	            }
	
	            if (e.attr("name").equals("login:tbxPassword")) {
	                e.attr("value", pwd); //设置用户密码
	            }
	
	        }
	
	        /**
	         * 第二次请求，post表单数据，以及cookie信息
	         *
	         * *
	         */
	        Connection con2 = Jsoup.connect("https://www.amazon.com/ap/signin?openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&siteState=https%3A%2F%2Fwww.amazon.com%2Fclouddrive%3F_encoding%3DUTF8%26mgh%3D1%26sf%3D1&marketPlaceId=ATVPDKIKX0DER&pageId=photos_authportal_us&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fclouddrive%2Fauth&openid.assoc_handle=amzn_photos_us&openid.oa2.response_type=token&openid.mode=checkid_setup&openid.ns.oa2=http%3A%2F%2Fwww.amazon.com%2Fap%2Fext%2Foauth%2F2&openid.oa2.scope=clouddrive%3Aretailweb&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.oa2.client_id=iba%3Aamzn1.application-oa2-client.d45dc8aaf8fa47b0966a0dfbc75de512&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.pape.max_auth_age=172800");
	        con2.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
	        //设置cookie和post上面的map数据
	        Response login = (Response) con2.ignoreContentType(true).method(Method.POST).data(datas).cookies(rs.cookies()).execute();
	        //打印，登陆成功后的信息
	      //  System.out.println(login.body());
	
	        //登陆成功后的cookie信息，可以保存到本地，以后登陆时，只需一次登陆即可
	        Map<String, String> map = login.cookies();
	        for (String s : map.keySet()) {
	            System.out.println(s + "      " + map.get(s));
	        }
	
	    }
	
	    
	    public static void main(String[] args) {
	    	login l =new login();
	    	try {
				l.tologin("20160101", "197905");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}


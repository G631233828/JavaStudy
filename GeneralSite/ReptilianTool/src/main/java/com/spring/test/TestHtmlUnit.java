package com.spring.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TestHtmlUnit {
	/** 登录页面 */
	private static final String LOGIN_URL = "http://www.fushanedu.cn/jxq/jxq.aspx";
	private static final String URL= "http://www.fushanedu.cn";
	/** 任务列表页面 */
	private static final String TASK_LIST_URL = "http://www.fushanedu.cn/jxq/jxq_User.aspx";

	/***http://www.fushanedu.cn/jxq/jxq_User_jtzyck.aspx
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		testHomePage();
	}

	public static void testHomePage() throws Exception {
 		final WebClient webClient = new WebClient(BrowserVersion.CHROME);


	       webClient.getOptions().setThrowExceptionOnScriptError(false);
	       webClient.getOptions().setCssEnabled(false);
	       webClient.getOptions().setJavaScriptEnabled(false);
	       webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
	       webClient.getOptions().setTimeout(30000);

	       
		// 获取首页
		HtmlPage page = (HtmlPage) webClient.getPage(LOGIN_URL);

		
		HtmlInput in = page.getHtmlElementById("login_tbxUserName");
		HtmlInput pass = page.getHtmlElementById("login_tbxPassword");
	    HtmlInput btn = page.getFirstByXPath(".//*[@id='login_btnlogin']");

		in.setAttribute("value", "20160101");
		pass.setAttribute("value", "197905");
		
		 btn.click();
	
		HtmlPage page3 = webClient.getPage("http://www.fushanedu.cn/jxq/jxq_User_jtzyck.aspx");
		
		String login =page3.getElementById("login_lblmsg").asText();
		
		
		
		Document doc = Jsoup.parse(page3.asXml());
		
		String a = doc.getElementById("SchoolName").text();
		String b = doc.getElementById("GradeName").text();
		String c = doc.getElementById("ClassName").text();
		String d = doc.getElementById("MyMSG").text();
		System.out.println(a+b+c+d);
		
		//获取到作业节点的table
		Element e = doc.select("form table tbody tr:eq(1) table:eq(3) tbody tr td:eq(1) table").first();
		
		Document doc1 = Jsoup.parse(e.toString());
		String  newe = e.toString();
		
		Elements links=doc1.select("a[href]");
		for(Element link:links){
			String oldLink = link.attr("href");
			String newLink = URL+oldLink;
			newe =newe.replace(oldLink, newLink);
		}
		
		
		Document doc2 = Jsoup.parse(newe.toString().replaceAll("<br>", "*/*"));
		
		
		Elements ele = doc2.select("td");
		for(int i =0;i<ele.size();i++){
			if(ele.get(i).text().equals("语文作业")){
				String yuwen =ele.get(i+1).text();
				System.out.println("yuwen"+yuwen);
			}
			if(ele.get(i).text().equals("数学作业")){
				String shuxue =ele.get(i+1).text();
				System.out.println("shuxue"+shuxue);
			}
			
			if(ele.get(i).text().equals("英语作业")){
				String eg =ele.get(i+1).text();
				System.out.println("eg"+eg);
			}
			
			
		}
		
		
		
		
		
//		Element chinese = doc2.select("table tbody tr:eq(3)").last();
//		System.out.println(chinese.text());
//		
//		Element chinesezuoye = doc2.select("table tbody tr:eq(4)").last();
//		System.out.println(chinesezuoye.text());
//		
//		
//		Element english = doc2.select("table tbody tr:eq(6)").last();
//		System.out.println(english.text());
//		Element englishzuoye = doc2.select("table tbody tr:eq(7)").last();
//		System.out.println(englishzuoye.text());
//		
//		
//		
//		Element math = doc2.select("table tbody tr:eq(9)").last();
//		System.out.println(math.text());
//		Element mathzuoye = doc2.select("table tbody tr:eq(10)").last();
//		System.out.println(mathzuoye.text());
//		
		
		
		
		
		
		webClient.close();
		
	
	
		
	}
}
//2018年10月18日 17:24:31
//<tr> 
//<td colspan="2" height="20" bgcolor="#E2E4FA" align="LEFT" valign="MIDDLE"> <b> 语文作业 </b> </td> 
//</tr>
//<tr> 
//<td align="left" valign="top"> 1整理、复习14课的笔记 </td> 
//</tr>
//<tr> 
//<td colspan="2" height="20" bgcolor="#E2E4FA" align="LEFT" valign="MIDDLE"> <b> 英语作业 </b> </td> 
//</tr>
//<tr> 
//<td align="left" valign="top"> <font size="3"> Thursday Oct. 18th <br> <br> </font> 1. <a href="/jxq/UpLoadFolder/OtherFile/hewj/20181018.ppt"> <font color="#0000ff" size="2"> 20181018.ppt </font> </a> 完成在家默本。 <br> 2. 听读熟读 <a href="/jxq/UpLoadFolder/OtherFile/hewj/gettingaroundthecity.rar"> <font color="#0000ff" size="2"> gettingaroundthecity.rar </font> </a> ，完成评价。 <br> <br> 家默本问题：没有按照学校格式要求；没有按照作业要求；家默量不够；没有批改订正。望今后注意。 </td> 
//</tr>
//<tr> 
//<td colspan="2" height="20" bgcolor="#E2E4FA" align="LEFT" valign="MIDDLE"> <b> 数学作业 </b> </td> 
//</tr>
//<tr> 
//<td align="left" valign="top"> 1、完成练习册P44 <br> 2、口算本继续往后2面 </td> 
//</tr>

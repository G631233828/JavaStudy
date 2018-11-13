//package com.spring.test;
//
//import com.gargoylesoftware.htmlunit.BrowserVersion;
//import com.gargoylesoftware.htmlunit.Page;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlForm;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
//import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
//
//public class TestHtmlUnit2 {
//	/** 登录页面 */
//	private static final String LOGIN_URL = "http://www.fushanedu.cn/jxq/jxq.aspx";
//	/** 任务列表页面 */
//	private static final String TASK_LIST_URL = "http://www.fushanedu.cn/jxq/jxq_User.aspx";
//
//	/***http://www.fushanedu.cn/jxq/jxq_User_jtzyck.aspx
//	 * @param args
//	 * @throws Exception
//	 */
//	public static void main(String[] args) throws Exception {
//		testHomePage();
//	}
//
//	public static void testHomePage() throws Exception {
//		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
//
//
//	       webClient.getOptions().setThrowExceptionOnScriptError(false);
//	       webClient.getOptions().setCssEnabled(false);
//	       webClient.getOptions().setJavaScriptEnabled(false);
//	       webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//	       webClient.getOptions().setTimeout(30000);
//
//		// 获取首页
//		HtmlPage page = (HtmlPage) webClient.getPage(LOGIN_URL);
//
//		// 根据form的名字获取页面表单，也可以通过索引来获取：page.getForms().get(0)
//		final HtmlForm form = page.getFormByName("_ctl0");
//
//		// 用户名/密码
//		HtmlTextInput textUserName = form.getInputByName("login:tbxUserName");
//		textUserName.setText("20160101");
//		HtmlPasswordInput txtPwd = form.getInputByName("login:tbxPassword");
//		txtPwd.setText("197905");
//
//		// 调用JS触发登录按钮
//		Page page1 = page.executeJavaScript("$('#login_btnlogin').click()").getNewPage();
//		HtmlPage page2 = (HtmlPage) webClient.getPage(TASK_LIST_URL);
//		System.out.println(page2.getWebResponse().getContentAsString());
//		
//	}
//}

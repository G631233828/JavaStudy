package Future模式;

public class Main {
	
	public static void main(String[] args) {
		
		FutureClient fc = new FutureClient();
		
		
		Data data = fc.request("请求参数");
		
		
		System.out.println("请求发送成功！");
		System.out.println("可以做其他事情。");
		
		System.out.println(data.getRequest());		
	}
	

}

package 出现数据脏读;

public class example1 {
	
	private String accountName ="fliay";
	private String passWord ="123456";
	
	public synchronized void setValue(String accountName,String passWord){
		this.accountName=accountName;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.passWord= passWord;
		System.out.println("您的帐号："+accountName+"您的密码："+passWord);
	}
	
	public /*synchronized*/ void getValue(){
		System.out.println("您的帐号："+accountName+"您的密码："+passWord);
	}
	
	public static void main(String[] args) {
		
		
		 final example1 e = new example1();
		
		 
		 e.getValue();
		 
		 new Thread(new Runnable() {
			
			public void run() {
				String name="jay";
				String pass="111111";
				e.setValue(name, pass);
				
			}
		}).start();
		new Thread(new Runnable() {
			
			public void run() {
				e.getValue();
			}
		}).start();
		
	
	
		
	}
	
	

}

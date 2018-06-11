package 定时器;

import java.util.Timer;
import java.util.TimerTask;

public class 定时炸弹 {
	
	public static void main(String[] args) throws InterruptedException {
		
		int time =10;
		
		for(int i=0;i<time;time--){
			
			System.out.println("倒数计时："+time+"秒");
			Thread.sleep(1000);
			
		}
		new Timer().schedule(new Boom(), time);
	}

}


class Boom extends TimerTask{

	public void run() {
		
		System.out.println("Boom炸了！");
		
		//炸了又炸
		new Timer().schedule(new Boom(), 5000, 1000);
		
	}
	
	
	
	
	
}
package 定时器;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class 定时任务 {

	public static void main(String[] args) {

		String time = "2018-03-21 21:16:00";
		setTimeTask(time);
		
	}

	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	
	public static void setTimeTask(String time){
		
		new Thread(new timeMinus()).start();

		new Timer().schedule(new timerTask(), strToDate(time));
		
		
	}

}

class timerTask extends TimerTask {

	@Override
	public void run() {
		timeMinus.flag = false;
		System.out.println("定时器执行了！");
		return; 
	}

}

class timeMinus implements Runnable {

	public volatile static boolean flag = true;

	public void run() {
		while (flag) {

			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String d = s.format(new Date());

			System.out.println("当前时间" + d);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

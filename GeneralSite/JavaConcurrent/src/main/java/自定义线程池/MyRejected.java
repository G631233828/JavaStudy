package 自定义线程池;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejected implements RejectedExecutionHandler {
	
	
	public MyRejected(){}

	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
	System.out.println("自定义处理》。");
	System.out.println("当前被拒绝的的任务为："+r.toString());
	}

}

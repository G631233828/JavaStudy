package ForkJoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate extends RecursiveTask<Long> {

	
	private long start;
	private long end;
	
	private static final long THRESHOLD =10000; //设置临界值
	
	public ForkJoinCalculate(long start, long middle) {
		this.start = start;
		this.end = middle;
	}

	protected Long compute(){
		long length = start - end;
		if(length <= THRESHOLD){
			long sum =0;
			for(long i =start;i<=end;i++){
				sum+=i;
			}
			return sum;
		}else{
			long middle = (start+end)/2;
			ForkJoinCalculate left = new ForkJoinCalculate(start,middle);
			left.fork();//拆分任务，同时压入线程队列
			
			ForkJoinCalculate right = new ForkJoinCalculate(middle+1,end);
			left.fork();//拆分任务，同时压入线程队列
			return left.join()+right.join();
			
		}
	}

	
	
	


	
	
	

	
	

	
}

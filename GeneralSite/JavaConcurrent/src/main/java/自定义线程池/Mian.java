package 自定义线程池;

public class Mian {

	
	
	
	public static void main(String[] args) {
		
		MyThreadPool my = new MyThreadPool();
		long start = System.currentTimeMillis();
		for(int i =1 ;i<=70;i++){
			Student s = new Student();
			s.setId(i);
			s.setName("fliay"+i);
			s.setAge(i%2==0?25:27);
			my.execute(s);
		}
		my.executor.shutdown();
		
		Thread.yield();
		
		long end = System.currentTimeMillis();
		
		System.out.println("耗时："+(end-start));
		

	}

}

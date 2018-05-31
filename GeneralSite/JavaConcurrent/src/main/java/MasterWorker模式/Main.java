package MasterWorker模式;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		// 实际开发中多少个线程最好写成Runtime.getRuntime().availableProcessors()
		
		Master master = new Master(new Worker(), 10);
		
		Random random = new Random();
		
		//执行100个循环创建task任务放到master的taskQueue任务集合中
		for (int i = 0; i <= 1; i++) {
			Task task = new Task();
			task.setId(i);
			task.setName("任务" + i);
			task.setPrice(random.nextInt(1000));
			master.submit(task);
		}
		
		//执行任务
		master.execute();
		
		
		long start = System.currentTimeMillis();
		while (true) {
			if (master.isComplete()) {
				long end = System.currentTimeMillis() - start;
				int ret = master.getResult();
				System.out.println("计算结果:" + ret + ",执行耗时:" + end);
				break;
			}
		}
	}
}

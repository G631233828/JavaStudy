package MasterWorker模式;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {
	// 任务集合
	private ConcurrentLinkedQueue<Task> taskQueue;
	//处理结果
	private ConcurrentHashMap<String, Object> resultMap;

	public void setTaskQueue(ConcurrentLinkedQueue<Task> taskQueue) {
		this.taskQueue = taskQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public void run() {
		while (true) {
			Task executeTask = this.taskQueue.poll();
			if (executeTask == null)
				break;
			// 真正的任务处理
			Object result = handle(executeTask);
			this.resultMap.put(executeTask.getName(), result);
		}
	}

	// 核心处理逻辑，可以抽离出来由具体子类实现
	private Object handle(Task executeTask) {
		Object result = null;
		try {
			// 表示处理任务的耗时....
			Thread.sleep(0);
			result = executeTask.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}

}

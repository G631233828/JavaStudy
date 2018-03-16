package 多线程队列;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueue优先级排序队列 {
	
	
	/**
	 * 支持优先级排序的队列，需要对象实现Comparable接口
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		final PriorityBlockingQueue pbq = new PriorityBlockingQueue<Student>();
		
		Student s1 = new Student("fliay",15);
		Student s2 = new Student("jay",16);
		Student s3 = new Student("Perter",13);
		pbq.put(s1);
		pbq.put(s2);
		pbq.put(s3);
		
		System.out.println("输出所有："+pbq);
		
		for(int i=0;i<10;i++){
		new Thread(new Runnable() {
			
			public void run() {
				while(pbq.size()>0){
					try {
						System.out.println("有序打印："+pbq.take());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		}
		
		
		
		
	}

}



class Student implements Comparable<Student>{
	
	private String name;
	
	private int age;
	
	 Student(String name,int age){
		this.name = name;
		this.age = age;
	}
	

	public int compareTo(Student o) {
		
		return this.age>o.age? 1:(this.age<o.age?-1:0);
	}


	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
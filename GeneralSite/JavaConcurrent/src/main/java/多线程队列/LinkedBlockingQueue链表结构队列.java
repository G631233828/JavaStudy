package 多线程队列;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueue链表结构队列 {

	/**
	 * 链表有序输出
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException {

		/*
		 * LinkedBlockingQueue lbq = new LinkedBlockingQueue(); lbq.put("A");
		 * lbq.put("D"); lbq.put("B"); lbq.put("E"); lbq.put("C");
		 * 
		 * System.out.println(lbq.size());
		 * 
		 * while(lbq.size()>0){ System.out.println(lbq.take()); }
		 */
		final LinkedBlockingQueue<Students> lbq = new LinkedBlockingQueue<Students>();
		for (int i = 0; i < 1000; i++) {
			Students s = new Students();
			s.setAge(i);
			s.setHeart((int) (Math.random() * 150));
			s.setName(getRandomChar());
			lbq.put(s);
		}

		// 创建线程池用来获取数据

		ExecutorService es = Executors.newFixedThreadPool(2);//消耗时间：17481

		final File f = new File("D:/test.txt");
		if (f.exists()) {
			f.delete();
		}

		if (!f.exists()) {
			f.createNewFile();
		}

		final long start = System.currentTimeMillis();
		for (int i = 0; i <2; i++) {

			Runnable ru = new Thread(new Runnable() {

				public void run() {
					FileWriter fw;
					try {
						while (lbq.size() > 0) {

							final Students student = lbq.take();

							fw = new FileWriter(f, true);
							BufferedWriter bw = new BufferedWriter(fw);
							
							bw.write("[Thread:" + Thread.currentThread().getName() + "],name:" + student.getName()
									+ "heart:" + student.getHeart() + "age:" + student.getAge()+"\r");
							long end = System.currentTimeMillis();
							System.out.println("[Thread:" + Thread.currentThread().getName() +"消耗时间：" + (end - start));
							bw.flush();
							bw.close();
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});

			es.execute(ru);
		}

	}

	public static String getRandomChar() {
		String str = "";
		int highCode;
		int lowCode;

		Random random = new Random();

		highCode = (176 + Math.abs(random.nextInt(39))); // B0 + 0~39(16~55)
															// 一级汉字所占区
		lowCode = (161 + Math.abs(random.nextInt(93))); // A1 + 0~93 每区有94个汉字

		byte[] b = new byte[2];
		b[0] = (Integer.valueOf(highCode)).byteValue();
		b[1] = (Integer.valueOf(lowCode)).byteValue();

		try {
			str = new String(b, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}

class Students {
	String name;
	int heart;
	int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

package 多线程集合;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayList线程安全集合 {

	// list是线程不安全的
	// static List<String> list = new ArrayList<String>(10);
	static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();

	// 想集合中添加数据
	public void toAdd(List<String> list) {

		try {
			Thread.sleep(100);
			list.add("aaa");
			System.out.println("成功添加数据");
		} catch (Exception e) {
			System.out.println("成功数据失败");
			e.printStackTrace();
			return;
		}
	}

	public void get(List<String> list) {
		try {
			if (list.size() > 0) {
				list.remove(0);
				System.out.println("删除数据成功");
			}
		} catch (Exception e) {
			System.out.println("删除数据失败");
			e.printStackTrace();
			return;
		}
	}

	public static void main(String[] args) {

		final CopyOnWriteArrayList线程安全集合 c = new CopyOnWriteArrayList线程安全集合();

		Thread t1 = new Thread(new Runnable() {

			public void run() {
				while (true) {
					c.toAdd(list);
				}

			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {
				while (true) {
					c.get(list);
				}

			}
		});

		t1.start();
		t2.start();

	}

}

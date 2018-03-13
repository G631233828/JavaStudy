package 线程互斥锁;

//主线程每执行10次，子线程执行100次，主线程执行50次
public class 面试题目轮流输出 {

	public static void main(String[] args) {

		final Test t = new Test();

		new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 50; i++) {
					try {
						t.sub(i);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();

		for (int i = 0; i < 100; i++) {

			try {
				t.main(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

class Test {
	private boolean flag = true;

	public synchronized void main(int i) throws InterruptedException {

		while (!flag) {
			this.wait();
		}

		for (int j = 1; j <= 10; j++) {

			System.out.println("主线程" + i + "执行" + j);
		}
		flag = false;
		this.notify();

	}

	public synchronized void sub(int i) throws InterruptedException {

		while (flag) {
			this.wait();
		}

		for (int j = 1; j <= 10; j++) {

			System.out.println("子线程" + i + "执行" + j);
		}
		flag = true;
		this.notify();

	}

}

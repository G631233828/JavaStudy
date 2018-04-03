
public class test {

	public static void main(String[] args) throws InterruptedException {

		int count = 10;

		countVal cv = new countVal(count);

		for (int i = 0; i < 10; i++) {
			new Thread(new countThread(cv)).start();
		}
	}
}

class countVal {

	public int count;

	public countVal(int count) {
		this.count = count;
	}

}

class countThread implements Runnable {

	countVal count;

	public countThread(countVal count) {
		this.count = count;
	}

	public void run() {

		count.count--;
		System.out.println(count.count);
	}

}
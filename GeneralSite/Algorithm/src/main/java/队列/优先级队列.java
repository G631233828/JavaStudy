package 队列;

/**
 * PriorityQueue
 * 
 * @author fliay
 *
 */
public class 优先级队列 {
	private long[] a = null;
	private int nItems = 0;
	private int maxSize = 0;//꧁

	public 优先级队列(int maxSize) {  
	        a = new long[maxSize];  
	        this.maxSize = maxSize;  
	        nItems = 0;  
	    }

	public void insert(long l) {
		// ꧁优先级队列的插入不是队尾,而是选择一个合适的按照某种顺序插入的
		// 当队列长度为0时，如下
		// 不为0时,将所有比要插入的数小的数据后移,这样大的数就在队列的头部了
		int i = 0;
		if (nItems == 0) {
			a[0] = l;
		} else {
			for (i = nItems - 1; i >= 0; i--) {
				if (l < a[i])
					a[i + 1] = a[i];
				else
					break;
			}
			a[i + 1] = l;
		}
		nItems++;
	}

	public long remove() {
		// 移出的是数组最上端的数，这样减少数组元素的移动
		return a[--nItems];
	}

	public boolean isEmpty() {
		return (nItems == 0);
	}

	public boolean isFull() {
		return (nItems == maxSize);
	}

	public int size() {
		return nItems;
	}
}

 class duilie { // 队列体类
	private duilie s;
	private String data;

	duilie(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public duilie getS() {
		return s;
	}

	public void setS(duilie s) {
		this.s = s;
	}
}
 class duiliecz { // 队列操作类
	/**
	 * 
	 * @param args
	 * 
	 */
	private int i = 0; // 队列长
	private duilie top = new duilie(""); // 队列头
	private duilie end = new duilie(""); // 队列尾

	public void add(String s) { // 添加队列  
	        duilie m = new duilie(s);  
	        if (i != 0) {  
	            m.setS(top.getS());  
	            top.setS(m);  
	        } else {  
	            top.setS(m);  
	            end.setS(m);  
	        }  
	        i++;  
	    }
}
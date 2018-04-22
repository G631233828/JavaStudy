package 排序算法;

public class 插入排序 {

	public static Integer[] charu(Integer[] params) {
		System.out.println();
		long start = System.currentTimeMillis();
		int temp;// 定义临时交换数据
		int num = 0;// 循环次数
		int movenum = 0;// 移动次数
		
		for (int i = 0; i < params.length; i++) {
			for (int j = i-1; j >= 0; j--) {
				num++;
				if (params[j + 1] < params[j]) {
					temp = params[j+1];
					params[j+1] = params[j ];
					params[j] = temp;
					movenum++;
				}else {
					break;
				}
			}

		}
		long end = System.currentTimeMillis();

		System.out.println("插入排序完成，循环次数：" + num + ",移动次数:" + movenum + "耗时：" + (end - start) + "毫秒");

		return params;
	}

}

package 排序算法;

public class 选择排序 {
	public static Integer[] xuanzhe(Integer params[]) {
		System.out.println();
		long start = System.currentTimeMillis();
		int num = 0;// 循环次数
		int movenum = 0;// 移动次数
		int temp;// 临时变量用于值互换
		
		for(int i=0;i<params.length;i++) {
			for(int j=i;j<params.length-1;j++) {
				num++;
				if(params[j+1]<params[i]) {
					temp = params[j+1];
					params[j+1]=params[i];
					params[i]=temp;
					movenum++;
				}
			}
		}
		
		
		
		long end = System.currentTimeMillis();

		System.out.println("选择排序完成，循环次数：" + num + ",移动次数:" + movenum + "耗时：" + (end - start) + "毫秒");

		return params;

	}

}

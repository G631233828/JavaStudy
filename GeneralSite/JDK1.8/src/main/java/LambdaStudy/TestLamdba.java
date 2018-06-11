package LambdaStudy;


import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * @author fliay
 * �?、Lamdba 表达式的基础语法：java8中引入了�?个新的操作符�?->�? 称为箭头操作符或Lamdba操作�?
 * 		箭头操作符将lamdba表达式拆分为�?
 *    左侧：Lamdba 表达式的参数列表
 *    右侧：Lamdba 表达式中�?�?要执行的功能，即Lamdba�?
 *    语法格式1：无参数，无返回�?
 *   		  ()->System.out.println("使用Lamdba表达式的方法")
 *    语法格式2：有�?个参数，并且无返回�??
 *    		  (x)-> System.out.println(x);
 *    语法格式3：有两个参数，并且有返回�?
 *    	 Comparator<Integer> com = (x,y)->{
			System.out.println("函数式接�?");
			return Integer.compare(x, y);
		};
			int i = com.compare(23, 24);
			System.out.println("比较两个数字大小"+i);
 * 二�?? Lambda 表达式需要�?�函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称谓函数式接口，可以使用@FunctionalInterface修饰
 *
 */
public class TestLamdba {

	/**
	 * jdk1.8之前的版本与jdk1.8使用Runnable的区�?
	 */
	@Test
	public void test1(){
		Runnable r1 = new Runnable(){
			public void run() {
			System.out.println("jdk1.8以前启动线程的方�?");
			}
		};
		r1.run();
		
		System.out.println("------------版本分割�?-----JDK1.8-------------------");
		//使用Lamdba表达式的方法使用线程
		Runnable r2 = ()->System.out.println("使用Lamdba表达式的方法");
		r2.run();
	}
	/**
	 * 模拟无参数，无返回�?�的Lamdba表达�?
	 */
	@Test
	public void test2(){
		Runnable r = ()-> System.out.println("你好！Lamdba,无参，无返回�?");
		r.run();
	}
	
	
	/**
	 * 有一个参数，无返回�??
	 */
	@Test
	public void test3(){
		Consumer<String> con = (x)-> System.out.println(x);
		con.accept("你好!Lamdba.");
	}
	
	/**
	 * 有两个参数，并且有返回�??
	 */
	@Test
	public void test4(){
		Comparator<Integer> com = (x,y)->{
			System.out.println("函数式接�?");
			return Integer.compare(x, y);
		};
		int i = com.compare(23, 24);
		System.out.println("比较两个数字大小"+i);
		
		System.out.println("---------------------------升级�?-----------------------");
		
		Comparator<Integer> com2 = (x,y)->Integer.compare(x, y);
		int i2 = com2.compare(20, 22);
		System.out.println(i2);
	}
	
	
	/**
	 * 自定义了�?个MyFun进行运算
	 */
	@Test
	public void test5(){
		Integer num = operation(100, (x)->x*x);
		System.out.println(num);
		//其他写法
		System.out.println(operation(200, (x)->x*x));
	}
	
	public Integer operation(Integer num,MyFun mf){
		return mf.getValue(num);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

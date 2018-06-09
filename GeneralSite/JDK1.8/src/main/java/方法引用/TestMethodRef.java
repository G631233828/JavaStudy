package 方法引用;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;



import Lambda表达式学习.Employee;
import org.junit.Test;

/**
 * 
 * @author fliay
 *
 *	1.方法引用：若 Lambda体中的内容有方法以及实现了，我们可以使用“方法引用”
 *  可以理解为方法引用是Lambda表达式的另外一种表现形式
 *	主要有三种语法格式：
 *	
 *	对象::实例方法名
 *
 *	类   ::静态方法名
 *
 *  类   ::实例方法名
 *  
 *  注意：
 *  Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表返回值类型保持一致。
 *  2.构造器引用
 *  格式：
 *  ClassName:new
 *  
 */
public class TestMethodRef {

	//对象：：实例方法名
	@Test
	public void test1(){
		PrintStream ps1 = System.out;
		Consumer<String> con1 = (x)-> ps1.println(x); 
		//另一种方式
		PrintStream ps = System.out;
		Consumer<String> con = ps::println;
		con1.accept("acsdfsdf");
		con.accept("acsdfsdf");
	}
	
	
	@Test
	public void test2(){
		Employee emp = new Employee(1,"fliay",66666.66);
		Supplier<String> sup=()->emp.getName();
		String str = sup.get();
		System.out.println(str);
		//另一种方式
		Supplier<String> sup2 = emp::getName;
		System.out.println(sup.get());
	}

	/**
	 * 类：：静态方法名
	 */
	@Test
	 public void test3(){
		 Comparator<Integer> com =(x,y)->Integer.compare(x, y);
		 int r = com.compare(1, 2);
		 System.out.println(r);
		 
		 Comparator<Integer> com1 = Integer::compare;
		 int r2 = com1.compare(2, 3);
		 System.out.println(r2);
	 }
	
	@Test
	public void test4(){
		BiPredicate<String, String> bp =(x,y)->x.equals(y);
		
		BiPredicate<String, String> bp2=String::equals;
	}
	
	
	//构造器引用
	@Test
	public void test5(){
		Supplier<Employee> emp = Employee::new;
		Employee e = emp.get();
		System.out.println(e);
	}
	
	//带参数构造器引用
	@Test
	public void test6(){
		Function<Integer, Employee> fun = (x)->new Employee(x,"aaa",111.1);
		System.out.println(fun.apply(22));
		
		Function<Double,Employee> fun2 = Employee::new;
		Employee emp = fun2.apply(2222.2);
		System.out.println(emp);
	}
	
	//数组引用
	@Test
	public void test7(){
		Function<Integer,String[]> fun =(x)->new String[x];
		
		String[] strs = fun.apply(10);
		System.out.println(strs.length);
		
		Function<Integer,String[]> fun2 =String[]::new;
		String[] strs2 = fun2.apply(20);
		System.out.println(strs2.length);
		
	}
	
	
	
}

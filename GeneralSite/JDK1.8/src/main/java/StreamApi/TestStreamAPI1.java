package StreamApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

import LambdaStudy.Employee;

/**
 * 
 * @author fliay
 * 
 *         一、Stream 的三个操作步骤 1.创建stream
 * 
 *         2.中间操作
 * 
 *         3.终止操作（终端操作）
 * 
 *
 */

public class TestStreamAPI1 {
	
	List<Employee> employees = Arrays.asList(new Employee(3, "fliay", 2000.00),
											 new Employee(5, "mick", 6000.00),
											 new Employee(1, "linda", 4000.00),
											 new Employee(4, "jack2", 3040.00),
											 new Employee(2, "jay", 3000.00),
											 new Employee(4, "jack", 5000.00));

	@Test
	public void test1() {
		//1.通过Collection 系列集合提供的stream()或parallelStream()
		List<String> list = new ArrayList();
		Stream<String> stream1 =list.stream();
		
		//2.通过Arrays 中的静态方法stream()获取数组流
		Employee[] emps = new Employee[0];
		Stream<Employee> stream2 = Arrays.stream(emps);
		
		//3.通过Stream类的静态方法 of()
		Stream<String> stream3 = Stream.of("aaa","bbb","ccc");
		//stream3.forEach(System.out::println);
		
		//4.创建无限流
		//迭代
		Stream<Integer> stream4 = Stream.iterate(0, (x)->x+2);
//		stream4.limit(5)//只要获取5条记录
//		.forEach(System.out::println);
		
		//生成随机数个5个
		Stream<Double> stream5 = Stream.generate(()->Math.random());
		stream5.limit(5).forEach(System.out::println);
	}
	
	
	//中间操作
	/**
	 * 筛选与切片
	 * filter —— 接受lambda，从流中排除某些元素
	 * limit  —— 截断流，使其元素不超过给定数量。
	 * skip(n)—— 跳过元素，返回一个扔掉前面N个元素的流，偌流中元素不足N个，则返回一个空流，与limit(n)互补
	 * distinct ——筛选，通过流所生成元素的hashcode()和equals()去除重复元素
	 */
	@Test
	public void test2(){
		//中间操作：不会执行任何操作
		Stream<Employee> stream = employees.stream().filter((e)->e.getId()>=3);
		stream.forEach(System.out::print);
		//终止操作：一次性执行全部内容，即“惰性求值”
		Stream<Employee> stream2 = employees.stream().filter((e)->{
			//System.out.println("Stream API 的中间操作"); 
			return e.getId()>1;
		});
		//stream2.limit(2)//输出不超过2条记录
		//stream2.skip(2)//扔掉 元素0，1，2的数据，输出3
		stream2.distinct()//去除重复项
		.forEach(System.out::println);
		
		List<String> list = Arrays.asList("aaa","bbb","ccc","ccc","ddd");
		Stream<String> stream3= list.stream();
		stream3.filter((x)->!x.equals("ddd"))
		.distinct().
		forEach(System.out::println);
		
		System.out.println("-------------------------");
		
		employees.stream().map(Employee::getName).forEach(System.out::println);
		
		System.out.println("-------------------------");
		
		Stream<Stream<Character>> stream4 = list.stream().map(TestStreamAPI1::filterCharacter);
		
		stream4.forEach((x)->{
			x.forEach(System.out::println);
		});
		
		System.out.println("-------------------------");
		Stream<Character> sm = list.stream().flatMap(TestStreamAPI1::filterCharacter);
		sm.forEach(System.out::println);
		
		
	}
	
	
	public static Stream<Character> filterCharacter(String str){
		List<Character> list = new ArrayList<>();
		for(Character c:str.toCharArray()){
			list.add(c);
		}
		return list.stream();
	}
	
	
	
	
	/**
	 * 排序
	 * sorted() —— 自然排序（Comparable）
	 * sorted(Comparator com) —— 定制排序(Comparator) 
	 */
	@Test
	public void test3(){
		List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee","aaa");
		list.stream().sorted().forEach(System.out::println);
//		输出结果
//		aa
//		aaa
//		bbb
//		ccc
//		ddd
//		eee
		System.out.println("-----------------------------");
		employees.stream().sorted((e1,e2)->{
			if(e1.getId().equals(e2.getId())){
				return e1.getName().compareTo(e2.getName());
			}else{
				return e1.getId().compareTo(e2.getId());
			}
		}).forEach(System.out::println);
	}
	
	/**
	 * 查找与匹配
	 * allMatch —— 检查是否匹配所有元素
	 * anyMatch —— 检查是否至少匹配一个元素
	 * noneMatch—— 检查是否没有匹配所有元素
	 * findFirst—— 返回第一个元素
	 * findAny  —— 返回当前流中的任意元素
	 * count    —— 返回流中元素的总个数
	 * max		—— 返回流中最大值
	 * min		—— 返回流中最小值
	 */
	@Test
	public void test4(){
		//allMatch —— 检查是否匹配所有元素
		boolean b1 = employees.stream().allMatch((e)->e.getId()>2);
		System.out.println(b1);
		//anyMatch —— 检查是否至少匹配一个元素
		boolean b2 = employees.stream().anyMatch((e)->e.getId()==2);
		System.out.println(b2);
		//noneMatch—— 检查是否没有匹配所有元素
		boolean b3 = employees.stream().noneMatch((e)->e.getId()==9);
		System.out.println(b3);
		//findFirst—— 返回第一个元素
		Optional<Employee> op = employees.stream().filter((e)->e.getId() ==3).findFirst();
		System.out.println(op.get());
		//先排序
		Optional<Employee> op1 = employees.stream().sorted((e1,e2)->e1.getId().compareTo(e2.getId())).findFirst();
		System.out.println(op1);
		//findAny  —— 返回当前流中的任意元素
		Optional<Employee> op2 = employees.stream().filter((e)->e.getId()>3).findAny();
		System.out.println(op2.get());
		//count    —— 返回流中元素的总个数
		Long count = employees.stream().count();
		System.out.println(count);
		//max		—— 返回流中最大值
		Optional<Employee> op3 = employees.stream().max((e1,e2)->e1.getMoney().compareTo(e2.getMoney()));
		System.out.println(op3.get());
		//min		—— 返回流中最小值
		Optional<Employee> op4 = employees.stream().min((e1,e2)->e1.getMoney().compareTo(e2.getMoney()));
		System.out.println(op4.get());
		//使用map来输出
		Optional<Double> op5 = employees.stream().map(Employee::getMoney).min(Double::compare);
		System.out.println(op5.get());
		
	}
	
	

}

package functionInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 *
 * @author fliay
 * Java 8内置的四大核心函数式接口
 * Consumer<T> :消费型接口
 *         void accept(T t);
 * Supplier<T> :供给型接口
 *          T  get();
 * Function<T,R>:函数型接口
 *         R apply(T t);
 * Predicate<T>:断言型接口
 *         boolean test(T t)        
 */
public class TestLambda {

	@Test
	public void test1(){
		useComsumer("郭建波",(a)->System.out.println("你好，"+a+"!"));
	}
	
	//Consumer
	public void useComsumer(String name,Consumer<String> con){
		con.accept(name);
	}
	
	@Test
	public void test2(){
		List<Integer> list = getNumList(10, ()->100);
		System.out.println(list);
	}
	
	//供给型接口
	public List<Integer> getNumList(int num,Supplier<Integer> sup){
		List<Integer> list = new ArrayList<>();
		for(int i=0;i<num;i++){
			Integer n = sup.get();
			list.add(n);
		}
		return list;
	}
	
	
	/**
	 * 使用Function进行字符串替换
	 */
	@Test
	public void test3(){
		String str = strHandler("f,liay",(x)->x.replace(",", ""));
		System.out.println(str);
		
	}
	
	//用于处理字符串
	public String strHandler(String str,Function<String,String> fun){
		return fun.apply(str);
	}
	
	
	/**
	 * 使用断言的方式判断符合的字符串
	 */
	@Test
	public void test4(){
		
		List<String> listStr = Arrays.asList("fliay","jack","link","shanghai","beijing");
		List<String> list = filterStr(listStr,(x)->x.length()>5);
		System.out.println(list);
		
	}
	
	//将满足条件的字符串放入集合
	public List<String> filterStr(List<String> list,Predicate<String> pre){
		List<String> strList = new ArrayList<>();
		for(String str:list){
			if(pre.test(str)){
				strList.add(str);
			}
		}
		return strList;
	}
	
	
	
	
}

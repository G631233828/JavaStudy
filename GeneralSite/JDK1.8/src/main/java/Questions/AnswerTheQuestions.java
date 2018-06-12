package Questions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import LambdaStudy.Employee;

/**
 * 解决题目
 * @author fliay
 *
 */
public class AnswerTheQuestions {
	
	List<Transaction> transaction = null;
	
	@Before
	public void before(){
		Trader raoul = new Trader("Raoul","Cambridge");
		Trader mario = new Trader("mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("brian","Cambridge");
		Trader crian = new Trader("crian","Cambridge");
		transaction = Arrays.asList(
				new Transaction(raoul, 2011, 3300.00),
				new Transaction(mario, 2012, 2300.00),
				new Transaction(mario, 2011, 3300.00),
				new Transaction(alan, 2013, 1300.00),
				new Transaction(brian, 2014, 3300.00),
				new Transaction(crian, 2015, 4300.00),
				new Transaction(brian, 2016, 5300.00)
				);
				
	}
	
	
	
	
	
	
	/**
	 * 1.给定一个数字列表，如何返回一个由每个数的平方构成的列表
	 * 给定【1,2,3,4,5】 应该返回【1,4,9,16,25】
	 */
	@Test
	public void test1(){
		//先定义一数组
		Integer[] nums = new Integer[]{1,2,3,4,5};
		Arrays.stream(nums).map((x)->x*x).forEach(System.out::print);
	}
	
	
	/**
	 * 2.找出2011年发生的所有交易，并且按照交易额排序
	 */
	@Test
	public void test2(){
		transaction.stream()
		.filter((t)->t.getYear() == 2011)
		.sorted((t1,t2)->Double.compare(t1.getMoney(), t2.getMoney()))
		.forEach(System.out::println);
	}
	
	
	/**
	 * 3.查找所有来自剑桥的交易员，并且按照姓名排序
	 */
	@Test
	public void test3(){
		transaction.stream()
		.filter((t)->t.getTrader().getPlace() == "Cambridge")
		.map(Transaction::getTrader)
		.sorted((t1,t2)->t1.getName().compareTo(t2.getName()))
		.distinct()
		.forEach(System.out::println);
		System.out.println("----------------------------------");
		String str = transaction.stream().map((t)->t.getTrader().getName())
				.sorted().reduce("", String::concat);
		System.out.println(str);
	}
	
	
	/**
	 * 4.交易员都在哪些城市工作过
	 */
	@Test
	public void test4(){
		transaction.stream()
		.map((t)->t.getTrader().getPlace()).distinct().forEach(System.out::println);
	}
	
}

package Questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

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

	}
	
	
	/**
	 * 4.交易员都在哪些城市工作过
	 */
	@Test
	public void test4(){
		transaction.stream()
		.map((t)->t.getTrader().getPlace()).distinct().forEach(System.out::println);
	}
	
	
	
	/**
	 * 返回所有交易员的名字字符串，按照字母顺序排序
	 */
	@Test
	public void test5(){
	
		transaction.stream().map((t)->t.getTrader().getName())
		.sorted().forEach(System.out::print);
		System.out.println("-----------------------------------");
		String str = transaction.stream().map((t)->t.getTrader().getName())
				.sorted().reduce("", String::concat);
		System.out.println(str);
		System.out.println("-----------------------------------");
		transaction.stream().map((t)->t.getTrader().getName())
		.flatMap(AnswerTheQuestions::filterCharacter).sorted((s1,s2)->s1.compareToIgnoreCase(s2))
		.forEach(System.out::print);
	}
	
	public static Stream<String>  filterCharacter(String str){
		List<String> list = new ArrayList<>();
		for(Character ch :str.toCharArray()){
			list.add(ch.toString());
		}
		return list.stream();
	}
	
	/**
	 * 有没有在Milan工作的
	 */
	@Test
	public void test6(){
		boolean b =transaction.stream().anyMatch((e)->e.getTrader().getPlace().equals("Milan"));
		System.out.println(b);
	}
	
	
	
	/**
	 * 打印生活在剑桥的的交易员的所有交易额
	 */
	@Test
	public void test7(){
		transaction.stream().filter((e)->e.getTrader().getPlace().equals("Cambridge")).map(Transaction::getMoney)
		.sorted()
		.forEach(System.out::println);
		//对交易额进行统计
		Optional<Double> sum = transaction.stream().filter((e)->e.getTrader().getPlace().equals("Cambridge"))
				.map(Transaction::getMoney).reduce(Double::sum);
		System.out.println(sum.get());
	}
	
/**
 * 获取最高的交易额	
 */
	@Test
	public void test8(){
		Optional<Double> max =transaction.stream().map((e)->e.getMoney()).max(Double::compare);
		System.out.println(max.get());
		System.out.println("--------------最小交易额-----------------");
		Optional<Double> min = transaction.stream().map((e)->e.getMoney()).min(Double::compare);
		System.out.println(min.get());
	}
	
	
	
	
	
	
	
}

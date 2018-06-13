package ForkJoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestForkJoin {
	Instant start =  null;
	Instant end =  null;
	
	@Before
	public void before(){
		// JAVA8 计算运行时间
		start = Instant.now();
	}

	@Test
	public void test1() {
		
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalculate(0, 10000000L);
		Long sum = pool.invoke(task);
		System.out.println(sum);
		
	}

	/**
	 * java8 并行流
	 *  并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流
	 *  java8中将并行进行了优化，我们可以很容易的对数据进行并行操作，StreamAPI可以声明性地通过
	 *  parrallel()与sEQUENTIAL()在并行流之间进行切换 
	 */
	@Test
	public void test2() {
		long sum = LongStream.rangeClosed(0L, 1000000L).parallel().sum();
		System.out.println(sum);
	}
	
	@After
	public void end(){
		 end = Instant.now();
		System.out.println("耗时：" + Duration.between(start, end).toMillis());
	}
	
	
	
	
	
	
	
	

}

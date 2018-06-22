package Optional;

import java.util.Optional;

import org.junit.Test;

import LambdaStudy.Employee;

public class TestOptional {

	/**
	 * Optional.of(T t) : 创建一个Optional 实例 Optional.empty() : 创建一个空的Optional实例
	 * Optional.ofNullable(T t) : 若t不为null，创建optional实例，否则创建空实例 isPresent() :
	 * 判断是否包含值 orElse(T t) : 如果调用对象包含值，返回该值，否则返回t orElseGet(Supplier s) :
	 * 如果调用对象包含值，返回该值，否则返回s获取的值
	 */
	@Test
	public void test1() {
		// 创建一个Optional 实例
		// Optional<Employee> op = Optional.of(new Employee());
		Optional<Employee> op = Optional.of(null);
		Employee ee = op.get();
		System.out.println(ee);
	}

	@Test
	public void test2() {
		// 创建一个空的Optional实例
		Optional<Employee> op = Optional.empty();
		System.out.println(op.get());
	}

	@Test
	public void test3() {
		// 若t不为null，创建optional实例，否则创建空实例
		Optional<Employee> op = Optional.ofNullable(new Employee());
		// 判断是否包含值
		if (op.isPresent()) {
			System.out.println(op.get());
		}

	}

	@Test
	public void test4() {
		Optional<Employee> op = Optional.ofNullable(null);
		// 如果调用对象包含值，返回该值，否则返回t
		Employee emp = op.orElse(new Employee(1, "fliay", 2222.22));
		System.out.println(emp);
	}

	@Test
	public void test5() {
		Optional<Employee> op = Optional.ofNullable(new Employee(1, null, 3333.33));
		// 如果name出现null的情况下还是会报错
		// Optional<String> str = op.map((e)->e.getName()) ;
		// System.out.println(str.get());

		// 使用Optional对象对获取的数据进行封装
		Optional<String> str2 = op.flatMap((e) -> Optional.of(e.getName()));
		System.out.println(str2);
	}

}

<<<<<<< HEAD:GeneralSite/JDK1.8/src/main/java/LambdaStudy/TestLambda2.java
package LambdaStudy;
=======
package Lambda表达式学习;
>>>>>>> eed80db9da73e11b2cfb1ae50aa707d4755a1f73:GeneralSite/JDK1.8/src/main/java/Lambda表达式学习/TestLambda2.java

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class TestLambda2 {
	
	List<Employee> emps = Arrays.asList(new Employee(1, "jay", 4341.33),
			new Employee(11, "linda", 4442.33),
			new Employee(4, "fliay", 3322.33),
			new Employee(3, "zhangsan", 2111.33),
			new Employee(6, "lisi", 1121.33)
			);
	
	@Test
	public void test1(){
		Collections.sort(emps,(e1,e2)->{
			if(e1.getMoney()==e2.getMoney()){
				return Double.compare(e1.getMoney(), e2.getMoney());
			}else{
				return -Double.compare(e1.getMoney(), e2.getMoney());
			}
		});
		
		for(Employee emp:emps){
			System.out.println(emp);
		}
	
	}
	
	
	
	/**
	 * String字符串转换类
	 */
	@Test
	public void  test2(){
		String re = strHandler("hellosdf",(str)->str.toUpperCase());
		System.out.println(re);
	}
	
	
	//用于处理字符串
	public String strHandler(String str,MyFunction mf){
		return mf.getValue(str);
	}
	
	
	
	@Test
	public void  test3(){
		
		op(100L,200L,(x,y)->x+y);
		
	}
	
	//对于两个Long型数据进行处理
	public void op(Long l1,Long l2,MyFunction2<Long,Long> mf){
		System.out.println(mf.getValue(l1, l2));
	}
	
	
	
	
	
	
	

}

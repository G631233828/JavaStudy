package RepeatAnnotation;

import java.lang.reflect.Method;

import org.junit.Test;

public class TestAnnotation {
	
	@Test
	public void test1() throws NoSuchMethodException, SecurityException{
		Class<TestAnnotation> clazz = TestAnnotation.class;
		Method m1 = clazz.getMethod("show");
		MyAnnotation[] mas = m1.getAnnotationsByType(MyAnnotation.class);
		for(MyAnnotation myAnnotation:mas){
			System.out.println(myAnnotation.value());
		}
	}
	
	@MyAnnotation("Hello")
	public void show(){
		
	}
	

}

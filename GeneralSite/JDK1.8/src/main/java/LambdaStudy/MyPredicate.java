<<<<<<< HEAD:GeneralSite/JDK1.8/src/main/java/LambdaStudy/MyPredicate.java
package LambdaStudy;
=======
package Lambda表达式学习;
>>>>>>> eed80db9da73e11b2cfb1ae50aa707d4755a1f73:GeneralSite/JDK1.8/src/main/java/Lambda表达式学习/MyPredicate.java

/**
 * 函数式接口需要适应@FunctionalInterface
 * @author fliay
 *
 * @param <T>
 */
@FunctionalInterface
public interface MyPredicate<T> {

	public boolean test(T t);
	
}

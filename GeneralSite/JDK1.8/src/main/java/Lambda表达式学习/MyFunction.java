package Lambda表达式学习;

/**
 * 函数式接口需要适应@FunctionalInterface
 * @author fliay
 *
 * @param <T>
 */
@FunctionalInterface
public interface MyFunction {

	public String getValue(String num);
	
}

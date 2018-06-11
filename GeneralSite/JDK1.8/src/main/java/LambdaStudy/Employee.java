<<<<<<< HEAD:GeneralSite/JDK1.8/src/main/java/LambdaStudy/Employee.java
package LambdaStudy;
=======
package Lambda表达式学习;
>>>>>>> eed80db9da73e11b2cfb1ae50aa707d4755a1f73:GeneralSite/JDK1.8/src/main/java/Lambda表达式学习/Employee.java

public class Employee {

	
	public Employee() {
		super();
	}
	private int id;
	private String name;
	private double money;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Employee(int id, String name, double money) {
		this.id = id;
		this.name = name;
		this.money = money;
	}
	public Employee(double money){
		this.money = money;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", money=" + money + "]";
	}
	
	
	
	

	
}

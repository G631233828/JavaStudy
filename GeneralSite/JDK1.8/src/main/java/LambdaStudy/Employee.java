package LambdaStudy;

public class Employee {

	public Employee() {
		super();
	}

	public Integer id;
	public String name;
	public Double money;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", money=" + money + "]";
	}

	public Employee(Integer id, String name, Double money) {
		super();
		this.id = id;
		this.name = name;
		this.money = money;
	}
	public Employee(Double money) {
		this.money = money;
	}

}

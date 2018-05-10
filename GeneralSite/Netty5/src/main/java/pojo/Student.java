package pojo;

import java.io.Serializable;

import org.msgpack.annotation.Message;

//@Message
public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7374810513092521835L;
	private int id;
	private String name;
	private String code;
	private int age;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", code=" + code + ", age=" + age + "]";
	}
	public Student(int id, String name, String code, int age) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.age = age;
	}
	
	
	

}

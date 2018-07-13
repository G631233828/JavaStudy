package com.java.pojo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties:告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 * prefix="person" 配置文件中的哪个属性下面的属性进行映射
 * @author fliay
 *
 */
@ConfigurationProperties(prefix="person")//默认从全局配置文件中获取值
//@PropertySource("classpath:person.properties")//告诉springBoot读取classpath目录下的指定配置文件
@Component
public class Person {

	//@Value("${person.last-name}")
	//@Email//注入值必须是email格式
	private String lastName;
	//@Value("#{10*4}")
	private Integer age;
	
	private Boolean boss;
	private Date birthday;
	private Map<String, Object> maps;
	private List lists;
	private Dog dog;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getBoss() {
		return boss;
	}

	public void setBoss(Boolean boss) {
		this.boss = boss;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Map<String, Object> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}

	public List getLists() {
		return lists;
	}

	public void setLists(List lists) {
		this.lists = lists;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	@Override
	public String toString() {
		return "Person [lastName=" + lastName + ", age=" + age + ", boss=" + boss + ", birthday=" + birthday + ", maps="
				+ maps + ", lists=" + lists + ", dog=" + dog + "]";
	}

}

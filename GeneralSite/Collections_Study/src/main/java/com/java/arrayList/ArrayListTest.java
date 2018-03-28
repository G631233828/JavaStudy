package com.java.arrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArrayListTest {

	
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		
		String a = new String("afdsf");
		String b = "afdsf";
		
		System.out.println("a==b:"+a == b);
		
		System.out.println("a equals b:"+a.equals(b));
		
		boolean flag = Objects.deepEquals(a, b);
		
		System.out.println("deepEquals:"+flag);
		
		System.out.println(Objects.hash(b));
		
		String r = null;
		System.out.println("aa"+Objects.nonNull(r));
		
		
		List list2 = new ArrayList();
		
		list2.addAll(list)
		
		
		
	}
	
}

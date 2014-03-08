package com.joinfun.wj.test;

public class TestCurrentClass {

	
	
	public static void main(String args[]){
		/*Test test = new Test();
		String name = test.getName();
		System.out.println(name);
		String[] fullName = name.split("\\.");
		System.out.println(fullName.length);*/
		
		String a = "aaa\\bbb\\ccc";
		
		System.out.println(a.replaceAll("\\\\","/"));
	}
}

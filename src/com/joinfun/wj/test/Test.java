package com.joinfun.wj.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Test {

	public String getName(){
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		System.out.println(stacks[1].getClassName());
		return stacks[1].getClassName();
	}
	
	 public static void randomList(List list) {
	        Collections.sort(list, new Comparator(){
	            HashMap map = new HashMap();
	            public int compare(Object v1, Object v2) {
	                init(v1);
	                init(v2);
	                
	                double n1 = ((Double)map.get(v1)).doubleValue();
	                double n2 = ((Double)map.get(v2)).doubleValue();
	                if(n1 > n2)
	                    return 1;
	                else if(n1 < n2)
	                    return -1;
	                return 0;
	            }
	            private void init(Object v){
	                if(map.get(v) == null){
	                    map.put(v, new Double(Math.random()));
	                }
	            }
	            protected void finalize() throws Throwable {
	                map = null;
	            }
	        });
	    }
	
	public static void main(String args[]){
		String a = "1";
		String b = "2";
		String c = "3";
		String d = "a,b,c,d,e,";
		List<String> strs = new ArrayList<String>();
		strs.add("1");
		strs.add(a);
		strs.add(b);
		
		List<Integer> nums = new ArrayList<Integer>();
		for(int i = 0 ; i < 1000000; i++){
			nums.add(i);
		}
		
		long startTime=System.currentTimeMillis(); 
		//Collections.shuffle(nums);
		Test.randomList(nums);
		long endTime=System.currentTimeMillis();
		System.err.println("程序运行时间： "+(endTime-startTime)+"ms");
		
		Collections.sort(strs,new Comparator<String>() {
			public int compare(String arg0, String arg1) {   
	               return arg0.compareTo(arg1);   
	            } 
		});
		for(int i = 0 ; i <3 ; i++){
			System.out.println(strs.get(i));
		}
		List<String> strB = new ArrayList<String>();
		System.err.println(d.substring(0, d.length()-1));
		strB.add("b");
		strB.add("c");
		strB.add(a);
		String test = "";
		for(String temp : strB){
			/*if(test.equals("") || test == null){
				test = temp ;
				test += "*&^";
			}else{
				test = test + temp + "*&^";
			}*/
			test = test + temp + "*&^";
		}
		System.err.println(test.substring(0,test.length()-3));
		/*System.out.println(strs.subList(0, 1).size());
		System.out.println(strs.contains("a"));
		System.out.println(strB.containsAll(strs));*/
	}
}

package com.example.UserMicroservice;

import java.util.Arrays;
import java.util.Stack;
interface v{
}
abstract class vv{
	vv(){
		
	}
}

class Solution {
	public static void main(String[] args) {
		boolean is  = isValid("[()]");
		String num="12";
		
		try {
			Integer.parseInt(num);
		}
		catch(Exception e){
			
		}
//		num.chars().mapToObj(x -> (Character)x).allMatch(y -> y.isDigit(y)).

        Stack<Integer> li = new Stack<Integer>();
        li.push(12);
        li.push(1);
        li.push(25);
        li.push(9);
        int min = li.stream().mapToInt(x->x).min().getAsInt();
        System.out.println(min);
		System.out.println(is);
	}
    public static boolean isValid(String s) {
        Stack<Character> li = new Stack<Character>();
        boolean result=true;
        for(Character ch:s.toCharArray()) {
        	if(li.isEmpty()) {
        		li.add(ch);
        	}else {
        		if(compatibilty(li.peek(), ch)) {
        			li.pop();
        		}else {
        			li.push(ch);
        		}
        	}
        }
        return li.size()==0;
    }
    public static boolean compatibilty(Character ch1, Character ch2) {
    	if((ch1=='[' && ch2==']')) {
    		return true;
    	}
    	if((ch1=='(' && ch2==')')) {
    		return true;
    	}
    	if((ch1=='{' && ch2=='}')) {
    		return true;
    	}
    	return false;
    }
}

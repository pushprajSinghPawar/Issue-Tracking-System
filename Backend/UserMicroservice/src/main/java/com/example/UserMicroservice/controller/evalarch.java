package com.example.UserMicroservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class evalarch {
	public static void main(String[] args) {
		//"A","D",null,"F",null,"P",null,"Q")
		List<String> li = Arrays.asList("A","D",null,"F",null,"P",null,"Q");
		System.out.println("Before filter :: "+li);
		
		li = li.stream().filter(e->e!=null).collect(Collectors.toList());
		System.out.println("After Filter :: "+li);
		
		//(1, 2, 3, 4, 4, 5, 5)
		List<Integer> li2 =(Arrays.asList(1, 2, 3, 4, 4, 5, 5));
		System.out.println(li2);
		li2 = li2.stream().distinct().collect(Collectors.toList());
		System.out.println(li2);
		
		
	}
}

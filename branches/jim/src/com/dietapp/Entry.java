package com.dietapp;

public class Entry {
	String name;
	int calories,servings,duration;
	boolean food;
	
	
	public Entry(String n, int c, int s,boolean b){
		name = n;
		calories = c;
		food = b;
		if (food)
			duration = s;
		else if (!food)
			servings = s;
	}
	
}

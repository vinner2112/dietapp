package com.dietapp;

import java.util.ArrayList;

public class Day {
	
	String date;
	ArrayList<Entry> entries;
	
	public Day(){
		entries = new ArrayList<Entry>();
		}
		
	public void addEntry(Entry e){
		entries.add(e);
	}
	
	public Entry getEntry(int i){
		return entries.get(i);
	}
	
	public int getSize(){
		return entries.size();
	}
	
	public String getDate(){
		return date;
	}
	
}

package lista1.q5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTypesComparator {
	
	final static int SIZE = 20000;
	
	public void compare() {
		System.out.println("\n ----- \n");
		System.out.println("CopyOnWriteArrayList GET: " + copyOnWriteArrayListGet());
		System.out.println("synchronizedList GET: " + synchronizedListGet());
		System.out.println("\nCopyOnWriteArrayList PUT: " + copyOnWriteArrayListPut());
		System.out.println("synchronizedList PUT: " + synchronizedListPut());
		System.out.println("\nCopyOnWriteArrayList REMOVE: " + copyOnWriteArrayListRemove());
		System.out.println("synchronizedList REMOVE: " + synchronizedListRemove());
		System.out.println("\nCopyOnWriteArrayList IS_EMPTY: " + copyOnWriteArrayListIsEmpty());
		System.out.println("synchronizedList IS_EMPTY: " + synchronizedListIsEmpty());
		System.out.println("\nCopyOnWriteArrayList SIZE: " + copyOnWriteArrayListSize());
		System.out.println("synchronizedList SIZE: " + synchronizedListSize());
	}
	
	private static long copyOnWriteArrayListPut() 
	{
		List<Integer> myList = new CopyOnWriteArrayList<>();
		
		long startTime = System.currentTimeMillis();
		populateList(myList);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
	private static long synchronizedListPut() 
	{	
		List<Integer> myList = new ArrayList<Integer>();
		List<Integer> synlist = Collections.synchronizedList(myList);
		
		long startTime = System.currentTimeMillis();
		populateList(synlist);
		long stopTime = System.currentTimeMillis();
	    
	    return stopTime-startTime;
	}
	
	private static long copyOnWriteArrayListRemove() 
	{
		List<Integer> myList = new CopyOnWriteArrayList<>();
		populateList(myList);
		
		long startTime = System.currentTimeMillis();
		testRemove(myList);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
	private static long synchronizedListRemove() 
	{	
		List<Integer> myList = new ArrayList<Integer>();
		List<Integer> synlist = Collections.synchronizedList(myList);
		
		populateList(synlist);
	    
		long startTime = System.currentTimeMillis();
		testRemove(synlist);
		long stopTime = System.currentTimeMillis();
	    
		return stopTime-startTime;
	}
	
	private static long copyOnWriteArrayListGet() 
	{
		List<Integer> myList = new CopyOnWriteArrayList<>();
		populateList(myList);
		
		long startTime = System.currentTimeMillis();
		testGet(myList);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
	private static long synchronizedListGet() 
	{	
		List<Integer> myList = new ArrayList<Integer>();
		List<Integer> synlist = Collections.synchronizedList(myList);
		
		populateList(synlist);
		
		long startTime = System.currentTimeMillis();
		testGet(synlist);
		long stopTime = System.currentTimeMillis();
	    
		return stopTime-startTime;
	}
	
	private static long copyOnWriteArrayListIsEmpty() 
	{
		List<Integer> myList = new CopyOnWriteArrayList<>();
		populateList(myList);
		
		long startTime = System.currentTimeMillis();
		testIsEmpty(myList);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
	private static long synchronizedListIsEmpty() 
	{	
		List<Integer> myList = new ArrayList<Integer>();
		List<Integer> synlist = Collections.synchronizedList(myList);
		
		populateList(synlist);
	    
		long startTime = System.currentTimeMillis();
		testIsEmpty(synlist);
		long stopTime = System.currentTimeMillis();
	    
	    return stopTime-startTime;
	}
	
	private static long copyOnWriteArrayListSize() 
	{
		List<Integer> myList = new CopyOnWriteArrayList<>();
		populateList(myList);
		
		long startTime = System.currentTimeMillis();
		testSize(myList);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
	private static long synchronizedListSize() 
	{	
		List<Integer> myList = new ArrayList<Integer>();
		List<Integer> synlist = Collections.synchronizedList(myList);
		
		populateList(synlist);
	    
		long startTime = System.currentTimeMillis();
		testSize(synlist);
		long stopTime = System.currentTimeMillis();
	    
	    return stopTime-startTime;
	}
	
	private static List populateList(List list) 
	{
		for (int i = 0; i <= SIZE; i++) {
			list.add(i);
		}
		return list;
	}
	
	private static void testRemove(List list) 
	{
		for (int i = 0; i < SIZE; i++) {
			list.remove(0);
		}
	}
	
	private static void testIsEmpty(List list) 
	{
		int i = SIZE;
		while (i!=0) {
			list.isEmpty();
			i--;
		}
	}
	
	private static void testSize(List list) 
	{
		int i = SIZE;
		while (i!=0) {
			list.size();
			i--;
		}
	}
	
	private static void testGet(List list) 
	{	
		for(int i = 0; i<=SIZE; i++) {
			list.get(i);
		}
	}
}

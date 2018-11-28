package lista1.questao5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTypesComparator implements Runnable {
	
	static int SIZE;
	
	public ListTypesComparator(int num_of_threads) {
		SIZE = 150000/num_of_threads;
	}
	
	@Override
	public void run() {
//		System.out.println("CopyOnWriteArrayList GET: " + copyOnWriteArrayListGet());
//		System.out.println("synchronizedList GET: " + synchronizedListGet());
		
//		System.out.println("\nCopyOnWriteArrayList PUT: " + copyOnWriteArrayListPut());
//		System.out.println("synchronizedList PUT: " + synchronizedListPut());
		
//		System.out.println("\nCopyOnWriteArrayList REMOVE: " + copyOnWriteArrayListRemove());
//		System.out.println("synchronizedList REMOVE: " + synchronizedListRemove());
	}
	
    /**
     * Gives the time in milliseconds of put operations
     *
     * @return time in milliseconds of put operations
     */
	private static long copyOnWriteArrayListPut() 
	{
		List<Integer> myList = new CopyOnWriteArrayList<>();
		
		long startTime = System.currentTimeMillis();
		populateList(myList);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
    /**
     * Gives the time in milliseconds of put operations
     *
     * @return time in milliseconds of put operations
     */
	private static long synchronizedListPut() 
	{	
		List<Integer> myList = new ArrayList<Integer>();
		List<Integer> synlist = Collections.synchronizedList(myList);
		
		long startTime = System.currentTimeMillis();
		populateList(synlist);
		long stopTime = System.currentTimeMillis();
	    
	    return stopTime-startTime;
	}
	
    /**
     * Gives the time in milliseconds of remove operations
     *
     * @return time in milliseconds of remove operations
     */
	private static long copyOnWriteArrayListRemove() 
	{
		List<Integer> myList = new CopyOnWriteArrayList<>();
		populateList(myList);
		
		long startTime = System.currentTimeMillis();
		testRemove(myList);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
    /**
     * Gives the time in milliseconds of remove operations
     *
     * @return time in milliseconds of remove operations
     */
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
	
    /**
     * Gives the time in milliseconds of get operations
     *
     * @return time in milliseconds of get operations
     */
	private static long copyOnWriteArrayListGet() 
	{
		List<Integer> myList = new CopyOnWriteArrayList<>();
		populateList(myList);
		
		long startTime = System.currentTimeMillis();
		testGet(myList);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
    /**
     * Gives the time in milliseconds of get operations
     *
     * @return time in milliseconds of get operations
     */
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
	
    /**
     * Populates list
     *
     * @param list List to be populated
     */
	private static void populateList(List<Integer> list) 
	{
		for (int i = 0; i <= SIZE; i++) {
			list.add(i);
		}
	}
	
    /**
     * Removes elements from list
     *
     * @param list List that's going to have it's elements removed
     */
	private static void testRemove(List<Integer> list) 
	{
		for (int i = 0; i < SIZE; i++) {
			list.remove(0);
		}
	}
	
    /**
     * Access elements from list
     *
     * @param list List that's going to have it's elements accessed
     */
	private static void testGet(List<Integer> list) 
	{	
		for(int i = 0; i<=SIZE; i++) {
			list.get(i);
		}
	}

	
}

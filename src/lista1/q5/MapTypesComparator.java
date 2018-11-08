package lista1.q5;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapTypesComparator {

	final static int SIZE = 90000;
	
	public void compare() {
		System.out.println("ConcurrentHashMap GET: " + concurrentHashmapGet());
		System.out.println("synchronizedMap GET: " + synchronizedMapGet());
		System.out.println("\nConcurrentHashMap PUT: " + concurrentHashmapPut());
		System.out.println("synchronizedMap PUT: " + synchronizedMapPut());
		System.out.println("\nConcurrentHashMap REMOVE: " + concurrentHashmapRemove());
		System.out.println("synchronizedMap REMOVE: " + synchronizedMapRemove());
		System.out.println("\nConcurrentHashMap IS_EMPTY: " + concurrentHashmapIsEmpty());
		System.out.println("synchronizedMap IS_EMPTY: " + synchronizedMapIsEmpty());
		System.out.println("\nConcurrentHashMap SIZE: " + concurrentHashmapSize());
		System.out.println("synchronizedMap SIZE: " + synchronizedMapSize());
	}
	
	private static long concurrentHashmapPut() 
	{
		Map<String,Integer> myMap = new ConcurrentHashMap<String,Integer>();
		
		long startTime = System.currentTimeMillis();
		populateMap(myMap);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
	private static long synchronizedMapPut() 
	{	
		Map<String,Integer> myMap = new HashMap<String,Integer>();
		Map<String,Integer> synmap = Collections.synchronizedMap(myMap);
		
		long startTime = System.currentTimeMillis();
		populateMap(synmap);
		long stopTime = System.currentTimeMillis();
	    
	    return stopTime-startTime;
	}
	
	private static long concurrentHashmapRemove() 
	{
		Map<String,Integer> myMap = new ConcurrentHashMap<String,Integer>();
		populateMap(myMap);
		
		long startTime = System.currentTimeMillis();
		testRemove(myMap);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
	private static long synchronizedMapRemove() 
	{	
		Map<String,Integer> myMap = new HashMap<String,Integer>();
		Map<String,Integer> synmap = Collections.synchronizedMap(myMap);
		
		populateMap(synmap);
	    
		long startTime = System.currentTimeMillis();
		testRemove(synmap);
		long stopTime = System.currentTimeMillis();
	    
		return stopTime-startTime;
	}
	
	private static long concurrentHashmapGet() 
	{
		Map<String,Integer> myMap = new ConcurrentHashMap<String,Integer>();
		populateMap(myMap);
		
		long startTime = System.currentTimeMillis();
		testGet(myMap);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
	private static long synchronizedMapGet() 
	{	
		Map<String,Integer> myMap = new HashMap<String,Integer>();
		Map<String,Integer> synmap = Collections.synchronizedMap(myMap);
		
		populateMap(synmap);
		
		long startTime = System.currentTimeMillis();
		testGet(synmap);
		long stopTime = System.currentTimeMillis();
	    
		return stopTime-startTime;
	}
	
	private static long concurrentHashmapIsEmpty() 
	{
		Map<String,Integer> myMap = new ConcurrentHashMap<String,Integer>();
		populateMap(myMap);
		
		long startTime = System.currentTimeMillis();
		testIsEmpty(myMap);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
	private static long synchronizedMapIsEmpty() 
	{	
		Map<String,Integer> myMap = new HashMap<String,Integer>();
		Map<String,Integer> synmap = Collections.synchronizedMap(myMap);
		
		populateMap(synmap);
	    
		long startTime = System.currentTimeMillis();
		testIsEmpty(synmap);
		long stopTime = System.currentTimeMillis();
	    
	    return stopTime-startTime;
	}
	
	private static long concurrentHashmapSize() 
	{
		Map<String,Integer> myMap = new ConcurrentHashMap<String,Integer>();
		populateMap(myMap);
		
		long startTime = System.currentTimeMillis();
		testSize(myMap);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
	private static long synchronizedMapSize() 
	{	
		Map<String,Integer> myMap = new HashMap<String,Integer>();
		Map<String,Integer> synmap = Collections.synchronizedMap(myMap);
		
		populateMap(synmap);
	    
		long startTime = System.currentTimeMillis();
		testSize(synmap);
		long stopTime = System.currentTimeMillis();
	    
	    return stopTime-startTime;
	}
	
	private static Map populateMap(Map map) 
	{
		for (int i = 0; i <= SIZE; i++) {
			map.put(String.valueOf(i), i);
		}
		return map;
	}
	
	private static void testRemove(Map map) 
	{
		for (int i = 0; i <= SIZE; i++) {
			map.remove(String.valueOf(i), i);
		}
	}
	
	private static void testIsEmpty(Map map) 
	{
		int i = SIZE;
		while (i!=0) {
			map.isEmpty();
			i--;
		}
	}
	
	private static void testSize(Map map) 
	{
		int i = SIZE;
		while (i!=0) {
			map.size();
			i--;
		}
	}
	
	private static void testGet(Map myMap) 
	{	
		Iterator<String> it = myMap.keySet().iterator();
		
	    while(it.hasNext()){
			String key = it.next();
			myMap.get(key);
		}
	}
}

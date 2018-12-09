package lista1.questao5;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapTypesComparator implements Runnable {

	static int SIZE;
	
	public MapTypesComparator(int num_of_threads) {
		SIZE = 150000/num_of_threads;
	} 
	
	@Override
	public void run() {
		System.out.println("ConcurrentHashMap GET: " + concurrentHashmapGet());
		//System.out.println("synchronizedMap GET: " + synchronizedMapGet());
		
//		System.out.println("\nConcurrentHashMap PUT: " + concurrentHashmapPut());
//		System.out.println("synchronizedMap PUT: " + synchronizedMapPut());
		
//		System.out.println("\nConcurrentHashMap REMOVE: " + concurrentHashmapRemove());
//		System.out.println("synchronizedMap REMOVE: " + synchronizedMapRemove());
	}
	
    /**
     * Gives the time in milliseconds of put operations
     *
     * @return time in milliseconds of put operations
     */
	private static long concurrentHashmapPut() 
	{
		Map<String,Integer> myMap = new ConcurrentHashMap<String,Integer>();
		
		long startTime = System.currentTimeMillis();
		populateMap(myMap);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
    /**
     * Gives the time in milliseconds of put operations
     *
     * @return time in milliseconds of put operations
     */
	private static long synchronizedMapPut() 
	{	
		Map<String,Integer> myMap = new HashMap<String,Integer>();
		Map<String,Integer> synmap = Collections.synchronizedMap(myMap);
		
		long startTime = System.currentTimeMillis();
		populateMap(synmap);
		long stopTime = System.currentTimeMillis();
	    
	    return stopTime-startTime;
	}
	
    /**
     * Gives the time in milliseconds of remove operations
     *
     * @return time in milliseconds of remove operations
     */
	private static long concurrentHashmapRemove() 
	{
		Map<String,Integer> myMap = new ConcurrentHashMap<String,Integer>();
		populateMap(myMap);
		
		long startTime = System.currentTimeMillis();
		testRemove(myMap);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
    /**
     * Gives the time in milliseconds of remove operations
     *
     * @return time in milliseconds of remove operations
     */
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
	
    /**
     * Gives the time in milliseconds of get operations
     *
     * @return time in milliseconds of get operations
     */
	private static long concurrentHashmapGet() 
	{
		Map<String,Integer> myMap = new ConcurrentHashMap<String,Integer>();
		populateMap(myMap);
		
		long startTime = System.currentTimeMillis();
		testGet(myMap);
		long stopTime = System.currentTimeMillis();
		
		return stopTime-startTime;
	}
	
    /**
     * Gives the time in milliseconds of get operations
     *
     * @return time in milliseconds of get operations
     */
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
	
    /**
     * Adds elements to map
     *
     * @param myMap Map that's going to have elements added to it
     */
	private static void populateMap(Map<String, Integer> myMap) 
	{
		for (int i = 0; i <= SIZE; i++) {
			myMap.put(String.valueOf(i), i);
		}
	}
	
    /**
     * Removes elements from map
     *
     * @param myMap Map that's going to have it's elements removed
     */
	private static void testRemove(Map<String, Integer> myMap) 
	{
		for (int i = 0; i <= SIZE; i++) {
			myMap.remove(String.valueOf(i), i);
		}
	}
	
    /**
     * Access elements from map
     *
     * @param myMap Map that's going to have it's elements accessed
     */
	private static void testGet(Map<String, Integer> myMap) 
	{	
		Iterator<String> it = myMap.keySet().iterator();
		
	    while(it.hasNext()){
			String key = it.next();
			myMap.get(key);
		}
	}
}

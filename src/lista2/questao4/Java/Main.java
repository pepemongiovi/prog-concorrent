package lista2.questao4.Java;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import lista1.questao5.ListTypesComparator;

public class Main {
	
	final static int NUM_OF_THREADS = 2000;

    public static void main(String args[]) throws ExecutionException, InterruptedException 
    {
    	long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
    	
    	ArrayList<Thread> threads = new ArrayList<Thread>();
    	
        for (int i = 0; i< NUM_OF_THREADS; i++) 
		{
        	HttpRequest httpRequest = new HttpRequest();
			Thread thread = new Thread(httpRequest ,"thread" + i);
			thread.start();
			threads.add(thread);
		}
        
        for (int i = 0; i< NUM_OF_THREADS; i++) 
		{
        	threads.get(i).join();
		}

        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        
        long actualMemUsed=afterUsedMem-beforeUsedMem;

        System.out.println("\nMemory used: " + actualMemUsed);
    }
}
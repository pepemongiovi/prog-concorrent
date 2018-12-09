package lista1.questao5;

public class Main {
	
	final static int NUM_OF_THREADS = 5;
	
	public static void main(String args[]) throws InterruptedException 
	{	
//		for (int i = 0; i< NUM_OF_THREADS; i++) 
//		{
//			MapTypesComparator mapComparator = new MapTypesComparator(NUM_OF_THREADS);
//			Thread thread = new Thread(mapComparator ,"thread" + i);
//			thread.start();
//		}
		
		for (int i = 0; i< NUM_OF_THREADS; i++) 
		{
			ListTypesComparator listComparator = new ListTypesComparator(NUM_OF_THREADS);
			Thread thread = new Thread(listComparator ,"thread" + i);
			thread.start();
		}
    }
}

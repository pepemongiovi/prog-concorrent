package lista1.questao5;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
	
	
	
	public static void main(String args[]) throws InterruptedException {
		MapTypesComparator mapComparator = new MapTypesComparator();
		ListTypesComparator listComparator = new ListTypesComparator();
		
		mapComparator.compare();
		listComparator.compare();
    }
}

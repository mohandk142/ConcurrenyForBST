package commonFiles;
import java.util.ArrayList;

import concurrentBST.*;
import nonConcurrentBST.*;

public class Test {

	public static void main(String[] args) {
		final int maxRange = 5000;
		final int minRange = 1;
		NonConcurrentBST<Integer> tree = new NonConcurrentBST<Integer>();

		// balanced
//		tree.insert(10);
//		tree.insert(5);
//		tree.insert(15);
//		tree.insert(3);
//		tree.insert(7);
//		tree.insert(1);
//		tree.insert(4);
//		tree.insert(6);
//		tree.insert(9);
//		tree.insert(12);
//		tree.insert(17);
//		tree.insert(11);
//		tree.insert(13);
//		tree.insert(16);
//		tree.insert(18);
		
		
/**************************************** Operations test ***************************************************/
				
		int NUM_THREADS = 10;
		int totalNumber = maxRange-minRange;
		int [] randomArr = new int[maxRange-minRange];
		for(int i=0;i<maxRange-minRange;i++){
			randomArr[i] = 1+(int)(Math.random()*maxRange);
		}
		
		NonConcurrentBST<Integer> ncbst = new NonConcurrentBST<>();
		long start = System.currentTimeMillis();
		for(int i:randomArr){
			ncbst.insert(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("NCBST insert time: "+(end-start));
		
		ReadWriteBST<Integer> rwTree = new ReadWriteBST<>();
		Thread[] insertThreadsForRWBST = new Thread[NUM_THREADS];
		
		for(int i=0;i<NUM_THREADS;i++){
			insertThreadsForRWBST[i] = new Thread(new runInsertInThreads(randomArr, rwTree, null, null, totalNumber/NUM_THREADS, i));
		}
		
		start = System.currentTimeMillis();
		for(int i=0;i<NUM_THREADS;i++){
			insertThreadsForRWBST[i].start();
		}
		for(int i=0;i<NUM_THREADS;i++){
			try {
				insertThreadsForRWBST[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		end = System.currentTimeMillis();
		System.out.println("RWBST insert time: "+(end-start));
		
		HandOverHandLockingBST<Integer> HOHTree = new HandOverHandLockingBST<>();
		Thread[] insertThreadsForHOHBST = new Thread[NUM_THREADS];
		
		for(int i=0;i<NUM_THREADS;i++){
			insertThreadsForHOHBST[i] = new Thread(new runInsertInThreads(randomArr, null, HOHTree, null, totalNumber/NUM_THREADS, i));
		}
		start = System.currentTimeMillis();
		for(int i=0;i<NUM_THREADS;i++){
			insertThreadsForHOHBST[i].start();
		}
		for(int i=0;i<NUM_THREADS;i++){
			try {
				insertThreadsForHOHBST[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		end = System.currentTimeMillis();
		System.out.println("HOH insert time: "+(end-start));
		

		CopyOnWriteBST<Integer> COWTree = new CopyOnWriteBST<>();
		Thread[] insertThreadsForCOW = new Thread[NUM_THREADS];
		
		for(int i=0;i<NUM_THREADS;i++){
			insertThreadsForCOW[i] = new Thread(new runInsertInThreads(randomArr, null, null, COWTree, totalNumber/NUM_THREADS, i));
		}
		start = System.currentTimeMillis();
		for(int i=0;i<NUM_THREADS;i++){
			insertThreadsForCOW[i].start();
		}
		for(int i=0;i<NUM_THREADS;i++){
			try {
				insertThreadsForCOW[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		end = System.currentTimeMillis();
		System.out.println("COW insert time: "+(end-start));
		
		
		
		
/**************************************** DFT Performance Test **********************************************/
		// random
		int [] randomArray = new int[maxRange-minRange];
		for(int i=0;i<maxRange-minRange;i++){
			randomArray[i] = 1+(int)(Math.random()*maxRange);
			tree.insert(randomArray[i]);
		}
		
		TestNonConcurrentBST(tree);
		TestConcurrentDFBST(tree);
		
	}

	private static void TestConcurrentDFBST(NonConcurrentBST<Integer> tree) {
		ParallelDepthFirstSearch con= new ParallelDepthFirstSearch();
		
		//balanced
//		tree.insert(10);
//		tree.insert(5);
//		tree.insert(15);
//		tree.insert(3);
//		tree.insert(7);
//		tree.insert(1);
//		tree.insert(4);
//		tree.insert(6);
//		tree.insert(9);
//		tree.insert(12);
//		tree.insert(17);
//		tree.insert(11);
//		tree.insert(13);
//		tree.insert(16);
//		tree.insert(18);
		
		//blabla tree
//		tree.insert(10);
//		tree.insert(5);
//		tree.insert(15);
//		tree.insert(12);
//		tree.insert(13);
//		tree.insert(11);
//		tree.insert(14);
//		tree.insert(17);
			
		
		//right skew
		/*for(int i=10;i>0;i--){
			tree.insert(i);
		}*/
		//left skew
//		for(int i=0;i<10;i++){
//			tree.insert(i);
//		}
		
		/*for(int i=0;i<maxRange-minRange;i++){
			int ran = 1+(int)(Math.random()*maxRange);
			tree.insert(ran);
		}*/
		long endTime = 0;
		long startTime = System.currentTimeMillis();
		con.ConcurrentTraverse(tree.getRoot());
		endTime = System.currentTimeMillis();
		System.out.println("Concurrent traverse time: "+(endTime-startTime));
		
	}

	private static void TestNonConcurrentBST(NonConcurrentBST<Integer> tree) {
		long endTime = 0;
		long startTime = System.currentTimeMillis();
		ParallelDepthFirstSearch.sequentialTraverse(tree.getRoot(), new ArrayList<Integer>(1));
		endTime = System.currentTimeMillis();
		System.out.println("Non-Concurrent traverse time: "+(endTime-startTime));
	}

}
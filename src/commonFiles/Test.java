package commonFiles;
import java.util.ArrayList;

import concurrentBST.*;
import nonConcurrentBST.*;

public class Test {

	public static void main(String[] args) {
		final int maxRange = 50000;
		final int minRange = 1;
		NCBST<Integer> tree = new NCBST<Integer>();

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
		
		// random
		int [] randomArray = new int[maxRange-minRange];
		for(int i=0;i<maxRange-minRange;i++){
			randomArray[i] = 1+(int)(Math.random()*maxRange);
			tree.insert(randomArray[i]);
		}
		
		TestNonConcurrentBST(tree);
		TestConcurrentDFBST(tree);
		
	}

	private static void TestConcurrentDFBST(NCBST<Integer> tree) {
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

	private static void TestNonConcurrentBST(NCBST<Integer> tree) {
		long endTime = 0;
		long startTime = System.currentTimeMillis();
//		tree.traversal(tree.getRoot());
		ParallelDepthFirstSearch.sequentialTraverse(tree.getRoot(), new ArrayList<Integer>(1));
		endTime = System.currentTimeMillis();
		System.out.println("Non-Concurrent traverse time: "+(endTime-startTime));
	}

}
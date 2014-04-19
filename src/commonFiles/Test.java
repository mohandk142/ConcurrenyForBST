package commonFiles;
import concurrentBST.*;
import nonConcurrentBST.*;

public class Test {

	public static void main(String[] args) {

//		TestNonConcurrentBST();
		TestConcurrentDFBST();
		
	}

	private static void TestConcurrentDFBST() {
		ParallelDepthFirstSearch con= new ParallelDepthFirstSearch();
		final int maxRange = 10;
		final int minRange = 1;
		BSTNode<Integer> one = new BSTNode<Integer>(minRange, null, null);
		NCBST<Integer> tree = new NCBST<Integer>();
		
		//balanced
	tree.insert(10);
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
		tree.insert(10);
		tree.insert(5);
		tree.insert(15);
		tree.insert(12);
		tree.insert(13);
		tree.insert(11);
		tree.insert(14);
		tree.insert(17);
			
		
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
		
		con.ConcurrentTraverse(tree.getRoot());
	}

	private static void TestNonConcurrentBST() {
		NCBST<Integer> nonConcurrentBST = new NCBST<Integer>();
		for(int i=10;i>=1;i--){
			nonConcurrentBST.insert(i);
		}
		nonConcurrentBST.traversal(nonConcurrentBST.getRoot());		
	}

}
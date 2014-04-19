import commonFiles.BSTNode;

import concurrentBST.ParallelDepthFirstSearch;
import nonConcurrentBST.NCBST;


public class TestDriver {

	public static void main(String[] args) {

//		TestNonConcurrentBST();
		TestConcurrentDFBST();
		
	}

	private static void TestConcurrentDFBST() {
		ParallelDepthFirstSearch con= new ParallelDepthFirstSearch();
		final int maxRange = 50;
		final int minRange = 1;
		BSTNode<Integer> one = new BSTNode<Integer>(minRange, null, null);
		NCBST<Integer> tree = new NCBST<Integer>();
		
		for(int i=0;i<maxRange-minRange;i++){
			int ran = 1+(int)(Math.random()*maxRange);
			tree.insert(ran);
		}
		
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

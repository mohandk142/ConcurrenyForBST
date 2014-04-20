package concurrentBST;

import java.util.ArrayList;

import commonFiles.*;

public class SeqTraverse implements Runnable{
	
	BSTNode<Object> root;
	ArrayList<Integer> traverseArr;

	
	public SeqTraverse(BSTNode root, ArrayList traverseArr){
		this.root = root;
		this.traverseArr = traverseArr;
	}

	@Override
	public void run() {
		ParallelDepthFirstSearch.sequentialTraverse(root, traverseArr);
	}

}

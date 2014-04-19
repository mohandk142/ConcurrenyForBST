package concurrentBST;

import java.util.ArrayList;

import commonFiles.*;

public class SeqTraverse implements Runnable{
	
	BSTNode<Integer> root;
	ArrayList<Integer> traverseArr;

	
	public SeqTraverse(BSTNode<Integer> root, ArrayList<Integer> traverseArr){
		this.root = root;
		this.traverseArr = traverseArr;
	}

	@Override
	public void run() {
		ParallelDepthFirstSearch.sequentialTraverse(root, traverseArr);
	}

}

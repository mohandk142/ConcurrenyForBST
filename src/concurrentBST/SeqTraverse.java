package concurrentBST;

import java.util.ArrayList;

import commonFiles.*;

public class SeqTraverse implements Runnable{
	
	BSTNode<Object> root;
	ArrayList<Object> traverseArr;

	
	public SeqTraverse(BSTNode<Object> root, ArrayList<Object> traverseArr){
		this.root = root;
		this.traverseArr = traverseArr;
	}

	@Override
	public void run() {
		ParallelDepthFirstSearch.sequentialTraverse(root, traverseArr);
	}

}

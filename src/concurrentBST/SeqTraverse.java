package concurrentBST;

import java.util.ArrayList;

import commonFiles.*;

public class SeqTraverse implements Runnable{
	
<<<<<<< HEAD
	BSTNode<Object> root;
	ArrayList<Object> traverseArr;

	
	public SeqTraverse(BSTNode<Object> root, ArrayList<Object> traverseArr){
=======
	BSTNode<Integer> root;
	ArrayList<Integer> traverseArr;

	
	public SeqTraverse(BSTNode<Integer> root, ArrayList<Integer> traverseArr){
>>>>>>> f63c381ba175c97ac9fbd9080dca8edd8c1e20e4
		this.root = root;
		this.traverseArr = traverseArr;
	}

	@Override
	public void run() {
<<<<<<< HEAD
		ParallelDepthFirstSearch.sequentialTraverse(root, traverseArr);
=======
		ConcurrentDepthFirstBST.sequentialTraverse(root, traverseArr);
>>>>>>> f63c381ba175c97ac9fbd9080dca8edd8c1e20e4
	}

}

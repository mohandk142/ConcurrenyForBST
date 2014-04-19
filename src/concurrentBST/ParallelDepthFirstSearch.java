package concurrentBST;

import static java.lang.System.err;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import commonFiles.*;

public class ParallelDepthFirstSearch {

	Queue<QNode> parentQueue = new LinkedList<QNode>();
	Queue<QNode> childQueue = new LinkedList<QNode>();

	int numProcs = Runtime.getRuntime().availableProcessors(); 

	public static void sequentialTraverse(BSTNode<Integer> root, ArrayList<Integer> traverseArr){

		if(null == root){
			return;
		}	
		traverseArr.add(root.getKey());
		sequentialTraverse(root.getLeft(), traverseArr);
		sequentialTraverse(root.getRight(), traverseArr);
	}

	public void prepareQueue(QNode n){

		QNode leftChild;
		QNode rightChild;

		if(n.getNode().getLeft() != null && n.getNode().getRight() != null){
			if(n.getProcs() > 1){
				childQueue.remove();
				numProcs = numProcs/2;
				parentQueue.add(n);

				leftChild = new QNode(n.getNode().getLeft(), numProcs);
				rightChild = new QNode(n.getNode().getRight(), numProcs);

				childQueue.add(leftChild);
				childQueue.add(rightChild);	

				prepareQueue(childQueue.element());
			}
		}
		else if(n.getNode().getLeft() == null && n.getNode().getRight() != null){
			if(n.getProcs() > 1){
				childQueue.remove();
				parentQueue.add(n);

				rightChild = new QNode(n.getNode().getRight(), numProcs);

				childQueue.add(rightChild);
				//System.out.println("childq "+childQueue.element().getNode().getKey());

				prepareQueue(childQueue.element());

			}
		}
		else if(n.getNode().getRight() == null && n.getNode().getLeft() != null){
			if(n.getProcs() > 1){
				childQueue.remove();
				parentQueue.add(n);

				leftChild = new QNode(n.getNode().getLeft(), numProcs);

				childQueue.add(leftChild);

				prepareQueue(childQueue.element());
			}
		}
		else{
			childQueue.remove();
			parentQueue.add(n);
			/*if(childQueue.element().getProcs() >1){
				childQueue.remove();
			}
			childQueue.add(new QNode(n.getNode(), 1));*/
		}
	}
	
	public void printDFT(ArrayList<ArrayList<Integer>> arr){
		
		int i = 0;
		
		while(!parentQueue.isEmpty()){
			if(parentQueue.element().getProcs() >2){
				System.out.print(parentQueue.element().getNode().getKey() + "\t");
				parentQueue.remove();				
			}
			else {
				System.out.print(parentQueue.element().getNode().getKey() + "\t");
				if(parentQueue.element().getNode().getLeft() != null && parentQueue.element().getNode().getRight() != null){
					for(int j = 0; j <arr.get(i).size(); j++){					
						System.out.print(arr.get(i).get(j) + "\t");
					}
					i++;
				}
				parentQueue.remove();
				for(int j = 0; j <arr.get(i).size(); j++){					
					System.out.print(arr.get(i).get(j) + "\t");
				}
				i++;
			}
			
		}
		
	}
	
	public void ConcurrentTraverse(BSTNode<Integer> root){
		if(numProcs < 2){
			ArrayList<Integer> traverseArr = new ArrayList<Integer>(1);
			sequentialTraverse(root, traverseArr);
			for(int j = 0; j<traverseArr.size(); j++){
				System.out.print(traverseArr.get(j) + "\t");
			}
		}

		else{

			QNode p = new QNode(root, numProcs);
			childQueue.add(p);				
			QNode n = childQueue.element();

			prepareQueue(n);	

			ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
			ArrayList<Thread> DFTThreads = new ArrayList<Thread>();
			int i = 0;

			while(!childQueue.isEmpty()){
				ArrayList<Integer> traverseArr = new ArrayList<Integer>(1);
				arr.add(i, traverseArr);
				DFTThreads.add(new Thread(new SeqTraverse(childQueue.element().getNode(), arr.get(i))));
				DFTThreads.get(i).start();
				childQueue.remove();
				i++;				
			}

			for (int j = 0; j < DFTThreads.size(); j++){
				try {
					DFTThreads.get(j).join();
				} catch (InterruptedException e) {
					err.print("error in thread join\n");
					e.printStackTrace();
				}

			}			
			printDFT(arr);
		}	
	}
}
package concurrentBST;

import java.util.ArrayList;

public class runInsertInThreads implements Runnable{
	
	ArrayList<Integer> arr = new ArrayList<Integer>();
	ReadWriteBST<Integer> rwTree;
	HandOverHandLockingBST<Integer> HOHTree;
	CopyOnWriteBST<Integer> COWTree;

	
	public runInsertInThreads(ArrayList arr, CopyOnWriteBST COWTree, HandOverHandLockingBST HOHTree, ReadWriteBST rwTree) {
		this.arr = arr;
		this.COWTree = COWTree;
		this.HOHTree = HOHTree;
		this.rwTree = rwTree;
	}	
	
	public void run(){
		
		for(int i = 0; i<arr.size(); i++){
			
			if(rwTree != null)
				rwTree.insert(arr.get(i));
			if(COWTree != null)
				COWTree.insert(arr.get(i));
			if(HOHTree != null)
				HOHTree.insert(arr.get(i));
		}
	}

}

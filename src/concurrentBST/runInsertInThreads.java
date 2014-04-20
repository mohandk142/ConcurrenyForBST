package concurrentBST;

public class runInsertInThreads implements Runnable{
	
	int[] randomArr;
	ReadWriteBST<Integer> rwTree;
	HandOverHandLockingBST<Integer> HOHTree;
	CopyOnWriteBST<Integer> COWTree;
	int end, start;
	
	public runInsertInThreads(int[] randomArr, ReadWriteBST<Integer>rwTree, HandOverHandLockingBST<Integer> HOHTree, CopyOnWriteBST<Integer> COWTree, int end, int i) {
		this.end = end;
		this.start = i;
		this.randomArr = randomArr;
		this.rwTree = rwTree;
		this.COWTree = COWTree;
		this.HOHTree = HOHTree;
	}	
	
	public void run(){
		for(int j=0;j<end;j++){
			
			if(rwTree != null)
				rwTree.insert(randomArr[(start*end)+j]);
			if(COWTree != null)
				COWTree.insert(randomArr[(start*end)+j]);
			if(HOHTree != null)
				HOHTree.insert(randomArr[(start*end)+j]);
		}
		
	}

}

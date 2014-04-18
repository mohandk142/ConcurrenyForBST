package concurrentBST;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import commonFiles.BSTNode;

public class RWBST<K extends Comparable<K>> {
	
	private BSTNode<K> root;
	
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock read  = readWriteLock.readLock();
	private final Lock write = readWriteLock.writeLock();
	
	public RWBST(){
		root = null;
	}
	
	public void insert(K key){
		write.lock();
		try{
			root = insert(root, key);
		} catch(Exception e){
			//todo -- introduce reqd exception
		} finally{
			write.unlock();
		}		
	}
	
	private BSTNode<K> insert(BSTNode<K> n, K key) {
	    if (n == null) {
	        return new BSTNode<K>(key, null, null);
	    }
	     
	    if (n.getKey().equals(key)) {
	        System.out.println("Duplicate key");
	        return null;
	    }
	    
	    if (key.compareTo(n.getKey()) < 0) {
	        n.setLeft( insert(n.getLeft(), key) );
	        return n;
	    }
	    
	    else {
	        n.setRight( insert(n.getRight(), key) );
	        return n;
	    }
	}
	
	public void delete(K key){
		write.lock();
		try{
			root = delete(root, key);
		} catch(Exception e){
			//todo -- introduce reqd exception
		} finally{
			write.unlock();
		}			
	}
	
	private BSTNode<K> delete(BSTNode<K> n, K key) {
	    if (n == null) {
	        return null;
	    }
	    
	    if (key.equals(n.getKey())) {

	        if (n.getLeft() == null && n.getRight() == null) {
	            return null;
	        }
	        if (n.getLeft() == null) {
	            return n.getRight();
	        }
	        if (n.getRight() == null) {
	            return n.getLeft();
	        }
	       
	        K smallVal = smallest(n.getRight());
	        n.setKey(smallVal);
	        n.setRight( delete(n.getRight(), smallVal) );
	        return n; 
	    }
	    
	    else if (key.compareTo(n.getKey()) < 0) {
	        n.setLeft( delete(n.getLeft(), key) );
	        return n;
	    }
	    
	    else {
	        n.setRight( delete(n.getRight(), key) );
	        return n;
	    }
	}
	
	public boolean lookup(K key){
		
		boolean var = false;
		read.lock();
		try{
			var = lookup(root, key);
		} catch(Exception e){
			//todo -- introduce reqd exception
		} finally{
			write.unlock();
		}	
		return var;
	}
	
	private boolean lookup(BSTNode<K> n, K key){
		if(n == null){
			return false;
		}
		
		if(n.getKey().equals(key)){
			return true;
		}
		
		if(key.compareTo(n.getKey()) < 0){
			return lookup(n.getLeft(), key);
		}
		
		else{
			return lookup(n.getRight(), key);
		}
	}
	
	public int[] search(K key){
		//same as lookup function. But returns the parent/position/adjacent nodes (whatever we may decide)
		//use read lock
		return null;
	}
	
	public int[] traversal(){
		//use read lock
		return null;
		
	}
	
	private K smallest(BSTNode<K> n){
		
	    if (n.getLeft() == null) {
	        return n.getKey();
	    } else {
	        return smallest(n.getLeft());
	    }
	}
}

package nonConcurrentBST;

import commonFiles.BSTNode;

public class NCBST<K extends Comparable<K>> {
	
	private BSTNode<K> root;
	
	public NCBST(){
		root = null;
	}
	
	public BSTNode<K> getRoot() {
		return root;
	}

	public void setRoot(BSTNode<K> root) {
		this.root = root;
	}

	public void insert(K key){
		root = insert(root, key);
	}
	
	private BSTNode<K> insert(BSTNode<K> n, K key) {
	    if (n == null) {
	        return new BSTNode<K>(key, null, null);
	    }
	     
//	    if (n.getKey().equals(key)) {
//	        System.out.println("Duplicate key");
//	        return null;
//	    }
	    
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
		root = delete(root, key);
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
		return lookup(root, key);		
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
		return null;
		
	}
	
	public void traversal(BSTNode<K> root){

		if(null == root){
			return;
		}	
		traversal(root.getLeft());
		System.out.println(root.getKey());
		traversal(root.getRight());
	}
	
	private K smallest(BSTNode<K> n){
		
	    if (n.getLeft() == null) {
	        return n.getKey();
	    } else {
	        return smallest(n.getLeft());
	    }
	}

}

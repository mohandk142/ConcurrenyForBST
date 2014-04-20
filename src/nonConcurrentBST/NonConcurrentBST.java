package nonConcurrentBST;

import commonFiles.BSTNode;

public class NonConcurrentBST<E extends Comparable<E>> {
	
	private BSTNode<E> root;
	
	public NonConcurrentBST(){
		root = null;
	}
	
	public BSTNode<E> getRoot() {
		return root;
	}

	public void setRoot(BSTNode<E> root) {
		this.root = root;
	}

	public void insert(E key){
		root = insert(root, key);
	}
	
	private BSTNode<E> insert(BSTNode<E> n, E key) {
	    if (n == null) {
	        return new BSTNode<E>(key, null, null);
	    }
	     
	    if (n.getKey().equals(key)) {
	    	n.setLeft( insert(n.getLeft(), key) );
	        return n;
	        //System.out.println("Duplicate key");
	        //return null;
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
	
	public void delete(E key){
		root = delete(root, key);
	}
	
	private BSTNode<E> delete(BSTNode<E> n, E key) {
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
	       
	        E smallVal = smallest(n.getRight());
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
	
	public boolean lookup(E key){
		return lookup(root, key);		
	}
	
	private boolean lookup(BSTNode<E> n, E key){
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
	
	public void traversal(BSTNode<E> root){

		if(null == root){
			return;
		}	
		traversal(root.getLeft());
		System.out.println(root.getKey());
		traversal(root.getRight());
	}
	
	private E smallest(BSTNode<E> n){
		
	    if (n.getLeft() == null) {
	        return n.getKey();
	    } else {
	        return smallest(n.getLeft());
	    }
	}

}
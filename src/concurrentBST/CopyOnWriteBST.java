package concurrentBST;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import commonFiles.BSTNode;

public class CopyOnWriteBST<E>{
	
	
	
	private final BSTNode<E> bst;
	
    public CopyOnWriteBST() {
    	
    	bst = null; 
        
    }
    
    public CopyOnWriteBST(BSTNode<E> bst){
    	
    	this.bst = bst;
    	
    }
    
    public CopyOnWriteBST(Object E){
    	
    	bst = new BSTNode(E);
    }
    	
    	
    	
    }
    
    

    
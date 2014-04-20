package concurrentBST;

import java.util.*;


import commonFiles.BSTNode;

public class CopyOnWriteBST<E>{
	
	
	
	private final BSTNode<E> bst;
	
    public CopyOnWriteBST() {
    	
    	bst = null; 
        
    }
    
    public CopyOnWriteBST(BSTNode bst){
    	
    	this.bst = bst;
    	
    }
    
    public CopyOnWriteBST(Object E){
    	
    	bst = new BSTNode(E);
    }
    
	public BSTNode CopyTree(BSTNode root) {
		if (root == null)
			return null;
		BSTNode newRoot = null;
		BSTNode curr = root, copyNode;
		HashMap<BSTNode, BSTNode> hm = new HashMap<BSTNode, BSTNode>();

		Queue<BSTNode> q = new LinkedList<BSTNode>();
		q.add(root);
		while (!q.isEmpty()) {
			curr = q.remove();

			copyNode = new BSTNode<E>(curr);
			hm.put(curr, copyNode);

			if (curr.getLeft() != null) {
				q.add(curr.getLeft());
			}
			if (curr.getRight() != null) {

				q.add(curr.getRight());
			}

		}

		q.add(root);
		while (!q.isEmpty()) {
			curr = q.remove();

			copyNode = hm.get(curr);
			copyNode.setLeft(hm.get(curr.getLeft()));
			copyNode.setRight(hm.get(curr.getRight()));
			if (curr.getLeft() != null) {
				q.add(curr.getLeft());
			}
			if (curr.getRight() != null) {

				q.add(curr.getRight());
			}

		}

		return newRoot;

	}
    
	
	
    
    
    	
    	
    	
    }
    
    

    
package commonFiles;

public class BSTNode<E> {
	
	private E key;
	private BSTNode<E> left, right;
	
	public BSTNode(E key){
		this.key = key;
		this.left = null;
		this.right = null;
	}
	
	public BSTNode(BSTNode<E> root){
		this.key = root.key;
		this.left = null;
		this.right = null;
	}
	
	public BSTNode(E key, BSTNode<E> left, BSTNode<E> right){
		this.key = key;
		this.left = left;
		this.right = right;
	}
	
	public E getKey(){
		return key;
	}
	
	public BSTNode<E> getLeft(){
		return left;
	}
	
	public BSTNode<E> getRight(){
		return right;
	}
	
	public void setKey(E newK) {
		key = newK;
	}
	
	public void setLeft(BSTNode<E> newL){
		left = newL;
	}
	
	public void setRight(BSTNode<E> newR){
		right = newR;
	}

}

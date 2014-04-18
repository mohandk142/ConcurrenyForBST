package commonFiles;

public class BSTNode<K> {
	
	private K key;
	private BSTNode<K> left, right;
	
	public BSTNode(K key, BSTNode<K> left, BSTNode<K> right){
		this.key = key;
		this.left = left;
		this.right = right;
	}
	
	public K getKey(){
		return key;
	}
	
	public BSTNode<K> getLeft(){
		return left;
	}
	
	public BSTNode<K> getRight(){
		return right;
	}
	
	public void setKey(K newK) {
		key = newK;
	}
	
	public void setLeft(BSTNode<K> newL){
		left = newL;
	}
	
	public void setRight(BSTNode<K> newR){
		right = newR;
	}

}

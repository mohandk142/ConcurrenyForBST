package commonFiles;

import java.util.concurrent.locks.ReentrantLock;

public class BSTLockNode<E> {
	
	private E key;
	private BSTLockNode<E> left, right;
	private ReentrantLock lock;
	
	public BSTLockNode(E key, BSTLockNode<E> left, BSTLockNode<E> right){
		this.key = key;
		this.left = left;
		this.right = right;
	}
	
	public E getKey(){
		return key;
	}
	
	public BSTLockNode<E> getLeft(){
		return left;
	}
	
	public BSTLockNode<E> getRight(){
		return right;
	}
	
	public void setKey(E newK) {
		key = newK;
	}
	
	public void setLeft(BSTLockNode<E> newL){
		left = newL;
	}
	
	public void setRight(BSTLockNode<E> newR){
		right = newR;
	}
	
	public void lock() {
		lock.lock();
	}

	public void unlock() {
		lock.unlock();
	}

}

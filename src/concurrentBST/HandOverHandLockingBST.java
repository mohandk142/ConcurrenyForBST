package concurrentBST;

import java.util.concurrent.locks.ReentrantLock;

import commonFiles.BSTLockNode;
import commonFiles.BSTNode;

public class HandOverHandLockingBST<E extends Comparable<? super E>> {

	BSTLockNode<E> root;
	public BSTLockNode<E> getRoot() {
		return root;
	}

	public void setRoot(BSTLockNode<E> root) {
		this.root = root;
	}

	ReentrantLock headLock;

	public HandOverHandLockingBST() {
		root = null;
		headLock = new ReentrantLock();
	}

	public boolean insert(E data) {
		BSTLockNode<E> newNode = new BSTLockNode<E>(data, null, null);
		BSTLockNode<E> parentNode = null;
		BSTLockNode<E> currentNode = null;
		int compare = 0;
		headLock.lock();
		if(root == null) {
			root = newNode;
			headLock.unlock();
		} else {
			currentNode = root;
			currentNode.lock();
			headLock.unlock();
			while(true) {
				parentNode = currentNode;
				compare = currentNode.getKey().compareTo(data);
				if(compare > 0) {
					currentNode = currentNode.getLeft();
				} else if(compare < 0) {
					currentNode = currentNode.getRight();
				} else {
					currentNode.unlock();
					return false;
				}

				if(currentNode == null) {
					break;
				} else {
					currentNode.lock();
					parentNode.unlock();
				}
			}

			//Insert the node into the tree
			if(compare > 0)
				parentNode.setLeft(newNode);
			else
				parentNode.setRight(newNode);
			parentNode.unlock();
		}
		return true;
	}

	public boolean lookUp(E data){
		BSTLockNode<E> curNode = null;
		BSTLockNode<E> parentNode = null;
		int compare = 0;

		headLock.lock();
		if(root != null) {
			//The tree is not empty, search the tree for the passed data
			curNode = root;
			curNode.lock();
			headLock.unlock();
			while(curNode != null) {
				compare = curNode.getKey().compareTo(data);
				parentNode = curNode;
				if(compare > 0) {
					curNode = curNode.getLeft();
				} else if(compare < 0) {
					curNode = curNode.getRight();
				} else {
					curNode.unlock();
					return true;
				}

				if(curNode == null) {
					break;
				} else {
					curNode.lock();
					parentNode.unlock();
				}
			}
		} else {
			headLock.unlock();
			return false;
		}
		parentNode.unlock();
		return false;
	}
	
	public E delete(E data) {

		BSTLockNode<E> curNode = null;
		BSTLockNode<E> parentNode = null;
		int compare = 0;
		int oldCompare = 0;

		headLock.lock();
		if(root != null) {
			//Tree is not empty, search for the passed data.  Start by checking
			//the root separately.
			curNode = root;
			parentNode = curNode;
			curNode.lock();
			compare = curNode.getKey().compareTo(data);
			if(compare > 0) {
				//root is "bigger" than passed data, search the left subtree
				curNode = curNode.getLeft();
				oldCompare = compare;
			} else if(compare < 0) {
				//root is "smaller" than passed data, search the right subtree
				curNode = curNode.getRight();
				oldCompare = compare;
			} else {
				//Found the specified data, remove it from the tree
				BSTLockNode<E> replacement = findReplacement(curNode);

				root = replacement;

				if(replacement != null) {
					replacement.setLeft(curNode.getLeft());
					replacement.setRight(curNode.getRight());
				}

				curNode.unlock();
				headLock.unlock();
				return curNode.getKey();
			}
			curNode.lock();
			headLock.unlock();

			while(true) {
				compare = curNode.getKey().compareTo(data);
				if(compare != 0) {
					parentNode.unlock();
					parentNode = curNode;
					if(compare > 0) {
						curNode = curNode.getLeft();
						oldCompare = compare;
					} else if(compare < 0) {
						curNode = curNode.getRight();
						oldCompare = compare;
					}
				} else {
					BSTLockNode<E> replacement = findReplacement(curNode);
					if(oldCompare > 0)
						parentNode.setLeft(replacement);
					else
						parentNode.setRight(replacement);

					//Replace curNode with replacement
					if(replacement != null) {
						replacement.setLeft(curNode.getLeft());
						replacement.setRight(curNode.getRight());
					}

					curNode.unlock();
					parentNode.unlock();
					return curNode.getKey();
				}

				if(curNode == null) {
					break;
				} else {
					curNode.lock();
				}
			}
		} else {
			//Tree is empty
			headLock.unlock();
			return null;
		}

		//The specified data was not in the tree
		parentNode.unlock();
		return null;
	}
	
	public void traversal(BSTLockNode<E> root){

		if(null == root){
			return;
		}	
		traversal(root.getLeft());
		System.out.println(root.getKey());
		traversal(root.getRight());
	}

	private BSTLockNode<E> findReplacement(BSTLockNode<E> subRoot) {
		BSTLockNode<E> curNode = null;
		BSTLockNode<E> parentNode = null;

		if(subRoot.getLeft() != null) {
			parentNode = subRoot;
			curNode = subRoot.getLeft();
			curNode.lock();
			while(curNode.getRight() != null) {
				if(parentNode != subRoot)
					parentNode.unlock();
				parentNode = curNode;
				curNode = curNode.getRight();
				curNode.lock();
			}
			if(curNode.getLeft() != null)
				curNode.getLeft().lock();
			if(parentNode == subRoot)
				parentNode.setLeft(curNode.getLeft());
			else {
				parentNode.setRight(curNode.getLeft());
				parentNode.unlock();
			}
			if(curNode.getLeft() != null)
				curNode.getLeft().unlock();
			curNode.unlock();
		} else if(subRoot.getRight() != null) {
			parentNode = subRoot;
			curNode = subRoot.getRight();
			curNode.lock();
			while(curNode.getLeft() != null) {
				if(parentNode != subRoot)
					parentNode.unlock();
				parentNode = curNode;
				curNode = curNode.getLeft();
				curNode.lock();
			}
			if(curNode.getRight() != null)
				curNode.getRight().lock();
			if(parentNode == subRoot)
				parentNode.setRight(curNode.getRight());
			else {
				parentNode.setLeft(curNode.getRight());
				parentNode.unlock();
			}
			if(curNode.getRight() != null)
				curNode.getRight().unlock();
			curNode.unlock();
		} else {
			return null;
		}
		return curNode;
	}
}
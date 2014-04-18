package commonFiles;

public class QNode {
	
	private BSTNode<Integer> node;
	private int availableProcs;
	
	public QNode(BSTNode<Integer> node, int availableProcs){
		this.node = node;
		this.availableProcs = availableProcs;
	}
	
	public int getProcs(){
		return availableProcs;
	}
	
	public BSTNode<Integer> getNode(){
		return node;
	}
	
	public void setNode(BSTNode<Integer> newNode){
		node = newNode;
	}
	
	public void setProcs(int newProcs){
		availableProcs = newProcs;
	}
}

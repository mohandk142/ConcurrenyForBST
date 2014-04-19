package commonFiles;

public class QNode {
	
	private BSTNode<Object> node;
	private int availableProcs;
	
	public QNode(BSTNode<Object> node, int availableProcs){
		this.node = node;
		this.availableProcs = availableProcs;
	}
	
	public int getProcs(){
		return availableProcs;
	}
	
	public BSTNode<Object> getNode(){
		return node;
	}
	
	public void setNode(BSTNode<Object> newNode){
		node = newNode;
	}
	
	public void setProcs(int newProcs){
		availableProcs = newProcs;
	}
}

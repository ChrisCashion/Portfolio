public class Node{
	private Object Item;//data
	private Node next;//location of next node
	
	public Node(Object newItem){
		this.Item = newItem;
		this.next = null;
	}
	
	public Node(Object newItem, Node nextNode){
		this(newItem);	
		this.next = nextNode;
	}
	//getters and setters
	public Object getItem()	{
		return this.Item;
	}
	
	public void setItem(Object newItem){
	this.Item = newItem;
	}
	
	public Node getNext(){
		return this.next;
	}

	

	public void setNext(Node nextNode)
	{
		this.next = nextNode;
	}

	public String toString()//simple toString method
	{
		return this.getItem().toString();
	}
}









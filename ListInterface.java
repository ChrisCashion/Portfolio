
public interface ListInterface
{
	public int getSize();//gets the size of the list
	public boolean isEmpty();//tells if the list is empty or not.
	public void addNode(Object newItem);//adds a node at the end
	public void addNode(int index, Object newItem);//adds a node with information
	public void removeNode(int index);//removes a certain node
	public void removeAll();//removes all of the nodes in the list
	public String toString();//puts the information we care about into a string for printing
}




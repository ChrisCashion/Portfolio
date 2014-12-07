
import java.util.*;//import all java utilities

import java.lang.*;//import all java lang

public class ListReferenceBased implements ListInterface{
	private Node head;//pointer to next node
	private int numItems;//how many nodes there are
	public ListReferenceBased(){
	
		head= null;//INITIALIZE
		numItems = 0;//keeps track of the number of nodes
	}


	public boolean isEmpty(){
	return numItems == 0;
	}

	public int getSize(){
		return 0;
	}
	
	
	public int getSize(Object newItem)
	{
		return numItems;
	}
  
   
	public void addNode(Object newItem)//adds a node and gives this node that node's location
	{
		Node newNode = new Node(newItem);
		Node curr;

		if(isEmpty())
		{
			this.head=newNode;
		}
		else{
			for(curr= head;
			curr.getNext() != null;
			curr=curr.getNext());
			curr.setNext(newNode);
		}
	numItems++;//adds one for extra nodes in the list
	}

@Override
public void addNode(int index, Object newItem)//takes the index of the new node location and the data to be stored in it to put into a new node
{
	Node newNode = new Node(newItem);
	Node prev;
	
	if(index == 1)
	{
		newNode.setNext(head);
		this.head = newNode;//if there isn't one there, then the head node gets this information
	}

	else{
		prev =find(index=1);
		newNode.setNext(prev.getNext());//finds the first node and adds it there.
	

		prev.setNext(newNode);
	}
	numItems++;
}



@Override
public void removeNode(int index)
{
	if(index == 1){
	   head = head.getNext();//if it's the #1 node, then just delete it and tell the next one it's the head.
   }
	else{
	   Node prev = find(index =1);
	   Node curr = prev.getNext();
	   prev.setNext(curr.getNext());//if it's not the first node, then we have to tell the node before to point to the node after
	}

	numItems--;//subtracts one for every node that is removed
}

@Override
public void removeAll()//deletes the whole string by telling the first node that it's null, thereby making all the others lost in space.
{
	this.head=null;//nullifies the pointers.
	numItems=0;//sets the number to 0, since there are not any nodes to link to and from
}


private Node find(int index)
{

	Node curr = head;
	for (int skip = 1; skip< index; skip++){
		curr=curr.getNext();//iterates down the list to find the one you are looking for
   }
	return curr;
}

@Override
public String toString(){//overrides toString for printing
String result = "";
for(Node curr = this.head; curr != null; curr = curr.getNext()){
	result = result + curr.getItem().toString() + "\n";}
return result;
}


public void sort(){//sorts the list via a bubble sort derivitive

   Node current, next, oneMore = new Node(3,head);
   int i = 0;
   int j = i;
   int w = i;
   
   
  // Node placeHolder = null;
   boolean searching = true;
		
		while(searching == true){
         searching = false;
         for(current = head; current != null; current = current.getNext()){
            
         
             i = (int) current.getItem();//sshhhhh it thinks its an object. It's actually an int.
             if (current.getNext() != null){
                j = (int) current.getNext().getItem();
                }
                  
         if(i < j){
            searching = true;
            w = i;//compares and swaps them around as ints, then puts them back into their homes in the correct order.
            i = j;
            j = w;
            
            current.setItem(i);//setitems for setting them into their homes.
             if (current.getNext() != null){
              current.getNext().setItem(j);
              }
            
            }
          }
        }
       }
public String printBackwards(){//prints the string backwards by mixing up the order in which it puts together the string as it does in the toString Method
	String result = "";
	Node curr = null;
	for(curr= this.head; curr!= null; curr = curr.getNext()){
		result ="\n" + curr.getItem().toString() + result;}
	return result;


	
}
}

	


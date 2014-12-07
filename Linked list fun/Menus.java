//I know some of the features don't work, but I stubbed them out.  
//the sort method is in ListReferenceBased, you can see that I noted out the part of the code that won't compile.
import java.util.*;//imports all of the utilities I might need, and a few I don't.


public class Menus {//menu class!
public static void test(){//the only method in this class!
	int menuVariable, j = 0;//two working variables, menus, things like that
	int work, working = 0;//two working variables
	ListReferenceBased myList = new ListReferenceBased();//make a listreferencebased called my list
	String sOP = "Sorry, that sure didn't work, try again";//default LOLUBROKEIT string, more efficient than typing it 15 times
	Scanner keyin = new Scanner(System.in);//new scanner for the user to input things
	System.out.println("Hello, Welcome to LinkedListFun!");//greeting for the user

	//displays menu options every time the loop restarts
	do{	
      System.out.println("Menu options: 1. Creates a new list \n 2. Sorts the list \n 3. print the list \n 4. prints list backwards \n 5. delete selected node \n 6. deletes the list \n 7. quits the program, as does any number not listed");
		try { menuVariable = keyin.nextInt();}//try catch block for 
      
		catch(InputMismatchException e){//only catches inputmismatchexceptions because lazy
			menuVariable = 0;
			System.out.println(sOP);//displays default error message
			keyin.nextLine();//flushes the buffer
		}
      
	if(menuVariable == 1){//menu option 1
			System.out.println("How many random numbers would you like to generate?");//create new list, user selects length
			try {work = keyin.nextInt();}//more try-catch!
			catch(InputMismatchException e) {
				System.out.println(sOP + "\nGoing with the default of 10");
				work = 10;//defaults to 10 instead of blowing up.
				
			}
			
			for(int i = work; i > 0; i--){//builds the list, using the RNG from another assignment
			j = RandomNumberGenerator.randomGenerator(10000);
			
			myList.addNode(j);
  			}
		}
		if(menuVariable == 2){
         myList.sort();
			System.out.println("The list is sorted!");
			//sort list (list will already be sorted, see above)
		}
		if(menuVariable == 3){
			System.out.println(myList);//prints the list in normal order, iteratively 
		}
		if(menuVariable == 4){//prints the list backwards, also iteratively. 
  			String hello = null;
			hello = myList.printBackwards();//builds the string
			System.out.println(hello);//prints the string we built.
		
		}
	
	
     
		if(menuVariable == 5){
			System.out.println("which node would you like to delete?");
			try{ working = keyin.nextInt();//try catch
				if(working>myList.getSize()|| working < 0){
				   
				}}
			catch(InputMismatchException e){
				System.out.println(sOP);
			}
			myList.removeNode(working);//delete node containing integer (user selected)
		}
		if(menuVariable == 6){
			myList.removeAll();//delete contents of list
			System.out.println("List Deleted");
		}
		if(menuVariable == 7){//quits the program
			menuVariable = -90001;
		}
	
		}while (menuVariable < 7&&menuVariable > -1);//if this happens bounce out of the loop, then goes to driver since class ended
	

		
}
}

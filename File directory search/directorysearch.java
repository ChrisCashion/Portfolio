import java.io.*;//imports all the io libraries for Java. 

import java.util.*;//imports all of the utilities that are going to be used, and a few that aren't.
//Chris Cashion's work for JavaII programming class. Directory searching.
public class directorysearch {

    
    private static long count = 0;//counts the number of files. Used a 'long' because it'll be a positive number and may be really really big. 

    public static void main(String[] args) {
    	String esearching, bsearching = null;//initializes our two strings we are searching
    	System.out.println("The Directory Searcher searches the first, and last part of the file you are looking for. \nIt will return results that have that extension and that beginningf file name.\nWhat directory would you like to search in?");
    	Scanner keyin = new Scanner(System.in);//starts a new keyboard scanner
    	String workingVariable = keyin.nextLine();//gets the next line from the keyboard
      
      File dir = new File (workingVariable);//sets our directory to what we brought in from the keyboard
      
      System.out.println("The name of the file you're looking for");
    	bsearching = keyin.nextLine().toLowerCase();//gets the beginning searching variable from the keyboard and looks at it only in lower case.
    	System.out.println("the extension of the file you're searching for");
    	esearching = keyin.nextLine().toLowerCase();//gets the extension variable from the keyboard and looks at it only in lower case.
      findDirectory(dir, bsearching, esearching);//calls the find directory recursive method and sends it our two variables to work with.
      System.out.println("The program has finished searching the directory, your results are displayed above.");
      System.out.println("The total files found was " + count);//counts the number of files found.
    }

    private static void findDirectory(File parentDirectory, String bsearching, String esearching) {
    
        if(parentDirectory.listFiles() == null) {//if we're at the end, we will start bouncing back up to the surface.
            return;
        }
        File[] files = parentDirectory.listFiles();//makes an array of files from the files listed in the directory
        for (File file : files) {//for every file in files
            if (file.isFile()) {//if the file is a file then...
            	if((file.getName().toLowerCase()).equals(bsearching) && bsearching.length() > 1){//we get the name, lower case it, compare it with our bsearching variable
            		System.out.println(file.getName());//if it is equal to and the length is right, we print the original file name to screen
            		count++;//and increment our count
            	}
            	if(((file.getName()).toLowerCase()).endsWith(esearching) && esearching.length()> 1)//same thing as above, but with the end of the file
            	{
            		System.out.println(file.getName());//prints the original name of the file we found a match for the end of.
            		count++;//increment count
            	}
            	
                continue;
               
            }
       
            if(file.isDirectory()) {//if the file is a directory, and not actually a file, we pass it back to the original function
               findDirectory(file, bsearching, esearching);
            }
        }
    }

}
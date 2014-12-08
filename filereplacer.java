//written by Chris Cashion for himself. Handy for those hard-to-find source-code files with curse words in them.
import java.io.*;//imports ALL of the IO handy-bits
import java.util.Scanner;//imports the scanner for keyin and reading files. 

public class filereplacer {//a terrible name

public static void main(String[] args){//it's all in main, because I wasn't concious about what I was slapping together.

int count = 0;//counting variable, a suggestion of a family member.
String workString = "The String is Null.";//initializes our string. I know it's not null and is a waste of resources as it is.
String keyin, replacer = null;//gives us our other two working strings
Scanner kin = new Scanner(System.in);//scanner for keyboard-in in the console
Scanner reading = null;//initializes our scanner for our file reader
PrintWriter outputFile = null;//initializes our outputfile writer

//These stubbed out for future improvments
/*try{
   //outputFile = new PrintWriter("C:/test.txt");
   }
catch(Exception A){
   System.out.println("Whoops. Something's broke. Time for crashing");
   }*/
//----------------------------------------------------------

System.out.println("Please enter your location of your file.");
keyin = kin.nextLine();//next line of the keyboard buffer set for the location of the file
try{
   reading = FileUtil.openInputFile(keyin);//sets our input scanner to the keyboard entered value and saves it. 
   outputFile = new PrintWriter("C:/test1.txt");//Saves our file as test1.txt on the C:/drive
   }
catch(Exception e){
   System.out.println("File not found. Defaulting to C:/test.txt");//defaults if the above file 404's
   try{
      reading = FileUtil.openInputFile("C:/test.txt");//change this one if you want the default to change.
     outputFile = new PrintWriter("C:/test1.txt");//default output file
      }
   catch(Exception f){
      System.out.println("Could not find either the file you entered or the default file. Exiting.");//crashes this bad boy if both files are missing, which could happen on a mac or linux system.
      }
   }
System.out.println("Please type the word or phrase you are looking to replace.");
keyin = kin.nextLine();//sets our searching word, the word we look the string over to replace. Must be exact. 
System.out.println("Please type the word or phrase you are substituting.");
replacer = kin.nextLine();//sets our replacement word/phrase

while(reading.hasNext()){//makes sure we're not at the end of the file, wouldn't want a NullPointerException!
   workString = reading.nextLine();//read the next line and set it to our working string
   
   if(workString.contains(keyin)){//if that string contains our keyin 
      workString = workString.replace(keyin, replacer);//then replace the word we don't like, with the new one
      count++;//add one to count for every line we replace words in
      }
      
    System.out.println(workString); //writes the string to the console screen.
    outputFile.write(workString + "\n");  //writes the line to file
   }
if(count > 1){
   System.out.println("Did " + count + " replacements.");//little extra function that lets you know how many replacements were done.
   }
else{
   System.out.println("No replacements were done.");//lets you know that no replacements were done without awkward syntax
   }
 outputFile.close();//closes the output file.
 reading.close();//closes our file, no more reading from reading!
 }//end method
}//end class

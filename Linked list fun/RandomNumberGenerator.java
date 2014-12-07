import java.util.Random;
public class RandomNumberGenerator {
	
	  public static int randomGenerator (int i){
		  Random randomGenerator = new Random();//makes a new random number generator
		  int j = randomGenerator.nextInt(i);//parses the random as an int between 0 and the number passed in i and sets it to j
		  return j;//returns j
	  }
}

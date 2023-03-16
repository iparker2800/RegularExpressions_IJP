package One;

/**
 * A class that will search a text file and find patterns. This class accepts two inputs in the command-line.
 *  1. The name of the file. 2. A regex pattern to search in the file. 
 *  This program also counts the number of occurrences, and prints them out.
 *  
 * @author Isaiah Parker
 * @version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Spring 2023
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {
	
	/**
	 * Creating a read-only using java.nio.Charbuffer on the file. Throws IOException 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
  public static CharSequence fromFile(String filename) throws IOException {
    try (FileInputStream fis = new FileInputStream(filename)) {
		FileChannel fc = fis.getChannel();

		ByteBuffer bbuf = fc.map(FileChannel.MapMode.READ_ONLY, 0, (int) fc.size());
		CharBuffer cbuf = Charset.forName("8859_1").newDecoder().decode(bbuf);
		return cbuf;
	}
  }// End of CharSequence

  	/**
  	 * Main method
  	 * @param args
  	 * A try-catch method, to catch the IO exception
  	 * Creates the matcher on file
  	 * Finds all matches and prints them along with how many occurrences
  	 * 
  	 * 
  	 * Pattern to use:
  	 * java One.TextProcessor dracula.txt "(\ [Aa][n]?\ )|(\ [Tt](he ))"
  	 * java One.TextProcessor dracula.txt "\w+( Transylvania)" or "\bTransylvania"
  	 * java One.TextProcessor dracula.txt "(\bMina Harker|\bMrs. Harker)" or "( Mina Harker )|( Mrs. Harker )"
  	 * java One.TextProcessor dracula.txt "\bto\s\w+"
  	 * java One.TextProcessor dracula.txt "\ (?!Helsing)(?!Godalming)\w+(ing)"
  	 */
  public static void main(String[] args){
    // Create matcher on file
	  try {
		  	Pattern pattern = Pattern.compile(args[1]);
		  	Matcher matcher = pattern.matcher(fromFile(args[0]));

    // Finds all matches
    int count = 0;
    while (matcher.find()) {
    // Grabs the matching string
    	
    	System.out.println(matcher.group());
       
        count++;
  	
      
    }//End of while loop
    
    System.out.println("Number of occurences is: " + count);
    System.out.println("\nDone");
    
	  }// End of try
	  
	  /**
	   * This method prints a stack trace for this throwable object on the standard error output stream
	   */
	  catch(IOException e){
		  
		  System.out.println("Error:");
		  e.printStackTrace();
		
	  }// End of catch
	  
  }// End of main	  
}// End of class

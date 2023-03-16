package Two;

/**
 * LogFileProcessor class searches a log file and stores each unique IP address and counts in a Hashmap, or stores unique usernames and counts in a separate Hashmap.
 * 
 * @author Isaiah Parker 
 * @version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Spring 2023
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Scanner;


public class LogFileProcessor {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {	
			
		int totalLines = 0; 
		int input = 0;
		Scanner scan = new Scanner(System.in);
		
		String file = "auth.txt";
		String thisLine = null;
		BufferedReader br = new BufferedReader(new FileReader(file));
	
		
		//Two hashmaps, one for ip addresses, and the other usernames
		HashMap<String, Integer> auth = new HashMap<String, Integer>();
		HashMap<String, Integer> username = new HashMap<String, Integer>();	
	
	
		//The first half of this while loop reads the lines on the file and matches them to the IP address regex pattern.The second half matches lines on the file to the username regex pattern.
		while((thisLine = br.readLine())!= null) {
		
			totalLines++;
		
			Pattern pattern1 = Pattern.compile("(?:\\d{1,3}\\.){3}\\d{1,3}");
			Matcher matcher1 = pattern1.matcher(thisLine);
		
			if(matcher1.find()) {
			
				String match = matcher1.group();
			
				if(auth.containsKey(match)){
					int y = auth.get(match) + 1;
					auth.replace(match, auth.get(match), y);
				
			}//End if
			else {
				
				auth.put(match, 1);
				
			}
		}//End if
		
		Pattern pattern2 = Pattern.compile("(user) \\w+");
		Matcher matcher2 = pattern2.matcher(thisLine);
		
		if(matcher2.find()) {
			
			String match = matcher2.group();
			
			if(username.containsKey(match)){
				int y = username.get(match) + 1;
				username.replace(match, username.get(match), y);
				
			}//End if 
			else {
				
				username.put(match, 1);
				
			}
		}//End if
		
	}//End while
	
		
		//Inputs for user, these are the options of what they'd want to see from the regex matching. 
		System.out.println("Enter 0 for the total number of lines in the log file that were parsed, the unique IP addresses, and the number of unique users in the log: \n");
		System.out.println("Enter 1 for the hashmap of IP addresses along with the total number of lines in the log file that were parsed, the unique IP addresses, and the number of unique users in the log: \n");
		System.out.println("Enter 2 for the hashmap of usernames along with the total number of lines in the log file that were parsed, the unique IP addresses, and the number of unique users in the log: \n");
		input = scan.nextInt();
	
		switch(input) {
	
			case 0:
			
				System.out.println(totalLines + " " + "lines in the log file were parsed.");
				System.out.println("There are " + auth.size() + " unique IP addresses in the log");
				System.out.println("There are " + username.size() + " unique users in the log");
			
				break;
			case 1://Case for list of IPs.
			
				for (String hashmap : auth.keySet()) {
					System.out.println(hashmap + ": " + auth.get(hashmap));// prints all keys and values
				}// End modifier for loop
			
				System.out.println(totalLines + " " + "lines in the log file were parsed.");
				System.out.println("There are " + auth.size() + " unique IP addresses in the log");
				System.out.println("There are " + username.size() + " unique users in the log");
			
				break;
			case 2://Case for list of usernames
			
				for (String hashmap : username.keySet()) {
					System.out.println(hashmap + ": " + username.get(hashmap));// prints all keys and values
				}// End modified for loop
			
				System.out.println(totalLines + " " + "lines in the log file were parsed.");
				System.out.println("There are "+ auth.size() + " unique IP addresses in the log");
				System.out.println("There are "+ username.size() + " unique users in the log");
			
				break;
			default://Default case will return same as case 0.
			
				System.out.println(totalLines + " " + "lines in the log file were parsed.");
				System.out.println("There are" + auth.size() + " unique IP addresses in the log.");
				System.out.println("There are" + username.size() + " unique users in the log.");
			
		}// End switch 

	}//End main

}//End LogFileProcessor
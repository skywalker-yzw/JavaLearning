package yizhiwu.finalproject.runner;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Validator {
	/**
	 * This method reads the value entered by the user and checks whether if it
	 * is valid If the user enters blank spaces or nothing an error message is
	 * displayed.
	 * 
	 * @param scanner - the user input
	 * @param prompt - the text to be shown to user for entering the value
	 * @return String - the valid value entered by the user
	 */
	public static String getRequiredString(Scanner scanner, String prompt) {
		String choice = "";
		boolean isValid = false;

		while (!isValid) {
			System.out.println(prompt);
			choice = scanner.nextLine().trim();
			if (choice.isEmpty()) {
				System.out.println("Error! This entry is required. Try again.");
			} else {
				isValid = true;
			}
		}
		return choice;
	}

	/**
	 * This method checks whether the value entered by the user is a valid one
	 * from among the choices mentioned.
	 * 
	 * @param scanner - the user input
	 * @param prompt - the text to be shown to user for entering the value
	 * @param choice1 - the first valid entry.
	 * @param choice2 - the second valid entry.
	 * @return String - the valid value entered by the user
	 */
	public static String getValidatedChoiceString(Scanner scanner, String prompt, String choice1, String choice2) {
		String choice = "";
		boolean isValid = false;

		while (!isValid) {
			choice = getRequiredString(scanner, prompt);

			if (choice1.equalsIgnoreCase(choice)
					|| choice2.equalsIgnoreCase(choice)) {
				isValid = true;
			} else {
				System.out.println("Error! Entry must be " + choice1 + " or "
						+ choice2 + ". Try again.");
			}
		}
		return choice;
	}

	/**
	 * This method checks whether the file input by the user exists
	 * 
	 * @param scanner - the user input
	 * @param prompt - the text to be shown to user for entering the value
	 * @return the valid file name string
	 */
	public static String getValidatedFile(Scanner scanner, String prompt) {
        String fileStr = null;  
        boolean isValid = false;
        
        while(!isValid) { 
        	System.out.println(prompt);
        	fileStr = scanner.nextLine().trim();;
	        if(!fileStr.isEmpty())
	        {
	        	//get the file path
	        	Path filePath = Paths.get(fileStr);
	        	
	        	//check if file exists. if so, exit from the while loop
	        	if (Files.exists(filePath))
	        	{	
	        		isValid = true;
	        	}	
	        	else
	        	{
	        		System.out.println("File doesn't exist...Please input a valid file!");
	        	}	
	        }
	        else
	        {
	        	System.out.println("Please input a file!");
	        }	
        } 
		return fileStr;
	}
}	

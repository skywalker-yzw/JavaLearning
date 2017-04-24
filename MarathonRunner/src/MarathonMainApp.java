package yizhiwu.finalproject.runner;

import java.util.Scanner;

/**
 * This is an application to simulate a Marathon run race. It will give options to user to read runner information 
 * from multiple data source, such as Derby database, text file and XML files. By default, it will have two runners 
 * to complete with each other. The application will start all the runner as multiple thread and declare the final winner
 * when it reaches the endline. The other runner will concede the race and quit.
 * @author Yizhi
 * @version 1.0 Nov 25, 2014
 * 
 * @author Yizhi
 *
 */
public class MarathonMainApp {
	//define threadRunnerManager to manage all the runners
	private static ThreadRunnerMananger threadRunnerManager = new ThreadRunnerMananger();
	
	//runnerReader is to read runner inforamtion from different data source
	private static ThreadRunnerReader runnerReader;
	
	//this flag is used to make sure we only declare one winner
	private static boolean finishFlag = false;
	
	public static void main(String args[])
	{		
        // display a welcome message
        System.out.println("Welcome to the Marathon Race Runner Program\n");	
         
        // perform actions
        String choice = "";
		Scanner scanner = new Scanner(System.in);
        while (!choice.equalsIgnoreCase("5"))
        {
            // display the command menu
            displayMenu();     
            
            // get the input from the user
            choice = Validator.getRequiredString(scanner, "Enter your choice: ");
            System.out.println();

            switch(choice)
            {
            	case "1":
            		runnerReader = new RunnerInfoDB();  
            		
            		//add all the runners
            		threadRunnerManager.addTreadRunner(runnerReader.getRunners());		
            		break;
            		
            	case "2":
            		String xmlFileStr = Validator.getValidatedFile(scanner, "Enter XML file names: ");
            		
            		runnerReader = new RunnerInfoXMLFile(xmlFileStr);  
            		
            		//add all the runners
            		threadRunnerManager.addTreadRunner(runnerReader.getRunners());
            		break;
            		
            	case "3":
            		String textFileStr = Validator.getValidatedFile(scanner, "Enter text file names: ");
            		
            		runnerReader = new RunnerInfoTextFile(textFileStr);
            		
            		//add all the runners
            		threadRunnerManager.addTreadRunner(runnerReader.getRunners());            		
            		break;
                
            	case "4":            		
            		//construct the two default runners: Tortoise and Hare
            		ThreadRunner Tortoise = new ThreadRunner("Tortoise", 10, 0);
            		ThreadRunner Hare = new ThreadRunner("Hare", 100, 90);  
            		
            		//add these two default runners
            		threadRunnerManager.addTreadRunner("Tortoise", Tortoise);
            		threadRunnerManager.addTreadRunner("Hare", Hare);           		
            		break;
                
            	case "5":
            		System.out.println("Thank you for using my Marathon Race Program");
                    continue;
                    
                default:
                	System.out.println("Not a valid choice! Please choose from 1~5\n");
                	continue;
            } 
            
            //start the Marathon run
            threadRunnerManager.startRun();
            
            //reset the finish flag for the next fresh run
            finishFlag = false;
            
            System.out.println("\nPress any key to continue . . .");
            scanner.nextLine();
        }       
	}
	
	/** Method to display menu
	 */	
	public static void displayMenu()
	{
		System.out.println("Select your data source:\n");
		System.out.println("1. Derby database");
		System.out.println("2. XML file");
		System.out.println("3. Text file");
		System.out.println("4. Default two runners ");
		System.out.println("5. Exit\n");		
	}
	
	/**This is the synchronized finished method that the first run who finish the marathon run 
	 * will call. It will declare the winner an interrupt all the runners to concede the race and
	 * quit. Note that we will only declare one winner and there is no ties.
	 * @param finishedThreadRunner the name of the runner who finish the run
	 */
	public synchronized static void finished(String finishedThreadRunner)
	{
		//use a finishFlag to make sure only declare one winner 
		if(finishFlag == false)
		{			
			System.out.println("The race is over! The " + finishedThreadRunner + " is the winner.\n");
			
			//interrupt all other runners
			threadRunnerManager.interruptAllOtherThreadRunner(finishedThreadRunner);
			
			finishFlag = true;
		}
	}
}

package yizhiwu.finalproject.runner;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the class to read runner information from text files. It implements the ThreadRunnerReader the interface,
 * so it needs to override getRunners method.
 * @author Yizhi
 * @version 1.0 Nov 25, 2014
 * 
 * @author Yizhi
 *
 */
public class RunnerInfoTextFile implements ThreadRunnerReader{
	//Use HashMap to store all the ThreadRunners
	private HashMap<String, ThreadRunner> threadRunners;
	
    private Path textFilePath;
    private File runnerInfoFile;
	
    //define string delimiter(multiple spaces)
    private final String FIELD_SEP = " +";
    
    /**default constructor*/
	public RunnerInfoTextFile()
	{
		//initialize all class members as null
		threadRunners = null;
		textFilePath = null;
		runnerInfoFile = null;
	}
	
	/** Constructor with passing=in file path
	 * 
	 * @param filePath pass in the path of the text file
	 */
	public RunnerInfoTextFile(String filePath)
	{
		this();	
		
		textFilePath = Paths.get(filePath);
		runnerInfoFile = textFilePath.toFile();	
		
		threadRunners = this.getRunners();
	}   
	
	@Override
	public HashMap<String, ThreadRunner> getRunners()
	{
        // if the runner info file has already been read, don't read it again
        if (threadRunners != null)
            return threadRunners;        

        threadRunners = new HashMap<>();        
        
        // TO-DO: make sure file path exists, prevent the FileNotFoundException
        if (Files.exists(textFilePath))  
        {
            try (BufferedReader in = 
                     new BufferedReader(
                     new FileReader(runnerInfoFile)))
            {
                // read all runner stored in the file
                String line = in.readLine();
                while(line != null)
                {
                    String[] columns = line.split(FIELD_SEP);
                    String runnersName = columns[0];
                    String runnersSpeed = columns[1];
                    String restPercentage = columns[2];
                    
                    // construct the runner objects
                    ThreadRunner runner = new ThreadRunner(runnersName, 
                    		                               Integer.parseInt(runnersSpeed), 
                    		                               Integer.parseInt(restPercentage));
                    // put it into the hashmap	
                    threadRunners.put(runnersName, runner);

                    line = in.readLine();                    
                }
            }
            catch(IOException e)
            {
                System.out.println(e);
                return null;
            }
        }
        else
        {
        	System.out.println("Can't find this file...Please input an exist text file!");
        	return null;
        }	
        
        //printAllRunner();
        
        return threadRunners;            
    }

	/**Method to print out all runners read out from text file, for debugging purpose
	 */	
	public void printAllRunner()
	{	
		if(threadRunners != null)
		{
			//loop the set to print out all runners
			for (Map.Entry runner : threadRunners.entrySet())
			{
				System.out.println(runner.getKey() + ": " + runner.getValue());
			}
		}
		else
		{
			System.out.println("No runner to print out");
		}	
	}	
}

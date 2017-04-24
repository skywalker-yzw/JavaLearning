package yizhiwu.finalproject.runner;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.*;  // StAX API

/**
 * This is the class to read runner information from XML files. It implements the ThreadRunnerReader the interface,
 * so it needs to override getRunners method.
 * @author Yizhi
 * @version 1.0 Nov 25, 2014
 * 
 * @author Yizhi
 *
 */
public class RunnerInfoXMLFile implements ThreadRunnerReader{
	//Use HashMap to store all the ThreadRunners
	private HashMap<String, ThreadRunner> threadRunners;

    private Path xmlFilePath;
    
    /**default constructor*/
	public RunnerInfoXMLFile()
	{
		//initialize all class members as null
		threadRunners = null;
		xmlFilePath = null;
	}
	
	/** Constructor with passing=in file path
	 * 
	 * @param filePath pass in the path of the xml file
	 */
	public RunnerInfoXMLFile(String filePath)
	{
		this();	
		
		xmlFilePath = Paths.get(filePath);	
		
		threadRunners = this.getRunners();
	}   
	
	@Override
	public HashMap<String, ThreadRunner> getRunners()
	{
        // if the runner info file has already been read, don't read it again
        if (threadRunners != null)
            return threadRunners;        

        threadRunners = new HashMap<>();        
        
        // make sure file path exists, prevent the FileNotFoundException
        if (Files.exists(xmlFilePath))  
        {
            // create the XMLInputFactory object
            XMLInputFactory inputFactory = XMLInputFactory.newFactory();
            
            try
            {
                // create a XMLStreamReader object
                FileReader fileReader =
                    new FileReader(xmlFilePath.toFile());
                XMLStreamReader reader =
                    inputFactory.createXMLStreamReader(fileReader);

                String runnerName = null, RunnersMoveIncrement = null,restPercentage = null;
                
                // read the runners from the XML file
                while (reader.hasNext())
                {
                	
                    int eventType = reader.getEventType();
                    switch (eventType)
                    {
                        case XMLStreamConstants.START_ELEMENT:
                            String elementName = reader.getLocalName();
                            if (elementName.equals("Runner"))
                            {
                                runnerName = reader.getAttributeValue(0);
                            }
                            if (elementName.equals("RunnersMoveIncrement"))
                            {
                                RunnersMoveIncrement = reader.getElementText();
                            }
                            if (elementName.equals("RestPercentage"))
                            {
                                restPercentage = reader.getElementText();
                            }
                            break;
                        case XMLStreamConstants.END_ELEMENT:
                            elementName = reader.getLocalName();
                            if (elementName.equals("Runner"))
                            {
                            	if((runnerName != null) &&  (RunnersMoveIncrement != null) && (restPercentage != null))
                            	{
                            		//construct the runner
                                    ThreadRunner runner = new ThreadRunner(runnerName, 
      		                               Integer.parseInt(RunnersMoveIncrement), 
      		                               Integer.parseInt(restPercentage));   
                                    
                                    //put runner into the hashmap
                                	threadRunners.put(runnerName, runner); 
                                	
                                	//reset local parameters
                                	runnerName = null;
                                	RunnersMoveIncrement = null;
                                	restPercentage = null;
                            	}
                            	else
                            	{
                            		System.out.println("XML file format is wrong! Information is incomplete!");
                            	}	

                            }
                            break;
                        default:
                            break;
                    }
                    reader.next();
                }
            }
            catch (IOException | XMLStreamException e)
            {
                System.out.println(e);
                return null;
            }
        }
        else
        {
        	System.out.println("Can't find this file...Please input an exist XML file!");
        	return null;
        }	
        
        //printAllRunner();
        
        return threadRunners;            
    }
	
	/**Method to print out all runners read out from xml file, for debugging purpose
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

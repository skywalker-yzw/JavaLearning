package yizhiwu.finalproject.runner;

import java.util.Random;

/**
 * This defines the runner thread. Each runner will run for the 1000 meter marathon in this case. The first one finish will decalre
 * victory and notify all other runners to concede and quit the race.
 * @author Yizhi
 * @version 1.0 Nov 25, 2014
 * 
 * @author Yizhi
 *
 */
public class ThreadRunner extends Thread implements RunConstants{
	private String runnersName;
	private int runnersSpeed;
	private int restPercentage;
	private int currentPos = 0;
	
	/** default constructor */
	public ThreadRunner()
	{
		this("", 0, 0);
	}
	
	/**
	 * Constructor with runner information
	 * @param runnersName runner name
	 * @param runnersSpeed runner speed
	 * @param restPercentage how often the runner will rest
	 */
	public ThreadRunner(String runnersName, int runnersSpeed, int restPercentage)
	{
		this.runnersName = runnersName;
		this.runnersSpeed = runnersSpeed;	
		this.restPercentage = restPercentage;
		//set the thread name with runner name
		super.setName(runnersName);
	}
	
	@Override
    public void run()
    { 
		//create a random number generator 
		Random randomGenerator = new Random();
		
		//get current running thread
		Thread ct = Thread.currentThread();
		
		// if run is not finished
        while(currentPos < TOTAL_RUN_LENGTH)
        {
			//if random number indicates runner should run
			if(randomGenerator.nextInt(100) > restPercentage)
			{
				//update the current position  
				currentPos += runnersSpeed;   
				
				if(currentPos < TOTAL_RUN_LENGTH)
				{	
					//print out the current position
					System.out.println(runnersName + " :" + currentPos);
				}	
				else
				{
					// length of the run can't exceed TOTAL_RUN_LENGTH
					System.out.println(runnersName + " :" + TOTAL_RUN_LENGTH);
					
					System.out.println(runnersName + "  : I finished!\n");
					
		        	//call the synchronized finish method in the main class to declare victory and interrupt other runners
		        	MarathonMainApp.finished(ct.getName());	      					
				}	
			}	
			
            try
            {
                //sleep 100ms for each loop iteration 
            	Thread.sleep(100);
            }
            catch (InterruptedException e) {
            	//concede race and quit
            	System.out.println( runnersName + "  : You beat me fair and square.");
            	break;
            }  
        }	        	
    }
		
    @Override
    public String toString()
    {
        return ("Runner(" + runnersName + "," + runnersSpeed + "," + restPercentage + ")") ;
    }	
}

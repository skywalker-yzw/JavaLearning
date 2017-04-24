package yizhiwu.finalproject.runner;

import java.util.HashMap;
import java.util.Map;

/**
 * This class will manage the runners and coordinate the Marathon run
 * @author Yizhi
 * @version 1.0 Nov 25, 2014
 * 
 * @author Yizhi
 *
 */
public class ThreadRunnerMananger {
	//try to use HashMap to store all the ThreadRunners
	private HashMap<String, ThreadRunner> threadRunnerManager;
	
	/**default constructor*/
	public ThreadRunnerMananger()
	{
		threadRunnerManager = new HashMap<>();
	}
	
    /**
     * Add runner into runner manager with passing in runner name and runner objects
     * @param threadName runner name
     * @param runner     runner object
     */
	public void addTreadRunner(String threadName, ThreadRunner runner)
	{
		threadRunnerManager.put(threadName, runner);
		
	}
    
	/**
	 * Add runner with a hashmap contains all runners
	 * @param runners  runner hashmap
	 */
	public void addTreadRunner(HashMap<String, ThreadRunner> runners)
	{
		// copy over the runners	
		threadRunnerManager.putAll(runners);;
		
	}	
	
	/**
	 * This method start the marathon run, allow all runners to join before return to main thread, 
	 * and clean up the hashmap for the next fresh run
	 */
	public void startRun()
	{
		//printAllThreadRunner();
		
		//start all runners
		startAllThreadRunner();
		
		//allow all runners join
		allThreadRunnerJoin();
		
		//clean up threadRunnerManager, so that runner data won't mess up for next fresh run
		removeAllThreadRunners();
	}
	
	/**
	 * This method will start all the runner threads
	 */
	public void startAllThreadRunner()
	{
		ThreadRunner runnerCnt; 
		
		System.out.println("Get set...Go!");
		
		//loop the set to let all runners to run
		for (Map.Entry runner : threadRunnerManager.entrySet())
		{
			runnerCnt = (ThreadRunner)runner.getValue();
			runnerCnt.start();
		}	
	}

	/**
	 * This method will allow all runner thread to join 
	 */
	public void allThreadRunnerJoin()
	{
		ThreadRunner runnerCnt; 
		
		try
		{		
			//loop the set to allow all runners join
			for (Map.Entry runner : threadRunnerManager.entrySet())
			{
				runnerCnt = (ThreadRunner)runner.getValue();
	
				runnerCnt.join();
			}				
		}catch(InterruptedException e) {}		
	}
	
	/**
	 * This method will interrupt all other runners
	 * @param currentThreadName the name of the current thread
	 */
	public void interruptAllOtherThreadRunner(String currentThreadName)
	{
		ThreadRunner runnerCnt; 
		
		//loop the set to interrupt all other thread, except the current thread
		for (Map.Entry runner : threadRunnerManager.entrySet())
		{
			runnerCnt = (ThreadRunner)runner.getValue();
			
			if(!currentThreadName.equalsIgnoreCase(runnerCnt.getName()))
			{
				runnerCnt.interrupt();
				//System.out.println("runner " + runnerCnt.getName() + "is interrupted");
			}	
		}				
	}
	
	/**
	 * Remove all the runners, so that it's ready for the next fresh run
	 */
	public void removeAllThreadRunners()
	{
		threadRunnerManager.clear();
	}
	
	public void printAllThreadRunner()
	{		
		System.out.println("Print out all runners for debug purpose");
		
		//loop the set to print out all runners
		for (Map.Entry runner : threadRunnerManager.entrySet())
		{
			System.out.println(runner.getKey() + ": " + runner.getValue());
		}			
	}	
}

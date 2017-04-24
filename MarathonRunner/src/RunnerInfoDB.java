package yizhiwu.finalproject.runner;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the class to read runner information from derby database. It implements the ThreadRunnerReader the interface,
 * so it needs to override getRunners method.
 * @author Yizhi
 * @version 1.0 Nov 25, 2014
 * 
 * @author Yizhi
 *
 */
public class RunnerInfoDB implements ThreadRunnerReader{  
		//Use HashMap to store all the ThreadRunners
		private HashMap<String, ThreadRunner> threadRunners;
		
		public RunnerInfoDB()
		{
			threadRunners = this.getRunners();
		}
		
		/**Method to establish the database connection
		 * 
		 * @return Connection Derby database connection
		 */
		private Connection connect()
	    {
	        Connection connection = null;
	        try
	        {
		        String dbDirectory = "resources";
		        System.setProperty("derby.system.home", dbDirectory);
		
		        String url = "jdbc:derby:RunnerDB";
		        String user = "";
		        String password = "";
		        connection = DriverManager.getConnection(url, user, password);
		    }
		    catch(SQLException e)
		    {
		        System.err.println("Error loading database driver: " + e);
		    }
	        
	        return connection;
	    }

		@Override
		public HashMap<String, ThreadRunner> getRunners()
	    {
	        // if the runner info file has already been read, don't read it again
	        if (threadRunners != null)
	            return threadRunners; 
	        
	        threadRunners = new HashMap<>();   
	        
	        try
	        {
	            Connection connection = connect();

	            String query = "SELECT Name, RunnersSpeed, RestPercentage "
	                         + "FROM Runners ORDER BY Name ASC";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ResultSet rs = ps.executeQuery();

	            while(rs.next())
	            {
	            	// get the runner name, speed and rest percentage
	                String Name = rs.getString("Name");
	                int RunnersSpeed = rs.getInt("RunnersSpeed");
	                int RestPercentage = rs.getInt("RestPercentage");
	                
	                //construct the runner
	                ThreadRunner runner = new ThreadRunner(Name, RunnersSpeed, RestPercentage);
	                //add the runner into the hashmap
	                threadRunners.put(Name,runner);
	            }
	            rs.close();
	            ps.close();
	            connection.close();
	            
	            //printAllRunner();
	            
	            return threadRunners;
	        }
	        catch(SQLException sqle)
	        {
	            //sqle.printStackTrace();  // for debugging
	            return null;
	        }
	    }
		
		/**Method to print out all runners read out from database, for debugging purpose
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

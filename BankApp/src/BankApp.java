import java.util.Scanner;

/**
 * This is the bank application, which will simulate the banking experience of a bank customer
 * The application first prompts the user to enter the information for a transaction, including 
 * whether a withdrawal or deposit is to be made, whether the transaction will be posted to 
 * the checking or savings account, and the amount of the transaction. When the user (customer) 
 * finishes deposits and withdrawals, the application displays the fees and payments for the month 
 * followed by the final balances for the month
 * @author Yizhi
 * @version 1.0 Oct 10, 2014
 */
public class BankApp
{
	private static Customer customer;
	//define AccountOperation type variable account 
	//for dynamic binding later on
	private static AccountOperation account;
	
	public static void main(String args[])
	{
		System.out.println("Welcome to the Account application\n");
		
		// create the new customer here with initial balance $1000 for both accounts 
		customer = new Customer(1000.0F,1000.0F);
		
		System.out.println("Starting Balances");
		customer.printBalance();

		System.out.println("Enter the transactions for the month");
		
        // enter the loop to run the bank transactions
		Scanner sc = new Scanner(System.in);
		String choice = "y";
		while (choice.equalsIgnoreCase("y"))
		{
            // let customer to choose withdraw or deposit
            String action = Validator.getString(sc,
                "\nWithdrawal or deposit? (w/d):");
            
            if(action.equalsIgnoreCase("w"))
            {
            	//let customer to choose checking or saving account
            	String accountType = Validator.getString(sc,
                        "Checking or savings? (c/s):");
            	
            	if(accountType.equalsIgnoreCase("c"))
            	{
            		float amount = Validator.getFloat(sc, "Amount?:");
            		
            		//dynamic binding to CheckingAccount object
            		account = customer.getCheckingAccount();
            		
            		//call Customer method to withdraw money from checking account
            		if(customer.withdrawMoney(account, amount) == false)
            		{
            			System.out.println("Withdraw from checking account failed, try again!");
            			continue;
            		}	            			       		
            	}
            	else if(accountType.equalsIgnoreCase("s"))
            	{
            		float amount = Validator.getFloat(sc, "Amount?:");
            		
            		//dynamic binding to SavingAccount object
            		account = customer.getSavingAccount();
            		
            		//call Customer method to withdraw money from checking account
            		if(customer.withdrawMoney(account, amount) == false)
            		{
            			System.out.println("Withdraw from saving account failed, try again!");
            			continue;
            		}	       		            		
            	}
            	else
            	{
            		System.out.println("Invalid choice!");
            		continue;
            	}	
            }
            else if(action.equalsIgnoreCase("d"))
            {
            	//let customer to choose checking or saving account
            	String accountType = Validator.getString(sc,
                        "Checking or savings? (c/s):");
            	
            	if(accountType.equalsIgnoreCase("c"))
            	{
            		float amount = Validator.getFloat(sc, "Amount?:");
              	    
            		//dynamic binding to CheckingAccount object
            		account = customer.getCheckingAccount();
            		
            		//call Customer method to deposit money to checking account	
            		if(customer.depositMoney(account, amount) == false)
            		{
            			System.out.println("Deposit to checking account failed, try again!");
            			continue;
            		}	           		   		
            	}
            	else if(accountType.equalsIgnoreCase("s"))
            	{
            		float amount = Validator.getFloat(sc, "Amount?:");
            		
            		//dynamic binding to SavingAccount object
            		account = customer.getSavingAccount();
            		
            		//call Customer method to deposit money to saving account	
            		if(customer.depositMoney(account, amount) == false)
            		{
            			System.out.println("Deposit to saving account failed, try again!");
            			continue;
            		}	               		        		
            	}
            	else
            	{
            		System.out.println("Invalid choice!");
            		continue;
            	}           	
            }
            else
            {
        		System.out.println("Invalid choice!");
        		continue;            	
            }	
			           
            // see if the user wants to continue
            choice = Validator.getString(sc, "\nContinue? (y/n): ");			
		}	
		
		// Exiting, displays the fees and payments for the month
		//followed by the final balances for the month
		System.out.println("\nMonthly Payments and Fees");
		customer.printMonthlySummary();
		
		//print out final balance
		System.out.println("\nFinal Balances");
		customer.calcFinalBalance();
		customer.printBalance();
	}
}
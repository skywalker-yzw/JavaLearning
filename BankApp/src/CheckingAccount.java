/**
 * Defined the CheckingAccount class, which is a noun in this banking system and a 
 * subclass of BankAccount class. It implements the AccountOperation interface, 
 * includes withdraw, deposit and printBalance methods. Apart from the interface methods, 
 * CheckingAccount class also implements basic set/get balance methods
 * and the utility methods, such as calcFinalBalance
 * @author Yizhi
 * @version 1.0 Oct 10, 2014
 */
public class CheckingAccount extends BankAccount implements AccountOperation
{
	/** Variable checkingBalance to keep track of checking balance*/
	private float checkingBalance;
	
	/**
	 * Default constructor
	 */
	public CheckingAccount()
	{
		checkingBalance = 0.0F;
	}
	
	/**
	 * Constructor with initial starting balance
	 * @param initialBalance  starting balance
	 */
	public CheckingAccount(float initialBalance)
	{
		this.checkingBalance = initialBalance;
	}

	/**
	 * Get current checking balance
	 * @return   return current checking balance
	 */
	public float getBalance()
	{
		return this.checkingBalance;
	}
	
	/**
	 * Update current checking balance
	 * @param balance  the balance amount want to update to
	 */
	public void setBalance(float balance)
	{
		this.checkingBalance = balance;
	}	

	/** Utility method to Calculate the final checking balance after pay for checking fee*/
	public void calcFinalBalance()
	{
		//call setBalance method to update current balance
		setBalance(this.checkingBalance - this.getCheckingFee());
	}

	
	/**
	 * implement AccountOperation interface method withdraw()
	 * @param amount withdraw amount
	 * @return       return false if failed; otherwise, return true 
	 */
	public boolean withdraw(float amount)
	{		
		//withdraw amount can't be less than 0, return false (failure)
		if(amount < 0)
		{
			System.out.println("Withdraw amount can't be negative!");
			return false;
		}
		else
		{
			// not enough money, overdraft, return false (failure) 	
			if(this.checkingBalance < amount)
			{
				System.out.println("Not enough money to withdraw from checking account!");
				return false;
			}	
			else
			{
				//update balance, return true
				this.checkingBalance -= amount;
				return true;
			}	
		}	
	}
	
	/**
	 * implement AccountOperation interface method deposit()
	 * @param amount withdraw amount
	 * @return       return false if failed; otherwise, return true 
	 */	
	public boolean deposit(float amount)
	{
		//deposit amount can't be negative, return false (failure)
		if(amount < 0)
		{
			System.out.println("Deposit money can't be negative!");
			return false;
		}
		else
		{	
			//update balance, return true
			this.checkingBalance += amount;
			return true;
		}	
	}
	
	/**
	 * implement AccountOperation interface method printBalance()ß
	 */		
	public void printBalance()
	{
		System.out.format("Checking: $%.2f%n", checkingBalance);
	}

	@Override
	public String toString() {
		return "CheckingAccount [checkingBalance=%.2f%n" + checkingBalance + "]";
	}
}
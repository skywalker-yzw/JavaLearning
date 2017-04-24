/**
 * Defined the SavingAccount class, which is a noun in this banking system and a subclass of BankAccount class.
 * It implements the AccountOperation interface, includes withdraw, deposit and printBalance methods
 * Apart from the interface methods, CheckingAccount class also implements basic set/get balance methods
 * and the utility methods, such as calcFinalBalance
 * @author Yizhi
 * @version 1.0 Oct 10, 2014
 */
public class SavingAccount extends BankAccount implements AccountOperation
{
	/** Variable savingBalance to keep track of saving account balance*/
	private float savingBalance;
	
	/** Default constructor */
	public SavingAccount()
	{
		savingBalance = 0.0F;
	}
	
	/**
	 * Constructor with initial starting balance
	 * @param initialBalance   starting balance
	 */
	public SavingAccount(float initialBalance)
	{
		this.savingBalance = initialBalance;
	}
	
	/**
	 * Get current saving balance
	 * @return   return current checking balance
	 */	
	public float getBalance()
	{
		return this.savingBalance;
	}
	
	/**
	 * Update current saving balance
	 * @param balance the balance amount we want to update to
	 */	
	public void setBalance(float balance)
	{
		this.savingBalance = balance;
	}
    
	/** Utility method to calculate final balance after adding up interests*/
	public void calcFinalBalance()
	{
		setBalance(this.savingBalance * (1+ this.getSavingInterest()));
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
			if(this.savingBalance < amount)
			{	
				System.out.println("Not enough money to withdraw from saving account!");
				return false;
			}	
			else
			{
				//update balance, return true
				this.savingBalance -= amount;
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
			this.savingBalance += amount;
			return true;
		}		
	}

	/**
	 * implement AccountOperation interface method printBalance()
	 */	
	public void printBalance()
	{
		System.out.format("Saving: $%.2f%n", savingBalance);
	}

	@Override
	public String toString() {
		return "SavingAccount [savingBalance=%.2f%n" + savingBalance + "]";
	}
}
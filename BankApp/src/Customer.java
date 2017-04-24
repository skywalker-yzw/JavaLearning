/**
 * Defined the Customer class, which is an important noun in our banking system.
 * A customer will start with some initial money on both their saving and checking account.
 * Then she/he can withdraw money from or deposit money to whatever accounts they want.
 * After transaction is done, she/he can print out the monthly summary and the final balance.
 * Customer class also defines basic get method on the accounts, as well as the utility methods, 
 * such as calcFinalBalance.
 * @author Yizhi
 * @version 1.0 Oct 10, 2014
 */
public class Customer
{
	/** Checking acccount of the customer*/
	private CheckingAccount checkingAccount;
	/** Saving account of the customer*/
	private SavingAccount  savingAccount;
	
	/*Default constructor*/
	public Customer()
	{
		checkingAccount = new CheckingAccount();
		savingAccount = new SavingAccount();		
	}
	
	/**
	 * Constructor to instantiate a customer with initial money on both
	 * saving account and checking account
	 * @param initialChecking  starting balance on checking account
	 * @param initialSaving    starting balance on saving account
	 */
	public Customer(float initialChecking, float initialSaving)
	{
		checkingAccount = new CheckingAccount(initialChecking);
		savingAccount = new SavingAccount(initialSaving);
	}

	/**
	 * Get method to return saving account as AccountOperation type
	 * @return  return saving account as generic AccountOperation type
	 */
	public AccountOperation getSavingAccount()
	{		
		return savingAccount;
	}

	/**
	 * Get method to return checking account as AccountOperation type
	 * @return  return checking account as generic AccountOperation type
	 */
	public AccountOperation getCheckingAccount()
	{
		return checkingAccount;
	}

	/**
	 * Method provided to customer to withdraw money on any account
	 * @param op       pass in account in format of AccountOperation
	 * @param amount   amount of the money to withdraw
	 * @return         true for success, false for failure
	 */
	public boolean withdrawMoney(AccountOperation op, float amount)
	{
		return op.withdraw(amount);
	}

	/**
	 * Method provided to customer to deposit money on any account
	 * @param op       pass in account in format of AccountOperation
	 * @param amount   amount of the money to deposit
	 * @return         true for success, false for failure
	 */
	public boolean depositMoney(AccountOperation op, float amount)
	{
		return op.deposit(amount);
	}
	
	/** Utility method to calculate the final balance on both accounts*/
	public void calcFinalBalance()
	{
		this.savingAccount.calcFinalBalance();
		this.checkingAccount.calcFinalBalance();
	}
	
	/** Utility method to print out monthly summary 
	 * after paying checking fee and add interests*/
	public void printMonthlySummary()
	{
		System.out.format("Checking fee:               $%.2f%n", this.checkingAccount.getCheckingFee());
		System.out.format("Savings interest payment:   $%.2f%n", 
		                    this.savingAccount.getSavingInterest() * this.savingAccount.getBalance());
	}
	
	/**Utility method to print final balance on both accounts*/
	public void printBalance()
	{		
		checkingAccount.printBalance();
		savingAccount.printBalance();
		System.out.println();
	}

	@Override
	public String toString() {
		return "Customer [checkingAccount=" + checkingAccount
				+ ", savingAccount=" + savingAccount + "]";
	}
}
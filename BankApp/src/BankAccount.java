/**
 * This is the base class BankAccount. It encapsulates the common information 
 * of the bank: checking fee and saving interest, which are unique to the bank. 
 * Other classes can only get those bank information through the get methods 
 * @author Yizhi
 * @version 1.0 Oct 20, 2014
 */
public class BankAccount
{
	/**Chekcing fee of the bank*/
	private final float CHECKING_FEE = 1.0F;
	/** monthly saving interest of the bank*/
    private final float SAVING_INTEREST = 0.01F;
    
    
	/**
	 * Get method to return checking fee of the bank
	 * @return    checking fee of the bank
	 */
	public float getCheckingFee()
	{
		return CHECKING_FEE;
	}
	
	/**
	 * Get method to return monthly saving interest
	 * @return    checking fee of the bank
	 */	
	public float getSavingInterest()
	{
		return SAVING_INTEREST;
	}

	@Override
	public String toString() {
		return "BankAccount [CHECKING_FEE=" + CHECKING_FEE
				+ ", SAVING_INTEREST=" + SAVING_INTEREST + "]";
	}	
}
/**
 * This is the interface for all the bank accounts, which describes the common contract for all 
 * bank accounts
 * @author Yizhi
 * @version 1.0 Oct 20, 2014
 */
public interface AccountOperation
{
	public boolean withdraw(float amount);
	public boolean deposit(float amount);
	public void printBalance();
}
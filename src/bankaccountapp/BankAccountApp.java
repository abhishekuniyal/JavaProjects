package bankaccountapp;

import java.io.FileNotFoundException;

public class BankAccountApp {

	public static void main(String[] args) throws FileNotFoundException {
		
		//Create an Saving account
		
/*		
		Saving saving = new Saving("Peter", "SSN123", "Savings", 2000);
		saving.deposit(4000);
		saving.withdraw(1000);
		saving.transfer(1000, "10002000");
		saving.showInfo();
*/		
//		Checking checking = new Checking("Sam", "SSN456", "Checking", 5000);
//		checking.showInfo();

		Utility.readCsv();
	}

}

package bankaccountapp;

public class Checking extends Account {
	
	private int debitCardNumber;
	private int debitCardPin;
	
	public Checking(String name, String sSN, String accountType, double initialDeposit) {
		super(name, sSN, accountType, initialDeposit);
		generateDebitCard();
	}

	private void generateDebitCard() {
		debitCardNumber = (int) (Math.random() * Math.pow(10, 12));
		debitCardPin = (int) (Math.random() * Math.pow(10, 4));
	}
	
	@Override
	protected void setRate() {
		rate = baseRate() * 0.15;
	}
	
	public void showInfo() {
		System.out.println("************Checking Account Details****************");
		super.showInfo();
		System.out.println("debitCardNumber::"+debitCardNumber
				+"\ndebitCardPin::"+debitCardPin);
	}
}

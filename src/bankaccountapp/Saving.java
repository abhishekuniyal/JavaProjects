package bankaccountapp;

public class Saving extends Account {

	private int safetyDepositBox;
	private int safetyDepositPin;

	public Saving(String name, String sSN, String accountType, double initialDeposit) {
		super(name, sSN, accountType, initialDeposit);
		System.out.println("Savings Account Created::");
		generateSafetyBox();
	}

	private void generateSafetyBox() {
		safetyDepositBox = (int) (Math.random() * Math.pow(10, 3));
		safetyDepositPin = (int) (Math.random() * Math.pow(10, 4));
	}

	@Override
	protected void setRate() {
		rate = baseRate() - 0.25;
	}

	public void showInfo() {
		System.out.println("************Savings Account Details****************");
		super.showInfo();
		System.out.println("SafetyDepositBox::" + safetyDepositBox + "\nSafetyDepositPin::" + safetyDepositPin);
	}

}

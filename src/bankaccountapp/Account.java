package bankaccountapp;

public abstract class Account implements IBaseRate{

	private String name;
	private String sSN;
	private String accountType;
	private double balance;
	private String accountNumber;

	private static int index = 10000;
	protected double rate;

	public Account(String name, String sSN, String accountType, double initialDeposit) {
		this.name = name;
		this.sSN = sSN;
		this.accountType = accountType;
		this.balance = initialDeposit;

		index++;
		setAccountNumber();
		setRate();
	}

	protected abstract void setRate();

	protected void setAccountNumber() {
		String lastTwoSSN = sSN.substring(sSN.length() - 2, sSN.length());
		int randomNumber = (int) (Math.random() * Math.pow(10, 3));
		this.accountNumber = ("Savings".equals(accountType) ? "1" : "2") + lastTwoSSN + index + randomNumber;
	};

	public void deposit(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) {
		balance -= amount;
	}

	public void transfer(double amount, String transferToAccountNo) {
		balance -= amount;
		System.out.println("Amount $"+ amount+" transferred to "+transferToAccountNo);
	}

	public void showInfo() {
		System.out.println("Account Details:: "
				+ "\nName ::"+name
				+ "\nSSN::"+sSN
				+"\nAccountTye::"+accountType
				+"\nBalance::"+balance
				+"\nAccountNumber::"+accountNumber
				+"\nRate::"+rate);
	}
}

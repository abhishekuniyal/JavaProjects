package emailApp;

import java.util.Scanner;

public class Email {

	private String firstName;
	private String lastName;
	private String deptName;
	private String password;
	private int mailCapacity = 500;
	private String alternateEmail;
	private int DEFAULT_LENGTH = 10;
	private String companyName = "company.com";

	public Email(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;

		// Set the Dept name based on Input
		setDeptName();

		// Set Random Password
		setRandomPassword(DEFAULT_LENGTH);
	}

	public void setDeptName() {
		System.out.println("UserName:" + firstName + "\nEnter Dept:\n0 for Sales\n1 for IT");
		Scanner sc = new Scanner(System.in);
		int deptChoice = sc.nextInt();

		if (deptChoice == 0)
			this.deptName = "sales.";
		else if (deptChoice == 0)
			this.deptName = "it.";
		else
			this.deptName = "";

	}

	public void setRandomPassword(int passLength) {

		String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";

		char[] pass = new char[passLength];
		for (int i = 0; i < passLength; i++) {
			int rand = (int) (Math.random() * passwordSet.length());
			pass[i] = passwordSet.charAt(rand);
		}

		this.password = new String(pass);
	}

	public void setMailCapacity(int mailCapacity) {
		this.mailCapacity = mailCapacity;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public void showDetails() {
		System.out.println("EMail " + firstName + "." + lastName + "@" + deptName + companyName
				+ "created with \n mailboxCapacity " + mailCapacity + " and alternate email " + alternateEmail);
	}

}

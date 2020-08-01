package emailApp;

public class EmailApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Email email = new Email("abhishek", "uniyal");

		email.setMailCapacity(600);
		email.setAlternateEmail("abc@gmail.com");

		email.showDetails();
	}

}

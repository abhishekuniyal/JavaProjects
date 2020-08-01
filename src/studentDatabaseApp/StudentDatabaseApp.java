package studentDatabaseApp;

import java.util.Scanner;

public class StudentDatabaseApp {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("How many users you want to enter?");
		Scanner sc = new Scanner(System.in);
		int noOfUsers = sc.nextInt();
		
		while(noOfUsers>0) {
			
			System.out.println("please enter the student name and year");
			String name = sc.next();
			
			Thread.sleep(1000);
			System.out.println("please enter the student year");
			int year = sc.nextInt();
			
			Student student = new Student(name, String.valueOf(year));
				
			noOfUsers --;
		}

	}
}

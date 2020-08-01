package studentDatabaseApp;

import java.util.List;
import java.util.Scanner;

public class Student {

	private String name;
	private String year;
	private String uniqueId;
	private double coursesFee;
	private List<String> courses;

	static int counter = 1000;

	public Student(String name, String year) {
		this.name = name;
		this.year = year;

		generateUniqueId();

	}

	private void generateUniqueId() {
		this.uniqueId = this.year + counter;
		counter++;

		enrollCourses();
	}

	private void enrollCourses() {
		System.out.println("please enter the courses to enroll");
		int selection;
		do {
			Scanner sc = new Scanner(System.in);
			courses.add(sc.nextLine());
			System.out.println("Press 0 to exit and 1 to enter new course");
			selection = sc.nextInt();
		} while (selection>0);

	}

}

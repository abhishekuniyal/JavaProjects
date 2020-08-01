package bankaccountapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utility {
	
	public static void readCsv() throws FileNotFoundException{
		
		String fileaPath = "/home/abhishek/Abhishek/Workspaces/Projects/JavaProjects/src/bankaccountapp/NewBankAccounts.csv";
		BufferedReader bfr = new BufferedReader(new FileReader(new File(fileaPath)));
		
		List<Account> account = new ArrayList<>();
		List<String[]> li = bfr.lines()
								.map(mapperFunction)
								.collect(Collectors.toList());
		
		for(String[] s : li) {
			if("Savings".equals(s[2])){
				account.add(new Saving(s[0], s[1], s[2], Double.parseDouble(s[3])));
			}else if("Checking".equals(s[2])){
				account.add(new Checking(s[0], s[1], s[2], Double.parseDouble(s[3])));
			}else {
				System.out.println("Please check the account Info");
			}
		}
		
		for(Account acc: account) {
			acc.showInfo();
		}
		
	}

	private static Function<String, String[]> mapperFunction = (input) ->{
		
		String[] strArr = input.split(",");
		return strArr;
	};

}

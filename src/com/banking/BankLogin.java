package com.banking;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class BankLogin {
	public static String mail, pwd;
	public static String now;
	public static LocalDateTime LoginTime;

	public static void Login() throws SQLException {
//		UserServiceLogic.Businesses();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Email ID:");
		mail = sc.next();
		System.out.println("Enter password :");
		pwd = sc.next();

//		BankLoginLogics.loginlogics();
		// call costants one method
	}
}
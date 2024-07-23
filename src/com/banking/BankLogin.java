package com.banking;

import java.sql.SQLException;
import java.time.LocalDateTime;

import com.properties.Constants;

public class BankLogin {
	public static String mail, pwd;
	public static String now;
	public static LocalDateTime LoginTime;

	public static void Login() throws SQLException {
		System.out.println("Enter your Email ID:");
		mail = Constants.sc.next();
		System.out.println("Enter password :");
		pwd = Constants.sc.next();

	}
}
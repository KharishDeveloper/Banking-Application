package com.banking;

import java.sql.SQLException;
import java.time.LocalDate;

import com.properties.Constants;

public class UserServiceCreation {

	public static String userName, mail, mobileNumber, FirstName, LastName, DOB, password;
	public static byte age;
	public static int checkerValueCap = 0;
	public static int checkerValueSmall = 0;

	public static void AccountCreation() throws SQLException {

		System.out.println("WELCOME TO NATIONAL BANKING SERVICES");
		System.out.println("Enter your first name :");
		FirstName = Constants.sc.next();
		System.out.println("Enter your last name :");
		LastName = Constants.sc.next();
		System.out.println("Enter your username :");
		userName = Constants.sc.next();
		System.out.println("Enter your Date-Of-Birth :[YYYY-MM-DD]");
		DOB = Constants.sc.next();
		LocalDate.parse(DOB);
		System.out.println("Enter your mail address :");
		mail = Constants.sc.next();
		System.out.println("Enter your age :");
		age = Constants.sc.nextByte();
		System.out.println("Enter mobile number");
		mobileNumber = Constants.sc.next();
		System.out.println("Enter password :");
		password = Constants.sc.next();

	}

}
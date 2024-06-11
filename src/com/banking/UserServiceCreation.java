package com.banking;

import java.time.LocalDate;
import java.util.Scanner;

public class UserServiceCreation {

	public static String userName, mail, mobileNumber, FirstName, LastName, DOB, password;
	public static byte age;
	public static int checkerValueCap = 0;
	public static int checkerValueSmall = 0;
//	public static int Deposit;

	public static void AccountCreation() {

		Scanner sc = new Scanner(System.in);

		System.out.println("WELCOME TO OUR BANKING SERVICES");
		System.out.println("Enter your first name :");
		FirstName = sc.nextLine();
		System.out.println("Enter your last name :");
		LastName = sc.nextLine();
		System.out.println("Enter your username :");
		userName = sc.nextLine();
//		String FullName = FirstName + LastName;
		System.out.println("Enter your Date-Of-Birth :[YYYY-MM-DD]");
		DOB = sc.next();
		LocalDate.parse(DOB);
		System.out.println("Enter your mail address :");
		mail = sc.next();
		System.out.println("Enter your age :");
		age = sc.nextByte();
		System.out.println("Enter mobile number");
		mobileNumber = sc.next();
		System.out.println("Enter password :");
		password = sc.next();
//		System.out.println("It is mandatory to deposit some amount for security purpose .");
//		Deposit = sc.nextInt();

	}

}
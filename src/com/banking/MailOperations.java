package com.banking;

import java.time.LocalDate;
import java.util.Scanner;

public class MailOperations {
	public static String mail;

	public static void ForgotPassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your mail ID : ");
		mail = sc.next();
		System.out.println("Enter your date of birth : ");
		String DOB = sc.next();
		LocalDate.parse(DOB);
		if (DOB.equals(UserServiceCreation.DOB)) {

			System.out.println("you received an OTP within 1 to 2 minutes");
			System.out.println("Enter your OTP :");
			int OTP = sc.nextInt();
			// write a logic for OTP verification
			// if true
			{
				System.out.println("OTP verification successfully done");
				System.out.println("Enter the new password :");
				String NewPassword = sc.next();
				System.out.println("re enter your new password :");
				String ReEnterPassword = sc.next();
				if (NewPassword.equals(ReEnterPassword)) {
					System.out.println("successfully updated your password");
				} else {
					System.out.println("mismatched your passwords");
				}
			}

		}

	}

	public static void MailVerifyAtCreationTime() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your OTP : ");
		int otp = sc.nextInt();
//		 write a logic for OTP verification
//		if true
//		System.out.println("OTP verification successfully done");
//		successfully verified your mail

	}
}

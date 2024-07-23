package com.banking;

import java.time.LocalDate;

import com.properties.Constants;

public class MailOperations {

	public static void ForgotPassword() {
		System.out.println("Enter your mail ID : ");
		String mail = Constants.sc.next();
		System.out.println("Enter your date of birth : ");
		String DOB = Constants.sc.next();
		LocalDate.parse(DOB);
		if (DOB.equals(UserServiceCreation.DOB)) {

			System.out.println("you received an OTP within 1 to 2 minutes");
			String generation = Constants.generation(4);
			System.out.println("Enter your OTP :");
			String OTP = Constants.sc.next();
			if (generation.equals(OTP)) {
				System.out.println("OTP verification successfully done");
				System.out.println("Enter the new password :");
				String NewPassword = Constants.sc.next();
				System.out.println("re enter your new password :");
				String ConfirmPassword = Constants.sc.next();
				if (NewPassword.equals(ConfirmPassword)) {
					System.out.println("successfully updated your password");
				} else {
					System.out.println("mismatched your passwords");
				}
			}
			System.out.println(OTP);
			// write a logic for OTP verification
			// if true
//			{
//				System.out.println("OTP verification successfully done");
//				System.out.println("Enter the new password :");
//				String NewPassword = Constants.sc.next();
//				System.out.println("re enter your new password :");
//				String ConfirmPassword = Constants.sc.next();
//				if (NewPassword.equals(ConfirmPassword)) {
//					System.out.println("successfully updated your password");
//				} else {
//					System.out.println("mismatched your passwords");
//				}
//			}

		}

	}

	public static boolean MailVerifyAtCreationTime() {
		System.out.println("Generating OTP for verification");
		String generated = Constants.generation(6);
		System.out.println("Enter your OTP : ");
		String otp = Constants.sc.next();
		System.out.println(otp);
		if (generated.equals(otp)) {
			System.out.println("mail verified successfully");
			return true;
		} else {
			System.out.println("mail not verified");
			return false;
		}
//		 write a logic for OTP verification
//		if true
//		System.out.println("OTP verification successfully done");
//		successfully verified your mail

	}
}

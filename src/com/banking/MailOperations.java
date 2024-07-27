package com.banking;

import java.sql.SQLException;
import java.time.LocalDate;

import com.DB.MailOperationsDB;
import com.properties.Constants;
import com.properties.Details;

public class MailOperations {

	public static void ForgotPassword() throws SQLException {
		System.out.println("Enter your mail ID : ");
		String mail = Constants.sc.next();
		System.out.println("Enter your date of birth : ");
		String Date = Constants.sc.next();
		LocalDate.parse(Date);
		boolean forgotPWD = MailOperationsDB.ForgotpasswordDOB(Constants.GetConnection(), mail, Date);

		if (forgotPWD) {
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
				boolean pwdVerify = MailOperationsDB.PasswordRetrieve(Constants.GetConnection(), mail, NewPassword);
				if (NewPassword.equals(ConfirmPassword)) {
					if (pwdVerify) {
						System.out.println("you entered ur old password !!!");
						Details.LoginDetails();
					}
					int passwordValue = MailOperationsDB.UpdatePassword(Constants.GetConnection(), mail, NewPassword);
					if (passwordValue == 1) {

						System.out.println("successfully updated your password");
					}
				} else {
					System.out.println("mismatched your passwords");
					Details.LoginDetails();
				}
			}
			System.out.println(OTP);
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

	}
}

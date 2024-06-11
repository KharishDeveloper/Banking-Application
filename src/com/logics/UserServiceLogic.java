package com.logics;

import java.sql.SQLException;
import java.util.Scanner;

import com.DB.AccountDetailsDB;
import com.DB.UserCreationDB;
import com.banking.BankingServices;
import com.banking.MailOperations;
import com.banking.UserServiceCreation;
import com.excution.ExecutionState;
import com.properties.AccountDetails;
import com.properties.Constants;

public class UserServiceLogic {
	public static void Businesses() throws SQLException {
		UserServiceCreation.AccountCreation();
		Scanner sc = new Scanner(System.in);
		// userName validation
		while (UserServiceCreation.userName.contains(" ")) {
			System.out.println("space exist");
			UserServiceCreation.userName = sc.next();
		}
		UserCreationDB.UserNameChecking(Constants.GetConnection());
		// userName be unique

//		System.out.println(UserServiceCreation.mail.contains("@"));
//		System.out.println("mail logic o/p : "+((UserServiceCreation.mail.contains("@") && ((UserServiceCreation.mail.contains(".com"))
//				|| (UserServiceCreation.mail.contains(".in"))==true))));

		// mail validation
		while ((UserServiceCreation.mail.contains("@") & UserServiceCreation.mail.contains(".com")) == false) {
			System.out.println("invalid mail");
			UserServiceCreation.mail = sc.next();
		}
//		for (int i = 0; i == 1; i++) {
//			if ((UserServiceCreation.mail.contains("@")) && (UserServiceCreation.mail.contains(".com")) == true) {
//				System.out.println("@ phase checking");
//				i = 0;
////			UserServiceCreation.mail=sc.next();
//				System.out.println("exist");
//			}
//			else {
//				System.out.println("enter correct mail");
//				UserServiceCreation.mail = sc.next();
//				i = 0;
//			}
//		}
		// mail and OTP verification
		if ((UserServiceCreation.mail.contains("@") && ((UserServiceCreation.mail.contains(".com"))
				|| (UserServiceCreation.mail.contains(".in"))) == true)) {

			System.out.println("OTP level");
			MailOperations.MailVerifyAtCreationTime();
		}

		// Age validation
		while ((UserServiceCreation.age >= 18 && UserServiceCreation.age <= 60) == false) {
			System.out.println("your age was invalid !!");
			UserServiceCreation.age = sc.nextByte();
		}
//		// MobileNumber validation
		while ((UserServiceCreation.mobileNumber.length() == 10) == false) {
			System.out.println("Enter valid mobile number");
			UserServiceCreation.mobileNumber = sc.next();
		}
////		//password validation
		for (int k = 0; k <= 1; k++) {
			if ((UserServiceCreation.password.length() >= 8 && UserServiceCreation.password.length() <= 20) == true) {
//				System.out.println("length satsified");
				if (UserServiceCreation.password.contains("1") || UserServiceCreation.password.contains("2")
						|| UserServiceCreation.password.contains("3") || UserServiceCreation.password.contains("4")
						|| UserServiceCreation.password.contains("5") || UserServiceCreation.password.contains("6")
						|| UserServiceCreation.password.contains("7") || UserServiceCreation.password.contains("8")
						|| UserServiceCreation.password.contains("9") || UserServiceCreation.password.contains("0")) {
//					System.out.println("contains digit");
					if (UserServiceCreation.password.contains("@") || UserServiceCreation.password.contains("#")
							|| UserServiceCreation.password.contains("$")) {
//						System.out.println("contains special character");
						int vall = 0;
						int[] asciiValue = new int[UserServiceCreation.password.length()];
						for (int i = 0; i < UserServiceCreation.password.length(); i++) {
							char val = UserServiceCreation.password.charAt(i);
//							System.out.println(val + " charat function");
							vall = (int) val;
//							System.out.println("ascii " + vall);
							asciiValue[i] = vall;
						}
//						for (int qw : asciiValue) {
//							System.out.println(qw + "qw");
//						}
						for (int j = 0; j <= asciiValue.length - 1; j++) {
//						System.out.println(asciiValue[j]+"value of "+j);
							if (asciiValue[j] >= 97 && asciiValue[j] <= 122) {
								// 72 >65 && 72 <90
//							System.out.println("consists of small letter(s)");
								UserServiceCreation.checkerValueSmall++;
////							System.out.println(UserService.checkerValueSmall + " password checker small");
							} else {
//							System.out.println("not consists of small letters");

							}
							if (asciiValue[j] >= 65 && asciiValue[j] <= 90) {
//							System.out.println("consists of capital letter(s)");
								UserServiceCreation.checkerValueCap++;
//							System.out.println(UserService.checkerValueCap + "password checker capital");
							} else {
//							System.out.println("not consists of capital letter");
							}

//							System.out.println("capital val" + UserServiceCreation.checkerValueCap);
//							System.out.println(UserServiceCreation.checkerValueCap >= 1);
//							System.out.println("small val" + UserServiceCreation.checkerValueSmall);
//							System.out.println(UserServiceCreation.checkerValueSmall >= 1);
						}
					} else {
						System.out.println("not contains a special character");
						UserServiceCreation.password = sc.next();
						k = 0;
					}

				} else {
					System.out.println("Not contain a digit");
					UserServiceCreation.password = sc.next();
					k = 0;
				}
			} else {
				System.out.println("length not satsified");
				UserServiceCreation.password = sc.next();
				k = 0;
			}
		}

		if ((UserServiceCreation.checkerValueCap >= 1 && UserServiceCreation.checkerValueSmall >= 1) == true) {
			System.out.println("password successfully validated");
			// creation data inserting.
			UserCreationDB.AccountCreation(Constants.GetConnection());
			// insert row for login table
			AccountDetailsDB.loginInsert(Constants.GetConnection());
//			allocating a row for transaction operations
//			TransactionOpsDB.TransactionOpsInsert(Constants.GetConnection());
			// go to login page

//			while((UserServiceCreation.Deposit>=10 && UserServiceCreation.Deposit<=2000)==false) {
//				System.out.println("entered while deposit ");
//			}
//			System.out.println("its safe zone !!!");
//			
//			allocating bank account details
			AccountDetails.ops();

//			BankLoginLogics.loginlogics();

//			System.out.println("cap "+UserServiceCreation.checkerValueCap);
			// cap=1 small=2
		} else {
			System.out.println("unable to validate your password try again later!!!");
			System.out.println("could not find either capital or small letter");
			UserServiceCreation.password = "0";

		}

	}
}
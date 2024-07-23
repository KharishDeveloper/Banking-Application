package com.properties;

import java.sql.SQLException;

import com.logics.BankExit;
import com.logics.BankLoginLogics;
import com.logics.UserServiceLogic;

public class Details {

	public static void LoginDetails() throws SQLException {
//		try {
		System.out.println("Enter ur choice :");
		System.out.println("1. account creation \t 2. login\t 3. Exit");
		int a = Constants.sc.nextInt();
		switch (a) {
		case 1:
			System.out.println("case 1  ");
			UserServiceLogic.Businesses();
//				break;
		case 2:
			System.out.println("case 2  ");
			BankLoginLogics.loginlogics();
			break;
		case 3:
			System.out.println("case 3 ");
			BankExit.exiter();
			break;
		default:
			System.out.println("you have to enter the valid data please choose and enter carefully !!!");
			LoginDetails();
		}
//		}catch(Exception g) {
//			System.out.println("Exception raised !!!");
//			System.out.println("We enter the value in numeric only !!");
//			System.out.println("We want to use application then re-open the application !!!");
//			g.printStackTrace();
//		}
	}
}

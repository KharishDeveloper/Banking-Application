package com.properties;

import java.sql.SQLException;
import java.util.InputMismatchException;

import com.banking.BankingServices;

public class MainMenu {

	public static void LoginMainMenu() throws SQLException {

		try {
			System.out.println(
					"1. Deposit \t 2. Display Account balance \n 3. withdraw money \t 4. Transfer Money \t 5. Transaction history \n 6. log out");
			System.out.println("Enter ur choice :");
			int Takevalue = Constants.sc.nextInt();
			switch (Takevalue) {
			case 1:
				System.out.println("case 1  ");
				BankingServices.Deposit();
				LoginMainMenu();
				break;
			case 2:
				System.out.println("case 2  ");
				BankingServices.DisplayAccountBalance();
				LoginMainMenu();
				break;
			case 3:
				System.out.println(" case 3  ");
				BankingServices.WithdrawMoney();
				LoginMainMenu();
				break;
			case 4:
				System.out.println(" case 4  ");
				BankingServices.TransferMoney();
				LoginMainMenu();
				break;
			case 5:
				System.out.println(" case 5 ");
				BankingServices.TransactionHistory();
				LoginMainMenu();
				break;
			case 6:
				System.out.println("case 6  ");
//			BankExit.exiter();
				BankingServices.Logout();
				break;
//			Details.LoginDetails();
			default:
				System.out.println("Select valid one : ");
				LoginMainMenu();

			}
		} catch (InputMismatchException e) {
			System.out.println("input mismatch exception occured");
		}

	}
}

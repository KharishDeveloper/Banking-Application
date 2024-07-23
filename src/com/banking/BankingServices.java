package com.banking;

import java.sql.SQLException;

import com.DB.BankingServicesDB;
import com.DB.TransactionHistoryDB;
import com.DB.UserCreationDB;
import com.properties.Constants;
import com.properties.Details;

public class BankingServices {

	public static String Pin, TransactionID;
	public static int DepositAmount, Withdrawmoney, TransferAmount;
	public static String AnotherUserName, user, anotherMail;

	public static void SetPin() throws SQLException {

//		System.out.println("your mail :" + BankLogin.mail);

		// getting pin from d b is null or not
		System.out.println("Enter your user id :");

		user = Constants.sc.next();

//		BankingServicesDB.UserNameCheckingAtSetPin(Constants.GetConnection(), user);
		BankingServicesDB.UserNameValidationAtSetPin(Constants.GetConnection(), user);

		// write a query this userName is belongs to this particular user

//		System.out.println("Enter secret pin for creating");
//		BankingServices.Pin = sc.nextInt();
//		BankingServicesDB.SetPinDBLogic();
////		BankingServicesDB.PinNullInfo(Constants.GetConnection());
//		System.out.println("pin is : " + BankingServices.Pin);
		// save it in database

	}

	public static void Deposit() throws SQLException {
		user = UserCreationDB.RetrieveUserID(Constants.GetConnection(), BankLogin.mail); // getting user name based on
																							// mail
//		System.out.println("deposit phase user data : " + user);
		System.out.println("Enter amount to deposit :");
		DepositAmount = Constants.sc.nextInt();
		while ((DepositAmount >= Constants.MinAmountDepsoit && DepositAmount <= Constants.MaxAmountDepsoit) == false) {
			System.out.println("amount will be in between only 100 and 2000");
			System.out.println("please enter amount : ");
			DepositAmount = Constants.sc.nextInt();

		}

		System.out.println("Enter pin : ");
		// write d b query to check the entered pin is equal to the d b pin or not.

		BankingServices.Pin = Constants.sc.next();

		TransactionID = Constants.generation(10);
//		System.out.println(TransactionID + "Deposit ID");
//		System.out.println("pin from deposit " + BankingServices.Pin);
//		System.out.println("deposit db query");
//		BankingServicesDB.PinChecking(Constants.GetConnection(), BankingServices.Pin); // checks pin is valid or not
		boolean pinValidation = BankingServicesDB.PinValidation(Constants.GetConnection(), BankingServices.Pin);
		if (pinValidation == true) {
			BankingServicesDB.SavingDeposit(Constants.GetConnection());
		}
//		generating Transaction-ID

//		idGenerator = Constants.generation(10);

		// save it in database
//		BankingServicesDB.SavingDeposit(Constants.GetConnection());

	}

	public static void DisplayAccountBalance() throws SQLException {
//		System.out.println("displaying account balance");
		System.out.println("Enter your pin : ");
		BankingServices.Pin = Constants.sc.next();
		// checking pin
//		BankingServicesDB.PinChecking(Constants.GetConnection(), BankingServices.Pin);
		boolean pinValidation = BankingServicesDB.PinValidation(Constants.GetConnection(), BankingServices.Pin);
		if (pinValidation == true) {

			BankingServicesDB.DisplayingAccountBalanceDB(Constants.GetConnection(), BankLogin.mail);
		}
//		System.out.println(BankingServices.Pin);
	}

	public static void Logout() throws SQLException {
//		boolean isLoginUser = true;
//		int a = 0;
//		while (a != 0) {
//			try {
//				MainMenu.LoginMainMenu();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			isLoginUser = false;
//		}
		System.out.println("successfully logout the user !!!");
		Details.LoginDetails();
	}
//	retrieving data from database -> pin, account balance

	public static void WithdrawMoney() throws SQLException {
//		System.out.println("withdrawing money");
		System.out.println("enter amount to withdraw :");
		Withdrawmoney = Constants.sc.nextInt();

		while ((Withdrawmoney >= Constants.MinAmountDepsoit && Withdrawmoney <= Constants.MaxAmountDepsoit) == false) {
			System.out.println("amount will be in between only " + Constants.MinAmountDepsoit + "and"
					+ Constants.MaxAmountDepsoit);
			System.out.println("please enter amount : ");
			Withdrawmoney = Constants.sc.nextInt();

		}

		System.out.println("Enter pin : ");
		BankingServices.Pin = Constants.sc.next();
//		int MoneyChecking = BankingServicesDB.WithdrawMoneyChecking(Constants.GetConnection(), BankingServices.Pin);
		int MoneyChecking = BankingServicesDB.UserPinValidation(Constants.GetConnection(), BankingServices.Pin);
//		System.out.println("your balance at withdraw money state before performing withdraw:" + MoneyChecking);
//		TransactionID = Constants.generation(10);//10 >5
//		System.out.println(TransactionID + "withdraw ID");
		if (MoneyChecking > Withdrawmoney ) {
//			&& MoneyChecking > -1
			System.out.println("proceed with withdraw !");
			BankingServicesDB.WithDrawMoney(Constants.GetConnection());
		} else {
			System.out.println("not maintaining enough balance !");
		}
//		Generate Transaction ID
//		save it in a data base
	}

	public static void TransferMoney() throws SQLException {

//		System.out.println("1. Bank transfer using a/c number \n 2. money transfer using userID ");

//		BankingServicesDB.gettingUserName(Constants.GetConnection(), BankLogin.mail);
		System.out.println("Enter the user ID to transfer money : ");
		AnotherUserName = Constants.sc.next();// taking user name to transfer
		// checks existed or not
//		BankingServicesDB.TransferUserChecking(Constants.GetConnection());
//		BankingServicesDB.GettingDefaultUserName(Constants.GetConnection());
		BankingServicesDB.GettingSelfUserName(Constants.GetConnection());

		/*
		 * get the all userNames in d b if exists deposit it in that account and reduce
		 * the amount in this account if not print not exists then enter again deposit
		 * it in that account and reduce the amount in this account generate t-id save
		 * in d b
		 * 
		 */
//		Generate Transaction ID
//		save it in a data base

	}

	public static void TransactionHistory() throws SQLException {

		TransactionHistoryDB.GetUserDetails(Constants.GetConnection());

		System.out.println("----------------------------");
		TransactionHistoryDB.GetTransactionHistory(Constants.GetConnection(), 2);

	}

}
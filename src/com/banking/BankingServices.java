package com.banking;

import java.sql.SQLException;
import java.util.Scanner;

import com.DB.BankingServicesDB;
import com.DB.UserCreationDB;
import com.logics.BankExit;
import com.logics.UserServiceLogic;
import com.properties.Constants;
import com.properties.Details;
import com.properties.MainMenu;

public class BankingServices {

	public static Scanner sc = new Scanner(System.in);
	public static String Pin, TransactionID;
	public static int DepositAmount, Withdrawmoney, TransferAmount;
	public static String AnotherUserName, user, anotherMail;
//	user1;

//	public static String idGenerator;

	public static void SetPin() throws SQLException {

		System.out.println("your mail :" + BankLogin.mail);

		// getting pin from d b is null or not
		System.out.println("Enter your user id :");

		user = sc.next();

		BankingServicesDB.UserNameCheckingAtSetPin(Constants.GetConnection(), user);

		// write a query this userName is belongs to this particular user

//		System.out.println("Enter secret pin for creating");
//		BankingServices.Pin = sc.nextInt();
//		BankingServicesDB.SetPinDBLogic();
////		BankingServicesDB.PinNullInfo(Constants.GetConnection());
//		System.out.println("pin is : " + BankingServices.Pin);
		// save it in database

	}

//	public static void AllCalls() throws SQLException {
//		BankingServices bk = new BankingServices();
//		System.out.println("1. deposit money \t 2. display account balance \t 3. withdraw money");
//		System.out.println("4.Transfer money \t 5. display recent transactions \t 6. logout");
//		System.out.println("Enter your opionion :");
//		int choice = sc.nextInt();
//
//		switch (choice) {
//		case 1:
//			Deposit();
//			break;
//		case 2:
//			DisplayAccountBalance();
//			break;
//		case 3:
//			bk.WithdrawMoney();
//			break;
//		case 4:
//			bk.TransferMoney();
//			break;
//		case 5:
//			bk.TransactionHistory();
//			break;
//		case 6:
////			System.out.println("application exit step -> logout");
//			BankExit.exiter();
//			break;
//		}
//	}

	public static void Deposit() throws SQLException {
//		System.out.println("select one:");
//		SetPin();
		user = UserCreationDB.RetrieveUserID(Constants.GetConnection(), BankLogin.mail); // getting user name based on
																							// mail
		System.out.println("deposit phase user data : " + user);
		System.out.println("Enter amount to deposit :");
		DepositAmount = sc.nextInt();
		while ((DepositAmount >= Constants.MinAmountDepsoit && DepositAmount <= Constants.MaxAmountDepsoit) == false) {
			System.out.println("amount will be in between 100 and 2000");
			System.out.println("please enter amount : ");
			DepositAmount = sc.nextInt();

		}

		System.out.println("Enter pin : ");
		// write d b query to check the entered pin is equal to the d b pin or not.

		BankingServices.Pin = sc.next();

		TransactionID = Constants.generation(10);
		System.out.println(TransactionID+"Deposit ID");
		System.out.println("pin from deposit " + BankingServices.Pin);
		System.out.println("deposit db query");
		BankingServicesDB.PinChecking(Constants.GetConnection(), BankingServices.Pin); // checks pin is valid or not
		BankingServicesDB.SavingDeposit(Constants.GetConnection());

//		generating Transaction-ID

//		idGenerator = Constants.generation(10);

		// save it in database
//		BankingServicesDB.SavingDeposit(Constants.GetConnection());

	}

	public static void DisplayAccountBalance() throws SQLException {
		System.out.println("displaying account balance");
		System.out.println("Enter your pin : ");
		BankingServices.Pin = sc.next();
		//checking pin
		BankingServicesDB.PinChecking(Constants.GetConnection(), BankingServices.Pin);
		
		System.out.println(BankingServices.Pin);
		BankingServicesDB.DisplayingAccountBalanceDB(Constants.GetConnection(), BankLogin.mail);
	}

	
	
	public static void Logout() throws SQLException {
		boolean v=true;
		int a=0;
		while(a!=0) {
			try {
				MainMenu.LoginMainMenu();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			v=false;
		}
		Details.LoginDetails();
	}
//	retrieving data from database -> pin, account balance

	public static void WithdrawMoney() throws SQLException {
		System.out.println("withdrawing money");
		System.out.println("enter amount to withdraw :");
		Withdrawmoney = sc.nextInt();
		
		while ((Withdrawmoney >= Constants.MinAmountDepsoit && Withdrawmoney <= Constants.MaxAmountDepsoit) == false) {
			System.out.println("amount will be in between 100 and 3000");
			System.out.println("please enter amount : ");
			Withdrawmoney = sc.nextInt();

		}
		
		
		System.out.println("Enter pin : ");
		BankingServices.Pin=Constants.sc.next();
		System.out.println("HERE ");
		int MoneyChecking = BankingServicesDB.WithdrawMoneyChecking(Constants.GetConnection(),BankingServices.Pin);
		System.out.println("your balance at withdraw money state before performing withdraw:" + MoneyChecking);
		TransactionID = Constants.generation(10);
		System.out.println(TransactionID+"withdraw ID");
		if (MoneyChecking > Withdrawmoney) {
			System.out.println("proceed with withdraw !");
			BankingServicesDB.WithDrawMoney(Constants.GetConnection());
		} else {
			System.out.println("not maintaining enough balance !");
		}
//		Generate Transaction ID
//		save it in a data base
	}

	public static void TransferMoney() throws SQLException{
		
//		System.out.println("1. Bank transfer using a/c number \n 2. money transfer using userID ");
		
		System.out.println("Enter the user ID to transfer money : ");
		AnotherUserName=sc.next();// taking user name to transfer
		//checks existed or not
//		BankingServicesDB.TransferUserChecking(Constants.GetConnection());
		BankingServicesDB.GettingDefaultUserName(Constants.GetConnection());
		
		
		
		
		

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

	public void TransactionHistory() {
		System.out.println("Enter your username :");
		user = sc.next();
		/*
		 * fields : id, user name, transaction id, mail ID
		 * 
		 * use result set get the all transaction from db based on userName and any
		 * other field if both matches then only get the data else get the error msg
		 * print the transactions based on limit
		 */
		System.out.println("not found any transaction");
	}

}
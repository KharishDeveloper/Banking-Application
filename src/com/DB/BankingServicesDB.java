package com.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.banking.BankLogin;
import com.banking.BankingServices;
import com.properties.Constants;
import com.properties.MainMenu;


public class BankingServicesDB {

	public static void SavePinDB() throws SQLException {
		Connection con = Constants.GetConnection();
		String sql = "update accountdetails set pin=? where mail=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, BankingServices.Pin);
		st.setString(2, BankLogin.mail);
		st.execute();
		System.out.println("done !");

	}

	public static void PinExistInfo(Connection con) throws SQLException {
		String sql1 = "select pin,mail from accountdetails where (pin is not null and mail=?);";
		PreparedStatement stm = con.prepareStatement(sql1);
		stm.setString(1, BankLogin.mail);
		ResultSet b = stm.executeQuery();
		if (b.next()) {
			System.out.println("pin data : " + b.getString(1));
			MainMenu.LoginMainMenu();
		} else {
			System.out.println("pin data : NO");
			BankingServices.SetPin();
			MainMenu.LoginMainMenu();
		}
	}

	public static String PinValidation(Connection con, String pin1) throws SQLException {

		String sql2 = "select Pin from accountdetails where mail=?;";
		PreparedStatement stmt = con.prepareStatement(sql2);
		stmt.setString(1, BankLogin.mail);
		ResultSet b = stmt.executeQuery();
		while (b.next()) {
			if (b.getString(1).equals(pin1)) {
				System.out.print("Pin validating and ");
				return "successfull";
			}
		}
		return "Pin mismatch";

	}

	public static int SavingDeposit(Connection con) throws SQLException {
		String sql2 = "update accountdetails set BankBalance=BankBalance+? where Mail=?;";
		PreparedStatement stm = con.prepareStatement(sql2);
		stm.setInt(1, BankingServices.DepositAmount);
		stm.setString(2, BankLogin.mail);
		int i = stm.executeUpdate();
		System.out.println("updated confirmed rows : " + i);

		String sql3 = "insert into transactionhistory(mail,userId,transactionID,amount,credit,typeoftransaction,status,Date_Time) values(?,?,?,?,?,?,?,now());";
		PreparedStatement stmt = con.prepareStatement(sql3);
		stmt.setString(1, BankLogin.mail);
		stmt.setString(2, BankingServices.user);
		stmt.setString(3, BankingServices.TransactionID);
		System.out.println(BankingServices.TransactionID + " T-ID at BankingServicesDB for trans-history");
		stmt.setInt(4, BankingServices.DepositAmount);
		stmt.setString(5, "YES");
		stmt.setString(6, "DEPOSIT");
		stmt.setString(7, "SUCCESS");
		stmt.execute();

		return 0;
	}

	public static void UserNameValidationAtSetPin(Connection con, String users) throws SQLException {
		String sql = "select username from usercreation where mail=?;";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, BankLogin.mail);
		ResultSet set = stm.executeQuery();
		while (set.next()) {
			if (set.getString(1).equals(users)) {
				System.out.println("user name validation passed ");
				System.out.println("so you can proceed !");
				PinSaveDB();
			} else {
				System.out.println("user name mis match !");
				break;
			}
		}
	}

	public static void PinSaveDB() throws SQLException {
		System.out.println("Enter secret pin : ");
		BankingServices.Pin = Constants.sc.next();
		System.out.println(BankingServices.Pin);
		System.out.println(BankingServices.Pin.length());
		if ((BankingServices.Pin.length() == 4) && (BankingServices.Pin.contains("0")) == false) {
			BankingServicesDB.SavePinDB();
		} else {
			System.out.println("unable to set the pin because you entering more than 4 ");
			System.out.println("pin is : " + BankingServices.Pin);
		}

	}

	public static void DisplayingAccountBalanceDB(Connection con, String mail) throws SQLException {
		String sql = "select BankBalance,Pin from accountdetails where Mail=?;";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, BankLogin.mail);
		ResultSet query = stm.executeQuery();

		if (query.next()) {
			if (query.getString(2).equals(BankingServices.Pin)) {
				System.out.print("retrieving balance and ");
				System.out.println("your account is :" + query.getString(1));

			}
		}

	}

	public static int WithDrawMoney(Connection con) throws SQLException {
		String sql1 = "update accountdetails set BankBalance=BankBalance-? where Mail=?;";
		PreparedStatement stm = con.prepareStatement(sql1);
		stm.setInt(1, BankingServices.Withdrawmoney);
		stm.setString(2, BankLogin.mail);
		int i = stm.executeUpdate();
		System.out.println("updated confirmed rows : " + i);

		String sql2 = "insert into transactionhistory(mail,userID,transactionID,amount,Debit,typeoftransaction,status,Date_Time) values(?,?,?,?,?,?,?,now())";
		PreparedStatement sttmt = con.prepareStatement(sql2);
		sttmt.setString(1, BankLogin.mail);
		sttmt.setString(2, BankingServices.user);
		sttmt.setString(3, BankingServices.TransactionID);
		sttmt.setInt(4, BankingServices.Withdrawmoney);
		sttmt.setString(5, "YES");
		sttmt.setString(6, "WITHDRAW");
		sttmt.setString(7, "SUCCESS");
		boolean b = sttmt.execute();
		System.out.println(b);

		return 0;
	}

	public static int BankBalanceCheck(Connection con) throws SQLException {
		String sql = "select Bankbalance from accountdetails where mail=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, BankLogin.mail);
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			return set.getInt(1);

		}
		return -1;
	}

	public static void UserNameTransferMoney(Connection c) throws SQLException {
		String sql = "select UserName from usercreation where UserName=?;";
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setString(1, BankingServices.AnotherUserName);
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			System.out.println("Enter amount to transfer : ");
			BankingServices.TransferAmount = Constants.sc.nextInt();
			System.out.println("Enter pin : ");
			BankingServices.Pin = Constants.sc.next();
			int maintainAmount = BankBalanceCheck(c);
			if (maintainAmount > BankingServices.TransferAmount) {
				RetrieveMail(c);
			} else {
				System.out.println("insufficient amount !!!");
			}

		} else {
			System.out.println("entered user name is not existed ");
		}
	}

	public static void GettingSelfUserName(Connection c) throws SQLException {
		String sql = "select UserName from usercreation where Mail=?;";
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setString(1, BankLogin.mail);
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			BankingServices.user = set.getString(1);
			System.out.println("getting data of userid");
			while (set.getString(1).equals(BankingServices.AnotherUserName)) {
				System.out.println("Entered your self username");
				System.out.println("unable to transfer to your self !!!");
				System.out.println("please enter another username to transfer :");
				BankingServices.AnotherUserName = Constants.sc.next();
			}
			UserNameTransferMoney(c);
		}
	}

	public static void RetrieveMail(Connection c) throws SQLException {
		String sql = "select Mail from usercreation where UserName=?;";
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, BankingServices.AnotherUserName);
		ResultSet stm = st.executeQuery();
		if (stm.next()) {
			BankingServices.anotherMail = stm.getString(1);
			TransferMoney(c);
		}

	}

	public static void TransferMoney(Connection c) throws SQLException {
		String sql1 = "update accountdetails set BankBalance=BankBalance-? where Mail=?;";
		PreparedStatement st = c.prepareStatement(sql1);
		st.setInt(1, BankingServices.TransferAmount);
		st.setString(2, BankLogin.mail);
		int i = st.executeUpdate();
		System.out.println("updated1 : " + i);

		String sql2 = "update accountdetails set BankBalance=BankBalance+? where Mail=?;";
		PreparedStatement stm3 = c.prepareStatement(sql2);
		stm3.setInt(1, BankingServices.TransferAmount);
		stm3.setString(2, BankingServices.anotherMail);
		int j = stm3.executeUpdate();
		System.out.println("updated2 : " + j);

		TransferDataSaveTransactionHistoryAtSenderSide(c, BankingServices.TransactionID);
		TransferDataSaveTransactionHistoryAtReceiverSide(c, BankingServices.TransactionID);

	}

	public static void TransferDataSaveTransactionHistoryAtSenderSide(Connection con, String TransactionID)
			throws SQLException {
		String sql = "insert into transactionhistory(mail,userId,transactionID,amount,Debit,typeoftransaction,status,Date_Time,AnotherUseridTransfer) values(?,?,?,?,?,?,?,now(),?);";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, BankLogin.mail);
		stmt.setString(2, BankingServices.user);
		stmt.setString(3, TransactionID);
		stmt.setInt(4, BankingServices.TransferAmount);
		stmt.setString(5, "YES");
		stmt.setString(6, "TRANSFERED TO " + BankingServices.AnotherUserName);
		stmt.setString(7, "SUCCESS");
		stmt.setString(8, BankingServices.AnotherUserName);
		stmt.execute();
	}

	public static void TransferDataSaveTransactionHistoryAtReceiverSide(Connection con, String TransactionID)
			throws SQLException {
		String sql = "insert into transactionhistory(mail,userId,transactionID,amount,credit,typeoftransaction,status,Date_Time,AnotherUseridTransfer) values(?,?,?,?,?,?,?,now(),?);";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, BankingServices.anotherMail);
		stmt.setString(2, BankingServices.AnotherUserName);
		stmt.setString(3, TransactionID);
		stmt.setInt(4, BankingServices.TransferAmount);
		stmt.setString(5, "YES");
		stmt.setString(6, "RECEIVED FROM " + BankingServices.user);
		stmt.setString(7, "SUCCESS");
		stmt.setString(8, BankingServices.user);
		stmt.execute();
	}

}
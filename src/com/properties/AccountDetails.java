package com.properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.DB.AccountDetailsDB;
import com.banking.BankLogin;
import com.logics.BankLoginLogics;

public class AccountDetails {

	public static final String IFSCode = "HBSVJD5201";

	public static String BankBranch, AccountType;
	public static LocalDate AccountOpeningdate;
	public static String AccountNumber, CIF;

	public static long between;

	public static void ops() throws SQLException {

		AccountNumber = Constants.generation(9);

		System.out.println("Account number : " + (AccountNumber));
		BankBranch = "TENALI";
		System.out.println("Branch : " + BankBranch);
		AccountType = "Savings Account";
		System.out.println("Account type : " + AccountType);
		AccountOpeningdate = LocalDate.now();
		System.out.println("Account opened date : " + AccountOpeningdate);
		CIF = Constants.generation(9);
		System.out.println("customer information file : " + CIF);
//		AccountDetailsDB.AccountDetails(Constants.GetConnection());
		AccountDetailsDB.SavingAccountDetails(Constants.GetConnection());

	}

	// unblock time
	public static long failed() throws SQLException {

		String LoginTime = "select logintime from login where Mail=?;";
		Connection c = Constants.GetConnection();
		PreparedStatement stmtt = c.prepareStatement(LoginTime);
		stmtt.setString(1, BankLogin.mail);
		ResultSet set = stmtt.executeQuery();
		if (set.next()) {
			String time = set.getString(1);
			String replace = time.replace(' ', 'T');
			System.out.println(replace);
			LocalDateTime time2 = LocalDateTime.parse(replace);
			LocalDateTime LoginTime1 = LocalDateTime.now().withNano(0);
			between = ChronoUnit.HOURS.between(time2, LoginTime1);
			System.out.println(between);
			if (Constants.LocalFailedCount == 0) {
				if (between >= 24) {
					System.out.println("welcome back carefully enter");
					Constants.LocalFailedCount = 3;
					System.out.println(Constants.LocalFailedCount);
					Connection conn = Constants.GetConnection();
					PreparedStatement stt = conn.prepareStatement(
							"update login set failedcount=?,unblocktime=?,accountstatus=? where mail=?;");
					stt.setInt(1, Constants.LocalFailedCount);
					stt.setLong(2, between);
					stt.setString(3, "UNBLOCK");
					stt.setString(4, BankLogin.mail);
					stt.executeUpdate();
					BankLoginLogics.loginlogics();
				}

				else {
					System.out.println(
							"please wait " + (24 - between) + " hours then only you will allow to login into this");

//					String sql = "update login set failedcount=?,unblocktime=?,accountstatus=? where Mail=?;";

					AccountDetailsDB.LoginStatusUpdation(c);

					// update the values in DB unblock time and unblock status
					AccountDetailsDB.LoginStatusUpdation(Constants.GetConnection());
				}
			} else {
				System.out.println("failedcount is more");
			}
		}
		return 0;
	}
}

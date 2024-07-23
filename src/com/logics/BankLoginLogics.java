package com.logics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DB.AccountDetailsDB;
import com.DB.BankingServicesDB;
import com.banking.BankLogin;
import com.banking.MailOperations;
import com.properties.AccountDetails;
import com.properties.Constants;

public class BankLoginLogics {

	public static void loginlogics() throws SQLException {
		BankLogin.Login();

		
//		System.out.println("F C 1 code based : " + Constants.LocalFailedCount);

//		{
		Connection con = Constants.GetConnection();
		String sql = "select failedcount from login where mail=?;";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, BankLogin.mail);
		ResultSet set = st.executeQuery();
		if (set.next()) {
			System.out.println(set.getInt(1));
		}
		Constants.LocalFailedCount = set.getInt(1);
//		System.out.println("FC2 DB based is : " + Constants.LocalFailedCount);
//		}
		// verifying mail and password
		if (Constants.LocalFailedCount <= 3 && Constants.LocalFailedCount >= 1) {
			Connection connection = Constants.GetConnection();
			String sql2 = "select Mail, Password from usercreation where Mail=?;";
			PreparedStatement stm = connection.prepareStatement(sql2);
			stm.setString(1, BankLogin.mail);
			ResultSet query = stm.executeQuery();
			if (query.next()) {
//				System.out.println("mail :" + query.getString(1));
//				System.out.println("password :" + query.getString(2));

				if (query.getString(1).equals(BankLogin.mail) == true) {

//					System.out.println("mail matched ");
//					System.out.println("password phase");
					if ((query.getString(2).equals(BankLogin.pwd)) == true) {
//						System.out.println("password matched ");
//						BankLogin.LoginTime = LocalDateTime.now().withNano(0);
//						System.out.println(BankLogin.LoginTime);
//						AccountDetailsDB.loginInsert(Constants.GetConnection());
//						AccountDetailsDB.LastLogin(Constants.GetConnection());
						AccountDetailsDB.LastLoginUpdate(con);
//						System.out.println("pin null started");
//						BankingServicesDB.PinNullInfo(Constants.GetConnection());
						BankingServicesDB.PinExistInfo(Constants.GetConnection());

//						BankingServicesDB.SetPinDBLogic();

//						System.out.println("count final :"+Constants.FailedCount);

					} else {

						System.out.println("password not matched");
						Constants.LocalFailedCount--;
//						System.out.println("count password else block" + Constants.LocalFailedCount);
//						System.out.println("Enter your password again : ");
//						BankLogin.pwd = sc.next();
						Connection conn = Constants.GetConnection();
						PreparedStatement stmtq = conn.prepareStatement("update login set failedcount=? where mail=?");
						stmtq.setInt(1, Constants.LocalFailedCount);
						System.out.println("fc : " + Constants.LocalFailedCount);
						stmtq.setString(2, BankLogin.mail);
						stmtq.executeUpdate();
						System.out.println("Enter value :");
						System.out.println("1. login \t 2. reset the password");
						int value = Constants.sc.nextInt();
						switch (value) {
						case 1:
							BankLoginLogics.loginlogics();
							break;
						case 2:
							MailOperations.ForgotPassword();
							break;
						}

					}
				} else {
					System.out.println("mail does not exist");
					System.out.println("Enter your mail ID : ");
					BankLogin.mail = Constants.sc.next();
					Constants.LocalFailedCount--;
					System.out.println("count" + Constants.LocalFailedCount);
//					i = 0;
				}
//
			}

			else {
				System.out.println("unable to process");
			}
		} else if (Constants.LocalFailedCount == 0) {
			AccountDetails.failed();
		}

	}

}
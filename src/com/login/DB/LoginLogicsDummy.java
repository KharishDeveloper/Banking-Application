//package com.login.DB;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//import com.banking.BankLogin;
//import com.properties.AccountDetails;
//import com.properties.Constants;
//
//public class LoginLogicsDummy {
//
//	public void dummyMethod() throws SQLException {
//
//		BankLogin.Login();
//		Scanner sc = new Scanner(System.in);
//		// verifying mail and password
////		for (int i = 0; i <= 1; i++) {
//		System.out.println("default count " + Constants.FailedCount);
//		if (Constants.FailedCount <= 3 && Constants.FailedCount >= 1) {
//
//			Connection connection = Constants.GetConnection();
//			String sql2 = "select Mail, Password from usercreation where Mail=?;";
//			PreparedStatement stm = connection.prepareStatement(sql2);
//			stm.setString(1, BankLogin.mail);
//			ResultSet query = stm.executeQuery();
//			if (query.next()) {
//				// while
//				System.out.println("mail :" + query.getString(1));
//				System.out.println("password :" + query.getString(2));
////				}
//
//				if (query.getString(1).equals(BankLogin.mail) == true) {
//
//					System.out.println("mail matched ");
//					System.out.println("password phase");
//					if ((query.getString(2).equals(BankLogin.pwd)) == true) {
//						System.out.println("password matched ");
////						BankLogin.LoginTime = LocalDateTime.now().withNano(0);
////						System.out.println(BankLogin.LoginTime);
////						AccountDetailsDB.loginInsert(Constants.GetConnection());
////						BankLogin.LastLogin();
//						LoginDb.LastLogin();
////						System.out.println("count final :"+Constants.FailedCount);
//
//					} else {
//						System.out.println("password not matched");
//						Constants.FailedCount--;
//						System.out.println("count " + Constants.FailedCount);
//						System.out.println("Enter your password again : ");
//						BankLogin.pwd = sc.next();
////						i = 0;
//
//					}
//				} else {
//					System.out.println("mail does not matched");
//					System.out.println("Enter your mail ID : ");
//					BankLogin.mail = sc.next();
//					Constants.FailedCount--;
//					System.out.println("count" + Constants.FailedCount);
////					i = 0;
//				}
////
//			}
//
//			else {
//				System.out.println("unable to process");
//			}
//		}
//		if (Constants.FailedCount == 0) {
//			AccountDetails.failed();
//		}
//
//	}
//}

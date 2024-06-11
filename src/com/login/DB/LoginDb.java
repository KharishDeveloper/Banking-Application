//package com.login.DB;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import com.banking.BankLogin;
//import com.mysql.cj.jdbc.result.ResultSetMetaData;
//import com.properties.Constants;
//
//public class LoginDb {
//	public static void LastLogin() throws SQLException {
//		System.out.println("new methpdd!!!");
//		Connection con = Constants.GetConnection();
//		String sql = "update login set activestate=? where Mail=?;";
//		PreparedStatement stt1 = con.prepareStatement(sql);
//		stt1.setString(1, "login");
//		stt1.setString(2, BankLogin.mail);
//		int up = stt1.executeUpdate();
//		System.out.println("active state updation : "+up);
//		String sql1 = "select logintime,lastlogin,activestate from login where Mail=?;";
//		PreparedStatement stmt = con.prepareStatement(sql1);
//		stmt.setString(1, BankLogin.mail);
//		ResultSet query = stmt.executeQuery();
//		if (query.next()) {
//			ResultSetMetaData md = (ResultSetMetaData) query.getMetaData();
//			System.out.println("1 col name :"+md.getColumnName(1));
//			System.out.println("in 1 " + query.getString(1));
//			System.out.println("2 col name :"+md.getColumnName(2));
//			System.out.println("in 2 " + query.getString(2));//last login
//			System.out.println("3 col name :"+md.getColumnName(3));
//			System.out.println("in 3 " + query.getString(3));
//
//			if (query.getString(3).equals("login")) {
//				System.out.println("your last login :" + query.getString(1));
//				System.out.println("value addressed");
//				String url = "update login set logintime=now(),lastlogin=?,failedcount=? where Mail=?;";
//				PreparedStatement stt = con.prepareStatement(url);
//				stt.setString(1, query.getString(1));
//				stt.setInt(2, Constants.FailedCount);
//				stt.setString(3, BankLogin.mail);
//				System.out.println("updated");
//				int update = stt.executeUpdate();
//				System.out.println(update);
//			} else {
//
//				System.out.println("nothing");
//			}
//
//		} else {
//			System.out.println("no data");
//		}
//	}
//}
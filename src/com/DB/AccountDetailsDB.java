package com.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.banking.BankLogin;
import com.banking.UserServiceCreation;
import com.properties.AccountDetails;
import com.properties.Constants;

public class AccountDetailsDB {

	public static Connection SavingAccountDetails(Connection con) throws SQLException {
		String sql = "insert into accountdetails(Mail,AccountNumber,Branch, Openeddate,CIF) values(?,?,?,curdate(),?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, UserServiceCreation.mail);
		st.setString(2, AccountDetails.AccountNumber);
		st.setString(3, AccountDetails.BankBranch);
		st.setString(4, AccountDetails.CIF);
//		st.setInt(5, UserServiceCreation.Deposit);
		boolean b = st.execute();
		System.out.println(b);
		return con;
	}

	public static void InsertLoginDetails(Connection con) throws SQLException {
		String sql = "insert into login(Mail,logintime) values(?,now());";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, UserServiceCreation.mail);
		boolean b = st.execute();
		System.out.println(b);
	}

	public static void LoginStatusUpdation(Connection con) throws SQLException {
		String sql1 = "update login set accountstatus=?,unblocktime=?,failedcount=? where Mail=?;";
		PreparedStatement stt = con.prepareStatement(sql1);
		stt.setString(1, "BLOCK");
		stt.setLong(2, AccountDetails.between);
		stt.setInt(3, Constants.LocalFailedCount);
		stt.setString(4, BankLogin.mail);
		stt.executeUpdate();
	}

	public static void LastLoginUpdate(Connection con) throws SQLException {
//		Connection con = Constants.GetConnection();
		String sql = "update login set activestate=? where Mail=?;";
		PreparedStatement stt1 = con.prepareStatement(sql);
		stt1.setString(1, "Login");
//		stt1.setInt(2, 3);
		stt1.setString(2, BankLogin.mail);
		int up = stt1.executeUpdate();
		System.out.println("active state updation : " + up);
		String sql1 = "select logintime,lastlogin,activestate from login where Mail=?;";
		PreparedStatement stmt = con.prepareStatement(sql1);
		stmt.setString(1, BankLogin.mail);
		ResultSet query = stmt.executeQuery();
		if (query.next()) {
//			ResultSetMetaData md = (ResultSetMetaData) query.getMetaData();
//			System.out.println("1 col name :" + md.getColumnName(1));
//			System.out.println("in 1 " + query.getString(1));
//			System.out.println("2 col name :" + md.getColumnName(2));
//			System.out.println("in 2 " + query.getString(2));// last login
//			System.out.println("3 col name :" + md.getColumnName(3));
//			System.out.println("in 3 " + query.getString(3));

			if (query.getString(3).equals("login")) {
				System.out.println("your last login :" + query.getString(1));
//				System.out.println("value addressed");
				String url = "update login set logintime=now(),lastlogin=?,failedcount=? where Mail=?;";
				PreparedStatement stt = con.prepareStatement(url);
				stt.setString(1, query.getString(1));
				stt.setInt(2, Constants.LocalFailedCount);
				stt.setString(3, BankLogin.mail);
				System.out.println("updated");
				int update = stt.executeUpdate();
				System.out.println(update);
			} else {
				System.out.println("nothing");
			}

		} else {
			System.out.println("no data");
		}
	}
	
	
	
	public static int Logout(Connection con) throws SQLException{
		String sql="update login set activestate=? where Mail=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "Logout");
		stmt.setString(2, BankLogin.mail);
		int set = stmt.executeUpdate();
		if(set==1) {
			return 1;
		}
		return -1;
		
	}

	// userName unique logic
//	String sql = "select username from users;";
	/*
	 * if DB user name==entered userName then enter any other userName and checks
	 * again
	 * 
	 */
}

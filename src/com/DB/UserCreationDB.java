package com.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.banking.UserServiceCreation;
import com.properties.Constants;

public class UserCreationDB {

	public static Connection AccountCreation(Connection c) throws SQLException {
		String sql = "insert into usercreation(FirstName,LastName,FUllName,UserName,DOB,mail,age,MobilleNumber, password) values(?,?,?,?,?,?,?,?,?);";
		PreparedStatement statement2 = c.prepareStatement(sql);
		statement2.setString(1, UserServiceCreation.FirstName);
		System.out.println("account creation db");
		statement2.setString(2, UserServiceCreation.LastName);
		statement2.setString(3, (UserServiceCreation.FirstName + UserServiceCreation.LastName));
		statement2.setString(4, UserServiceCreation.userName);
		statement2.setString(5, UserServiceCreation.DOB);
		statement2.setString(6, UserServiceCreation.mail);
		statement2.setInt(7, UserServiceCreation.age);
		statement2.setString(8, UserServiceCreation.mobileNumber);
		statement2.setString(9, UserServiceCreation.password);
		boolean b = statement2.execute();
		System.out.println(b);
		return c;

	}

	public static void UserNameDataExist(Connection con) throws SQLException {
		String sql = "select UserName from usercreation where UserName=?;";
		PreparedStatement stm1 = con.prepareStatement(sql);
		stm1.setString(1, UserServiceCreation.userName);
		ResultSet query = stm1.executeQuery();
		while (query.next()) {
			while (query.getString(1).equalsIgnoreCase(UserServiceCreation.userName)) {
//				 && UserServiceCreation.userName.length()>=5
				System.out.println("inside while");
				System.out.println("you have to choose any other username for security purpose");
				UserServiceCreation.userName = Constants.sc.next();
				UserNameDataExist(con);
			}
		}
		System.out.println("your username verification was perfecctly done");
	}

	public static String RetrieveUserID(Connection con, String mailid) throws SQLException {
		String sql = "select UserName from usercreation where mail=?;";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, mailid);
		ResultSet query = st.executeQuery();
		if (query.next()) {
			return query.getString(1);
		}
//			else {
//			System.out.println("no username !");
//		}
//		return query.getString(1);
		return "no username exist";
	}
	
	
	public static String MailCheck(Connection con, String mail) throws SQLException{
		String sql="select mail from usercreation where mail=?;";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, UserServiceCreation.mail);
		ResultSet execute = statement.executeQuery();
		if(execute.next()) {
			return "already exist";
		}
		return "new mail";
	}

}

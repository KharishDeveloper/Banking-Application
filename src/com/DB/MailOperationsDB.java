package com.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MailOperationsDB {

	public static boolean ForgotpasswordDOB(Connection con, String mail, String DOB) throws SQLException {
		String sql = "select DOB from usercreation where Mail=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, mail);
		ResultSet execute = stmt.executeQuery();
		if (execute.next()) {
			if (DOB.equals(execute.getString(1))) {
				return true;
			}
		}
		return false;

	}

	public static boolean PasswordRetrieve(Connection con, String mail, String pwd) throws SQLException {
		String sql = "select Password from usercreation where mail=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, mail);
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			if (set.getString(1).equals(pwd)) {
				return false;
			}
		}
		return true;
	}
	
	public static int UpdatePassword(Connection con, String mail, String pwd) throws SQLException{
		String sql="update usercreation set Password=? where Mail=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, pwd);
		stmt.setString(2, mail);
		int update = stmt.executeUpdate();
		if(update==1) {
			return 1;
		}
		return -1;
	}
}

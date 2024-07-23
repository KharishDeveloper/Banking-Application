package com.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.banking.BankLogin;

public class TransactionHistoryDB {

	public static void GetUserDetails(Connection con) throws SQLException {
		String sql = "select Mail,AccountNumber,Branch,CIF,OpenedDate from accountdetails where Mail=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "hari@gmail.com");
		ResultSetMetaData rsmd = stmt.getMetaData();
		int count = rsmd.getColumnCount();
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			for (int i = 1; i <= count; i++) {
				System.out.println(rsmd.getColumnName(i) + " : " + set.getString(i));
			}
		}
	}

	public static void GetTransactionHistory(Connection con, int limit) throws SQLException {
		String sql = "select * from transactionhistory where mail=?;";
		// mail,Date_Time,transactionID,amount,typeoftransaction,status,AnotherUseridTransfer
		// Debit,credit
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, BankLogin.mail);
		ResultSetMetaData md = st.getMetaData();
		int count = md.getColumnCount();// like a original count
		System.out.println("count :" + count);
		ResultSet set = st.executeQuery();
		while (set.next()) {

			for (int i = 1; i <= count; i++) {
				System.out.println(md.getColumnName(i) + " : " + set.getString(i));
			}
			System.out.println("--------------------");
		}

	}
}

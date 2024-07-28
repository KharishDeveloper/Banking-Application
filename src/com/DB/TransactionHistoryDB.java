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
		stmt.setString(1, BankLogin.mail);
		ResultSetMetaData md = stmt.getMetaData();
		int count = md.getColumnCount();
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			for (int i = 1; i <= count; i++) {
				System.out.print(md.getColumnName(i) + " : " + set.getString(i)+"\t");
				if(i%2==0) {
					System.out.println("");
				}
			}
		}
		System.out.println("");
	}

	public static void GetTransactionHistory(Connection con) throws SQLException {
		String sql = "select * from transactionhistory where mail=?;";
//		(mail,userID,transactionID,amount,Debit,credit,typeoftransaction,status,Date_Time,AnotherUseridTransfer)
		// mail,Date_Time,transactionID,amount,typeoftransaction,status,AnotherUseridTransfer
		// Debit,credit
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, BankLogin.mail);
		ResultSetMetaData md = st.getMetaData();
		int count = md.getColumnCount();// like a original count
//		System.out.println("count :" + count);
		ResultSet set = st.executeQuery();
		while (set.next()) {

			for (int i = 1; i <= count; i++) {
				System.out.print(md.getColumnName(i) + " : " + set.getString(i)+"\t");
				if(i%2==0) {
					System.out.println("");
				}
			}
			
			System.out.println("\n --------------------");
		}

	}
}

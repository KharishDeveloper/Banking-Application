package com.properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

import com.banking.BankLogin;

public class Constants {

	public static int FailedCount;
	public static String url = "jdbc:mysql://localhost:3306/banking";
	public static String username = "root";
	public static String password = "Harish@123";
	public static final int MinAmountDepsoit=10;
	public static final int MaxAmountDepsoit=10000;
	public static Scanner sc = new Scanner(System.in);

	public static Connection GetConnection() throws SQLException {

		Connection con = DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
		return con;
	}

	public static String generation(int GeneratedLength) {
		Supplier<String> su = () -> {

			System.out.println("random class");
			String data = "1234567895";
			Random ran = new Random();
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= GeneratedLength; i++) {
				int val = data.length();// data length is 3...
				int index = ran.nextInt(val);// 1,3,2,1,3,2
				// System.out.println(index);
				sb.append(data.charAt(index));
			}
			return sb.toString();
		};
		String gen = su.get();
		System.out.println("generated :"+gen);
		return gen;
	}

	public static void FailedCountSetter() throws SQLException {
		Connection con = Constants.GetConnection();
		String sql = "select failedcount from login where mail=?;";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, BankLogin.mail);
		ResultSet set = st.executeQuery();
		if (set.next()) {
			System.out.println(set.getInt(1));
		}
		FailedCount = set.getInt(1);
		System.out.println("FC is : " + FailedCount);
	}

//	public static String TransactionIDGenerator() {
//		StringBuffer sf = new StringBuffer();
//		String generation = Constants.generation(10);
//		sf.append('T');
//		sf.append(generation);
//		System.out.println("transaction id is :" + sf);
//		return "returning";
//	}

}

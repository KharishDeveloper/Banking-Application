package com.logics;

import java.util.Scanner;

public class BankExit {

	public static void exiter() {
		String data;
//		Scanner sc = new Scanner(System.in);
		data = "0";
		while (data.equals("0")) {
			System.out.println("exiter invoked");
			break;
		}
		System.out.println("outside while");
	}
}

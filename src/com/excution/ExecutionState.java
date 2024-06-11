package com.excution;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import com.logics.UserServiceLogic;

public class ExecutionState {

	public static void main(String[] args) throws SQLException {

				
//				random();
		
		UserServiceLogic.Businesses();
		}


	private static void random() {
		int length = 1;
//				Supplier<String> su = () -> {
			
			String data = "GN";
			Random ran = new Random();
			StringBuilder sb = new StringBuilder();
			for(int i=1;i<=length;i++) {
				int val=data.length();//data length is 3...
				int index = ran.nextInt(val);//1,3,2,1,3,2
				//System.out.println(index);
				sb.append(data.charAt(index));
			}
			System.out.println(sb);
//					return sb.toString();
//				};
//				String suppo = su.get();
//				System.out.println("keyword : "+suppo);
//			}
	}

		
//	}

	private static void extracted() {
		String s="aabbbccccccf";
		char[] array = s.toCharArray();
		
		Map<Character, Integer> map=new HashMap<>();
//		map.put(s, null);
		for(char c: array) {
			if(map.get(c)==null) {
				map.put(c, 1);
			}
			else {
				Integer val = map.get(c);
				map.put(c, val+1);
			}
		}
		System.out.println(map);
	}
}
package cn.techtutorial.connection;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DbCon {
//	private static Connection connection = null;
//	public static Connection getConnection() throws ClassNotFoundException, SQLException {
//		if (connection == null) {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart", "root", "vietinBank1");
//			System.out.println("connected to mysql successfully");
//		}
//		return connection;
//	}
//}

import java.sql.Connection;
import java.sql.DriverManager;

public class DbCon {
		static String conString = "jdbc:mysql://localhost:3306/newdb?useUnicode=tru&characterEncoding=UTF-8";
	    static String USER_NAME = "root";
	    static String PASSWORD = "vietinBank1";
	    static Connection connection = null;
		static {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection(conString, USER_NAME, PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static Connection getConnection() {
			try {
				if (connection == null || connection.isClosed()) {
					connection = DriverManager.getConnection(conString, USER_NAME, PASSWORD);
					System.out.println("Kết nối thành công");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return connection;
		}
		
		public static void CLOSE_CONNECTION() {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	 

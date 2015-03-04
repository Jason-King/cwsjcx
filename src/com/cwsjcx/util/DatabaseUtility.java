package com.cwsjcx.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class DatabaseUtility {
	private static int conCount =0;
	
	synchronized private static void modifyConCount(int modifier){
		conCount += modifier;
	}
	
	public static Connection getConnection() throws NamingException, SQLException {
//		modifyConCount(1);
//		Util.d("getConnection, conCount:" + conCount + ","
//				+ Util.getCurrentDatetime());
		Context ctx = null;
		Connection connection = null;
			ctx = new InitialContext();
			DataSource dataSource = (DataSource) ctx
					.lookup("java:comp/env/jdbc/jucaicat");
			connection = dataSource.getConnection();
		return connection;
	}

	public static Connection getConRO() {
//		modifyConCount(1);
//		Util.d("getConnection, conCount:" + conCount + ","
//				+ Util.getCurrentDatetime());
		Context ctx = null;
		Connection connection = null;
		try {
			ctx = new InitialContext();
			DataSource dataSource = (DataSource) ctx
					.lookup("java:comp/env/jdbc/jucaicat");
			connection = dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void close(Connection connection,
			CallableStatement callableStatement, ResultSet resultSet) {
		if (null != resultSet) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != callableStatement) {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != connection ) {
			try {
				connection.close();
//				modifyConCount(-1);
//				Util.d("Connection close, conCount:" + conCount
//						+ "," + Util.getCurrentDatetime());
			} catch (SQLException e) {
				e.printStackTrace();
				Util.d("Connection close fail!!!!, conCount:"
						+ conCount + "," + Util.getCurrentDatetime());
			}
		}
	}
	public static void finalClose(Connection connection,
			CallableStatement callableStatement, ResultSet resultSet) {
		if (null != resultSet) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != callableStatement) {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != connection ) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				Util.d("Connection close fail!!!!, conCount:"
						+ conCount + "," + Util.getCurrentDatetime());
			}
		}
	}
	public static void close(Connection connection,
			PreparedStatement preparedStatement, ResultSet resultSet) {
		if (null != resultSet) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != preparedStatement) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != connection) {
			try {
				connection.close();
//				modifyConCount(-1);
//				System.out.println("Connection close, conCount:" + conCount
//						+ "," + Util.getCurrentDatetime());
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Connection close fail!!!!, conCount:"
						+ conCount + "," + Util.getCurrentDatetime());
			}
		}
	}
}

package com.entor.jdbc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JDBCCustomer {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	static {
		try {
			Properties properties = new Properties();
			properties.load(new BufferedInputStream(new FileInputStream(new File("jdbc.properties"))));
			driver = properties.getProperty("jdbc.driver");
			url = properties.getProperty("jdbc.url");
			username = properties.getProperty("jdbc.username");
			password = properties.getProperty("jdbc.password");

			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getconnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static Integer execute(String sql, Object... objects) {
		int column = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getconnection();
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < objects.length; i++) {
				preparedStatement.setObject(i + 1, objects[i]);
			}
			column = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(null, preparedStatement, connection);
		}
		return column;
	}

	public static List<Map<String, Object>> query(String sql, Object... objects) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getconnection();
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < objects.length; i++) {
				preparedStatement.setObject(i + 1, objects[i]);
			}
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int column = resultSetMetaData.getColumnCount();
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < column; i++) {
					String columnname = resultSetMetaData.getColumnName(i);
					Object columnvalue = resultSet.getObject(columnname);
					map.put(columnname, columnvalue);

				}
				result.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			release(resultSet, preparedStatement, connection);
		}

		return result;
	}
	public static void release(ResultSet resultSet,PreparedStatement preparedStatement, Connection connection){
		try {
			if (null != resultSet) {
				resultSet.close();
			}
			if (null != preparedStatement) {
				preparedStatement.close();
			}
			if (null != connection) {
				connection.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}

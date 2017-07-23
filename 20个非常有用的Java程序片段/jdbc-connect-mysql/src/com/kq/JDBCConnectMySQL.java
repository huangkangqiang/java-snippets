package com.kq;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用JDBC连接MySQL
 * 
 * @author huang
 *
 */
public class JDBCConnectMySQL {

	private Connection connection;

	public static void main(String[] args)
			throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {

		JDBCConnectMySQL jdbcConnectMySQL = new JDBCConnectMySQL();
		jdbcConnectMySQL.init(new FileInputStream("src/com/kq/config.properties"));
		jdbcConnectMySQL.fetch();
	}

	public void init(FileInputStream fs) throws IOException, ClassNotFoundException, SQLException {

		Properties properties = new Properties();
		properties.load(fs);

		String url = properties.getProperty("db.url");
		String user = properties.getProperty("db.username");
		String password = properties.getProperty("db.password");
		String className = properties.getProperty("db.driverClass");

		Class.forName(className);

		connection = DriverManager.getConnection(url, user, password);
	}

	public void fetch() throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
		}

		resultSet.close();
		preparedStatement.close();
		connection.close();
	}

}

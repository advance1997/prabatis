package com.advance.batis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcDataSource {
	
	private String className;
	
	private String url;
	
	private String username;
	
	private String password;
	
	public JdbcDataSource(Properties properties){
		this.className = properties.getProperty("class");
		this.url = properties.getProperty("url");
		this.username = properties.getProperty("username");
		this.password = properties.getProperty("password");
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ClassNotFoundException("JDBC类加载失败！");
		}
		Connection connection = null;
		try {
			 connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException("JDBC获取连接Connection失败！");
		}
		return connection;
	}

}

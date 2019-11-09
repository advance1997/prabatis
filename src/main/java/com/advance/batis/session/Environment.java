package com.advance.batis.session;

import com.advance.batis.jdbc.JdbcDataSource;

public class Environment {

	private JdbcDataSource dataSource;
	
	public Environment(JdbcDataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public JdbcDataSource getJdbcDataSource(){
		return dataSource;
	}
	
}

package com.advance.batis.validate;

public class ValidateSql {

	public static void assertSql(String sql){
		if(null == sql || "".equals(sql)){
			throw new IllegalArgumentException("### the sql is null!");
		}
	}
	
}

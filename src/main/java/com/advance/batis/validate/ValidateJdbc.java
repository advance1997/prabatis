package com.advance.batis.validate;

import java.sql.PreparedStatement;

public class ValidateJdbc {

	public static void assertStatement(PreparedStatement ps){
		if(null == ps){
			throw new IllegalArgumentException("### the PreparedStatement is null!");
		}
	}
	
}

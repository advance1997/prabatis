package com.advance;

import java.sql.SQLException;

import com.advance.batis.builder.SqlSessionFactoryBuilder;
import com.advance.batis.session.SqlSession;
import com.advance.batis.session.SqlSessionFactory;
import com.advance.test.mapper.UserMapper;

public class Application {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder("prabatis.xml");
		SqlSessionFactory factory = builder.build();
		SqlSession session = factory.openSqlSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.println(userMapper.getUserList());
//		userMapper.deleteUser();
	}

}

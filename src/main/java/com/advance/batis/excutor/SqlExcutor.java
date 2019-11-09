package com.advance.batis.excutor;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.advance.batis.Enum.SqlAnnotationEnumType;
import com.advance.batis.annotations.Delete;
import com.advance.batis.annotations.Insert;
import com.advance.batis.annotations.Select;
import com.advance.batis.annotations.Update;
import com.advance.batis.jdbc.JdbcDataSource;
import com.advance.batis.session.Configuration;
import com.advance.batis.session.Environment;
import com.advance.batis.validate.ValidateJdbc;
import com.advance.batis.validate.ValidateSql;

public class SqlExcutor {

	private  String sql;
	
	private Method method;
	
	private Configuration configuration;
	
	public SqlExcutor(Method method, Configuration configuration){
		this.method = method;
		this.configuration = configuration;
		this.sql = getMethodSql();
	}
	
	
	/**
	 * 获取当前的注解类型从而执行对应的jdbc操作
	     * @Title: getSqlAnnotationType  
	     * @Description: TODO
	     * @param @param method
	     * @param @return    参数  
	     * @return SqlAnnotationEnum    返回类型  
	     * @throws
	 */
	public SqlAnnotationEnumType getSqlAnnotationType(Method method){
		if(null != method.getAnnotation(Select.class)){
			return SqlAnnotationEnumType.SELECT;
		}else if(null != method.getAnnotation(Update.class)){
			return SqlAnnotationEnumType.UPDATE;
		}else if(null != method.getAnnotation(Insert.class)){
			return SqlAnnotationEnumType.INSERT;
		}else if(null != method.getAnnotation(Delete.class)){
			return SqlAnnotationEnumType.DELETE;
		}
		return SqlAnnotationEnumType.UNKNOW;
	}
	
	public List<Map<String, Object>> doQuery(){
		PreparedStatement ps = getreparedStatement();
		ValidateJdbc.assertStatement(ps);
		try {
			ResultSet resultSet = ps.executeQuery();
			return new ResultSetHandler(resultSet).handlerQueryResultSet();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int doUpdate(){
		PreparedStatement ps = getreparedStatement();
		ValidateJdbc.assertStatement(ps);
		try {
			int i = ps.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 获取注解的sql
	     * @Title: getMethodSql  
	     * @Description: TODO
	     * @param @return    参数  
	     * @return String    返回类型  
	     * @throws
	 */
	private String getMethodSql(){
		SqlAnnotationEnumType type = getSqlAnnotationType(method);
		String sql;
		switch (type) {
		case SELECT:
			sql = method.getAnnotation(Select.class).value()[0];
			break;
		case UPDATE:
			sql = method.getAnnotation(Update.class).value()[0];
			break;
		case INSERT:
			sql = method.getAnnotation(Insert.class).value()[0];
			break;
		case DELETE:
			sql = method.getAnnotation(Delete.class).value()[0];
			break;
		default:
			sql = null;
			break;
		}
		return sql;
	}
	
	public PreparedStatement getreparedStatement(){
		ValidateSql.assertSql(sql);
		Connection connection;
		try {
			connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			return ps;
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Environment environment = configuration.getEnviroment();
		JdbcDataSource dataSource = environment.getJdbcDataSource();
		return dataSource.getConnection();
	}
	
}

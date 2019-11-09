package com.advance.batis.excutor;

import java.lang.reflect.Method;

import com.advance.batis.Enum.SqlAnnotationEnumType;
import com.advance.batis.session.Configuration;

/**
 * Jdbc执行方法类
     * @ClassName: JdbcExcutor  
     * @Description: TODO
     * @author advance  
     * @date 2019年11月4日  
     *
 */
public class JdbcExcutor {

	private Method method;
	private SqlExcutor sqlExcutor;
	private Configuration configuration;
	
	public JdbcExcutor(Method method, Configuration configuration){
		this.method = method;
		this.configuration = configuration;
		this.sqlExcutor = new SqlExcutor(method, this.configuration); 
	}
	
	public Object doInvokeMethod(){
		SqlAnnotationEnumType type = sqlExcutor.getSqlAnnotationType(method);
		if(SqlAnnotationEnumType.SELECT.equals(type)){
			return sqlExcutor.doQuery();
		}else{
			return sqlExcutor.doUpdate();
		}
	}
	
}

package com.advance.batis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.advance.batis.excutor.JdbcExcutor;
import com.advance.batis.session.Configuration;

/**
 * 动态代理处理方法
     * @ClassName: ProxyHandler  
     * @Description: TODO
     * @author advance  
     * @date 2019年11月3日  
     *
 */
public class ProxyHandler implements InvocationHandler{
	
	private Configuration configuration;
	
	public ProxyHandler(Configuration configuration){
		this.configuration = configuration;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		JdbcExcutor jdbcExcutor = new JdbcExcutor(method, configuration);
		return jdbcExcutor.doInvokeMethod();
	}

}

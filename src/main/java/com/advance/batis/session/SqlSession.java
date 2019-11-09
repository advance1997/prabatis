package com.advance.batis.session;

import com.advance.batis.proxy.ProxyFactory;

/**
 * session会话，负责封装jdbc的方法
     * @ClassName: SqlSession  
     * @Description: TODO
     * @author advance  
     * @date 2019年11月3日  
     *
 */
public class SqlSession {

	private Configuration configuration;
	
	public SqlSession(Configuration configuration){
		this.configuration = configuration;
	}
	
	public <T> T getMapper(Class<T> clazz){
		ProxyFactory<T> proxyFactory = new ProxyFactory<T>(clazz, configuration);
		return proxyFactory.newInstance();
	}
	
}

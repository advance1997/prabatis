package com.advance.batis.proxy;

import java.lang.reflect.Proxy;

import com.advance.batis.session.Configuration;

/**
 * 代理工厂，封装动态代理返回一个对象
     * @ClassName: ProxyFactory  
     * @Description: TODO
     * @author advance  
     * @date 2019年11月3日  
     *
 */
public class ProxyFactory<T> {


	private final Class<T> target;
	
	private Configuration configuration;
	
	public ProxyFactory(Class<T> target, Configuration configuration){
		this.target = target;
		this.configuration = configuration;
	}
	
	@SuppressWarnings("unchecked")
	public T newInstance(){
		return (T) Proxy.newProxyInstance(target.getClassLoader(), 
				new Class[] {target}, 
				new ProxyHandler(configuration));
	}
	
}

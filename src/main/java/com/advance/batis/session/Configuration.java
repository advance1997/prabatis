package com.advance.batis.session;

/**
 * 获取配置信息
     * @ClassName: Configuration  
     * @Description: TODO
     * @author advance  
     * @date 2019年11月4日  
     *
 */
public class Configuration {
	
	private Environment environment;
	
	public Configuration(Environment environment){
		this.environment = environment;
	}
	
	public Environment getEnviroment(){
		return environment;
	}
	
}

package com.advance.batis.session;

/**
 * session会话工厂
     * @ClassName: SessionFactory  
     * @Description: TODO
     * @author advance  
     * @date 2019年11月3日  
     *
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

	private Configuration configuration;
	
	public DefaultSqlSessionFactory(Configuration configuration) {
		// TODO Auto-generated constructor stub
		this.configuration = configuration;
	}
	
	@Override
	public SqlSession openSqlSession() {
		// TODO Auto-generated method stub
		return new SqlSession(configuration);
	}

	@Override
	public Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return configuration;
	}
	
	
	
}

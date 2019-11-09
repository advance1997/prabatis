package com.advance.batis.session;

/**
 * sqlSession工厂
     * @ClassName: SqlSessionFactory  
     * @Description: TODO
     * @author advance  
     * @date 2019年11月9日  
     *
 */
public interface SqlSessionFactory {

	SqlSession openSqlSession();
	
	Configuration getConfiguration();
	
}

package com.advance.batis.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.advance.batis.parse.ParseXmlDom;
import com.advance.batis.session.Configuration;
import com.advance.batis.session.DefaultSqlSessionFactory;
import com.advance.batis.session.Environment;
import com.advance.batis.session.SqlSessionFactory;

/**
 * 创建会话工厂
     * @ClassName: SqlSessionFactoryBuilder  
     * @Description: TODO
     * @author advance  
     * @date 2019年11月4日  
     *
 */
public class SqlSessionFactoryBuilder {

	private String xmlPath;
	private InputStream is;
	private Configuration configuration;
	
	public SqlSessionFactoryBuilder(String xmlPath){
		this.xmlPath = xmlPath;
		this.is = getResourceStream();
		this.configuration = this.getConfiguration();
	}
	
	/**
	 * 获取配置信息
	     * @Title: getConfiguration  
	     * @Description: TODO
	     * @param @return    参数  
	     * @return Configuration    返回类型  
	     * @throws
	 */
	private Configuration getConfiguration(){
		ParseXmlDom parseXmlDom = new ParseXmlDom(is);
		Environment environment = parseXmlDom.getEnvironment();
		return new Configuration(environment);
	}
	
	public SqlSessionFactory build(){
		return new DefaultSqlSessionFactory(configuration);
	}
	
	/**
	 * 获取配置文件路径
	     * @Title: getResourceStream  
	     * @Description: TODO
	     * @param @return    参数  
	     * @return InputStream    返回类型  
	     * @throws
	 */
	public InputStream getResourceStream(){
		String resourcePath = this.getClass().getResource("/").getPath() + xmlPath;
		InputStream is = null;
		try {
			is = new FileInputStream(new File(resourcePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}
	
}

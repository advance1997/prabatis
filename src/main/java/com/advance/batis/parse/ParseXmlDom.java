package com.advance.batis.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.advance.batis.builder.SqlSessionFactoryBuilder;
import com.advance.batis.jdbc.JdbcDataSource;
import com.advance.batis.session.Environment;

public class ParseXmlDom {
	
	public Document document;
	
	public ParseXmlDom(InputStream is){
		this.document = parseDocument(is);
	}
	
	private Document parseDocument(InputStream is){
		SAXBuilder builder = new SAXBuilder();
		try {
			return builder.build(is);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Element parseRootElement(){
		return document.getRootElement();
	}
	
	private Element getEenvironmentElement(){
		Element root = parseRootElement();
		List<Element> environmentElements = root.getChildren();
		if(environmentElements.size() == 0){
			throw new IllegalArgumentException("###---->：Three occur a error because config"
					+ " xml without environment elements");
		}
		return environmentElements.get(0);
	}
	
	private Element getEnvironmentElement(){
		Element environmentElements = getEenvironmentElement();
		String defaultMode = getDefaultEnvironmentModeName();
		List<Element> environmentElementList = environmentElements.getChildren();
		for(Element element : environmentElementList){
			String id = element.getAttributeValue("id");
			if(defaultMode.equals(id)){
				return element;
			}
		}
		System.out.println("*****************default first enviornment element as system enviornment**********");
		return environmentElementList.get(0);
	}
	
	public Environment getEnvironment(){
		Element environmentElement = getEnvironmentElement();
		Element dataSource = environmentElement.getChild("datasource");
		List<Element> values = dataSource.getChildren();
		Properties properties = traslateValuesForProperties(values);
		JdbcDataSource jdbcDataSource = new JdbcDataSource(properties);
		Environment environment = new Environment(jdbcDataSource);
		return environment;
	}
	
	private Properties traslateValuesForProperties(List<Element> values){
		Properties properties = new Properties();
		for(Element element : values){			
			properties.put(element.getAttributeValue("name"), element.getAttributeValue("value"));
		}
		return properties;
	}
	
	private String getDefaultEnvironmentModeName(){
		return getEenvironmentElement().getAttributeValue("default");
	}
	
	public static void main(String[] args) {
		ParseXmlDom parseXmlDom = new ParseXmlDom(new SqlSessionFactoryBuilder("prabatis.xml").getResourceStream());
		parseXmlDom.getEnvironment();
	}
	
}

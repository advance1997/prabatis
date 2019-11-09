package com.advance.batis.excutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class ResultSetHandler {

	private ResultSet rs;
	
	public ResultSetHandler(ResultSet rs){
		this.rs = rs;
	}
	
	public List<Map<String, Object>> handlerQueryResultSet() throws SQLException{
		List<Map<String, Object>> returnData = new ArrayList<Map<String,Object>>();
		ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
		int metaDataCount = metaData.getColumnCount();
		Map<String, Object> data_item = null;
		while (rs.next()) {
			data_item = new HashMap<>();
			//从1开始的索引，初始值设置为1
			for (int i = 1; i <= metaDataCount; i++) {
				String colnumName = metaData.getColumnName(i);
				Object colunmValue = rs.getObject(colnumName);
				data_item.put(colnumName, colunmValue);
			}
			returnData.add(data_item);
		}
		return returnData;
	}
	
}

package com.advance.test.mapper;

import java.util.List;
import java.util.Map;

import com.advance.batis.annotations.Delete;
import com.advance.batis.annotations.Select;

public interface UserMapper {

	@Select("select * from user")
	Object getUserList();
	
	@Delete("delete from user where id = 123")
	void deleteUser();
	
}

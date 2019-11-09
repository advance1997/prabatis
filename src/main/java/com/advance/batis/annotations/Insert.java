package com.advance.batis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，新增方法注解
     * @ClassName: Select  
     * @Description: TODO
     * @author advance  
     * @date 2019年11月3日  
     *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Insert {

	String[] value();
	
}

package com.shop.springboot;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootTest
class SpringbootApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private SqlSessionFactory sessionFactory;

	@Test
	void contextLoads() {
	}

	@Test
	void context_테스트(){
		try{
			System.out.println("=============");
			System.out.println(context.getBean("sqlSessionFactory"));
			System.out.println("=============");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Test
	void SqlSession_테스트(){
		try{
			System.out.println("=============");
			System.out.println(sessionFactory.toString());
			System.out.println("=============");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}

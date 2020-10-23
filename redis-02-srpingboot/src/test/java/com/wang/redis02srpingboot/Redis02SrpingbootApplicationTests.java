package com.wang.redis02srpingboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.redis02srpingboot.Utils.RedisUtil;
import com.wang.redis02srpingboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SrpingbootApplicationTests {

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;

	@Autowired
	private RedisUtil redisUtil;

	@Test
	void contextLoads() {
		//操作不同的数据类型
		//opsForValue 操作字符串 类似 String, 其中Bitmap也在这个方法下面, 可以.setBit()方法
		//opsForList list
		//opsForSet set
		//opsForHash hashmap
		//opsForZset Zset
		//opsForGeo geo
		//opsForHyperLogLog hyperloglog
		/*
		redisTemplate.opsForValue().setBit("sign",0, true);
		 */

		//除了基本的操作, 我们常用的方法都可以直接通过RedisTemplate来操作, 比如事务和基本的CRUD

		//获取Redis的连接对象
		/*
		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
		connection.flushAll();
		 */
		redisTemplate.opsForValue().set("myKey", "我的RedisValue");
		System.out.println(redisTemplate.opsForValue().get("myKey"));
	}

	@Test
	void test() throws JsonProcessingException {
		//真实的开发一般使用JSON传递对象, 这里用SpringBoot自带的Jackson
		User user = new User("测试用户", 3);
		String jsonUser = new ObjectMapper().writeValueAsString(user);
		redisTemplate.opsForValue().set("user", jsonUser);
		System.out.println(redisTemplate.opsForValue().get("user"));
	}

	//利用我们自己封装的工具类测试
	@Test
	void test1() {
		redisUtil.set("name", "测试用户2");
		System.out.println(redisUtil.get("name"));
	}

}

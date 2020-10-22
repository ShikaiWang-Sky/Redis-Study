package com.wang.redis02srpingboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SrpingbootApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

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

}

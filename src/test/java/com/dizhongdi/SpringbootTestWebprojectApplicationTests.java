package com.dizhongdi;


import com.alibaba.druid.pool.DruidDataSource;
import com.dizhongdi.service.RegisterCodeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

@SpringBootTest
@DataRedisTest
class SpringbootTestWebprojectApplicationTests {

    @Autowired
    RegisterCodeServiceImpl codeService;


    //DI注入数据源
    @Autowired
    DataSource dataSource;

//    @Autowired
//    RedisTemplate redisTemplate;
//    @Test
//    public void test01(){
//        //字符串操作
//        redisTemplate.opsForValue().append("msg","coder");
//
//        //列表操作
//        redisTemplate.opsForList().leftPush("mylist","1");
//        redisTemplate.opsForList().leftPush("mylist","2");
//    }


    @Test
    void contextLoads() throws SQLException {
//        String s = codeService.sendEmailCode("1556035287@qq.com");
//        System.out.println(s);
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection =   dataSource.getConnection();
        System.out.println(connection);


        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());


        //关闭连接
        connection.close();

    }

}

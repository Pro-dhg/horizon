package com.example.hong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName Application
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/16 17:44
 * @Description:
 */
@EnableScheduling
@MapperScan(value = "com.example.hong.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

package com.last.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 22514
 */
@ComponentScan("com.last")
@MapperScan("com.last.edu.mapper")
@SpringBootApplication
public class LastEduApplication {
    public static void main(String[] args) {
        SpringApplication.run(LastEduApplication.class,args);
    }
}

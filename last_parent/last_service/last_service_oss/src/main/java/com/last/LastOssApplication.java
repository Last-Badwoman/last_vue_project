package com.last;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * @author 22514
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LastOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(LastOssApplication.class, args);
    }

}

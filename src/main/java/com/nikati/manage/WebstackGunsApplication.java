package com.nikati.manage;

import cn.stylefeng.roses.core.config.WebAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 *@Title WebstackGunsApplication.java
 *@description SpringBoot方式启动类
 *@time 2019年12月22日 下午8:47:10
 *@version 1.0
*
 */
@SpringBootApplication(exclude = WebAutoConfiguration.class)
@EnableScheduling
@EnableAsync
public class WebstackGunsApplication {

    private final static Logger logger = LoggerFactory.getLogger(WebstackGunsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebstackGunsApplication.class, args);
        logger.info("Application is success!");
    }
}

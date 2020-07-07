package com.nikati.manage.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@Slf4j
public class AutoDeleteUselessWebsite {
    @Value("${guns.flag}")
    private Boolean flag;
     @Scheduled(cron = "*/5 * * * * ?")
    public void monnitor() {
         if(flag){
             log.info("开始检查是否有不可用的站点------------------------------------------");
         }
         else{
             log.info("检查机制已经关闭！！！");
         }

    }

}

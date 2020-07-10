package com.nikati.manage.config;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nikati.manage.modular.system.dao.VisitorMapper;
import com.nikati.manage.modular.system.model.Visitor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@Slf4j
public class AutoDeleteUselessWebsite {

    @Autowired
    private VisitorMapper visitorMapper;
    @Value("${guns.flag}")
    private Boolean flag;

    //     @Scheduled(cron = "*/5 * * * * ?")
//    public void monnitor() {
//         if(flag){
//             log.info("开始检查是否有不可用的站点------------------------------------------");
//         }
//         else{
//             log.info("检查机制已经关闭！！！");
//         }
//
//    }
//    @Scheduled(cron = "*/15 * * * * ?")
//    public void bigDataInsertMysql() {
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        for (int i = 0; i < 3; i++) {
//            executorService.submit(()->task());
//        }
//
//    }
//    private void task(){
//        for (int i = 0; i < 1000; i++) {
//            Visitor visitor = new Visitor();
//            visitor.setIp("127.0.0.1");
//            visitor.setOs("linux");
//            visitor.setBrowser("google");
//            visitor.setAddress("上海");
//            visitor.setCreate_time(new Date());
//            visitorMapper.insertSelective(visitor);
//        }
//
//    }

}

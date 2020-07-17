package com.nikati.manage.config;

import com.nikati.manage.modular.system.dao.SiteMapper;
import com.nikati.manage.modular.system.dao.VisitorMapper;
import com.nikati.manage.modular.system.model.Site;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class AutoDeleteUselessWebsite {

    @Autowired
    private VisitorMapper visitorMapper;
    @Value("${guns.flag}")
    private Boolean flag;
    @Autowired
    private SiteMapper siteMapper;

   @Scheduled(cron = "0 0/45 * * * ?")
 //   @Scheduled(cron = "0/5 * * * * ?")

    public void checkUselessWebSite() {
        List<Site> siteList = new ArrayList<>();
        List<Site> siteMapperList = siteMapper.getList(null);
        for (Site site : siteMapperList) {
            Integer testUrlWithTimeOut = TestsUrl.testUrlWithTimeOut(site.getUrl(), 2500);
            if (testUrlWithTimeOut == 0) {
                // 状态置空
                site.setState(0);
                siteList.add(site);
            }
        }
        //  批量数据插入到数据库，更新状态；
        List<Integer> integerList = siteList.stream().map(Site::getId).collect(Collectors.toList());
        log.info("开始执行信息,参数如下：{}", integerList.toString());
        siteMapper.batchUpdate(integerList);

    }

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
//    @Scheduled(cron = "*/10 * * * * ?")
//    public void bigDataInsertMysql() {
//        ExecutorService executorService = Executors.newFixedThreadPool(12);
//        for (int i = 0; i < 24; i++) {
//            executorService.submit(()->task());
//        }
//
//    }
//    private void task(){
//        ArrayList<Visitor> visitors = Lists.newArrayList(new Visitor());
//        for (int i = 0; i < 1000; i++) {
//            Visitor visitor = new Visitor();
//            visitor.setIp("127.0.0.1");
//            visitor.setOs("linux");
//            visitor.setBrowser("google");
//            visitor.setAddress("上海");
//            visitor.setCreate_time(new Date());
//            visitors.add(visitor);
//            visitorMapper.insertSelective(visitor);
//        }
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

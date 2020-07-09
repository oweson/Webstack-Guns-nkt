package com.nikati.manage.modular.system.model;

import lombok.Data;

import java.util.Date;

@Data
public class Visitor {
    /**int serverPort = request.getServerPort();
     String ipAddr = IpUtils.getIpAddr(request);
     String osAndBrowserInfo = IpUtils.getOsAndBrowserInfo(request);
     ipAddr="222.76.8.158";
     String queryAddress = AddressUtils.queryAddress(ipAddr);
     System.out.println("");*/
    private Integer id;
    private String ip;
    private String os;
    private String browser;
    private String address;
    private  Date create_time;
}

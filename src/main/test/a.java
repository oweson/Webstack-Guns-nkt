import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.google.common.collect.Maps;
import com.nikati.manage.core.util.TencentIpUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @author oweson
 * @date 2021/7/19 23:18
 */


public class a {

    /**
     * ping ip地址是否通的方法
     *
     * @param usableUrl
     * @return
     * @throws Throwable
     */
    private static boolean pingUrl(String usableUrl) throws Throwable {

        String reaDomain = "";

        if (usableUrl.contains("http://")) {
            reaDomain = usableUrl.replace("http://", "");
        }

        if (usableUrl.contains("https://")) {
            reaDomain = usableUrl.replace("https://", "");
        }

        int lastIndexOf = reaDomain.lastIndexOf("/");

        String substring = reaDomain.substring(0, lastIndexOf);

        usableUrl = substring;
        // http://giveupfantasy.top/

        //String usableUrl = "127.0.0.1";
        int timeOut = 1000;  //超时应该在1钞以上
        // 当返回值是true时，说明host是可用的。
        return InetAddress.getByName(usableUrl).isReachable(timeOut);
    }

    public static void main(String[] args) throws Throwable {
      /*  String addresses = TencentIpUtils.getAddresses("85.29.147.122");
        System.out.println(addresses);*/
        // url无规律，有的依赖vpn才可以！

        boolean b = pingUrl("https://www.flaticon.com/");

        // 没有必要，url

        System.out.println(b);

    }
}

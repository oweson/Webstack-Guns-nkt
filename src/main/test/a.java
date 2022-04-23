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
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @author oweson
 * @date 2021/7/19 23:18
 */


public class a {
    public static void main(String[] args) throws IOException {
        String addresses = TencentIpUtils.getAddresses("85.29.147.122");
        System.out.println(addresses);

    }
}

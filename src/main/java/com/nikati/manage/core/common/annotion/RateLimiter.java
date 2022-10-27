package com.nikati.manage.core.common.annotion;



import com.nikati.manage.core.common.constant.Constants;
import com.nikati.manage.core.common.constant.LimitType;

import java.lang.annotation.*;

/**
 * 限流注解
 *
 * @author Lion Li
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /**
     * 限流key
     */
    String key() default Constants.RATE_LIMIT_KEY;

    /**
     * 限流时间,单位秒
     */
    int time() default 10;

    /**
     * 限流次数
     */
    int count() default 5;

    /**
     * 限流类型
     */
    LimitType limitType() default LimitType.IP;
}

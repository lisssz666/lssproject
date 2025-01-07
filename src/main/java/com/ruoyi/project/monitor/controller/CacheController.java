package com.ruoyi.project.monitor.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 缓存监控
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor/cache")
public class CacheController {

    //是否启用了redis
    @Value("${spring.redis.enabled}")
    private boolean enabledRedis;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PreAuthorize("@ss.hasPermi('monitor:cache:list')")
    @GetMapping()
    public AjaxResult getInfo() throws Exception {
        Map<String, Object> result = new HashMap<>(3);
        List<Map<String, String>> pieList = new ArrayList<>();
        if(enabledRedis){
            result.put("enabled", true);
            Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info());
            Properties commandStats = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));
            Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());
            result.put("info", info);
            result.put("dbSize", dbSize);

            assert commandStats != null;
            commandStats.stringPropertyNames().forEach(key -> {
                Map<String, String> data = new HashMap<>(2);
                String property = commandStats.getProperty(key);
                data.put("name", StringUtils.removeStart(key, "cmdstat_"));
                data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
                pieList.add(data);
            });
        }else {
            result.put("enabled", false);
            result.put("info", null);
            result.put("dbSize", null);
        }
        result.put("commandStats", pieList);

        return AjaxResult.success(result);
    }
}

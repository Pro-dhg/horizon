package com.example.hong.config;

import cn.hutool.core.thread.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ExecutorDynamicService
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/28 10:13
 * @Description: 将配置文件自动加载的注释 @ConfigurationProperties
 */
@Slf4j
@Component
public class ExecutorDynamicService {

    private static final int maxPoolSize = Runtime.getRuntime().availableProcessors();

    @Value("${executor.corePoolSize}")
    private Integer corePoolSize;

    @Value("${executor.maximumPoolSize}")
    private Integer maximumPoolSize;

    public static ThreadPoolExecutor EXECUTOR_SERVICE = new ThreadPoolExecutor(Math.min(maxPoolSize, 5), Math.min(maxPoolSize, 10),
            10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5000), new NamedThreadFactory("HORIZON", false));

    @PostConstruct
    private void init() {
        log.info("System availableProcessors:{},config corePoolSize:{},config maximumPoolSize:{}", maxPoolSize, corePoolSize, maximumPoolSize);
        EXECUTOR_SERVICE.allowCoreThreadTimeOut(true);
        if (corePoolSize > 0 && maximumPoolSize >= corePoolSize) {
            EXECUTOR_SERVICE.setMaximumPoolSize(maximumPoolSize);
            EXECUTOR_SERVICE.setCorePoolSize(corePoolSize);
        }
    }
}

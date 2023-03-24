package com.example.hong.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName PullBehaviorDataJob
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/16 16:22
 * @Description:
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "pullTask", name = "pull", havingValue = "true")
public class PullBehaviorDataJob {

    @Scheduled(cron = "${pullTask.behavior.scheduledCron}")
    private void pullBehaviorDataJob(){
        try {
            log.info("开始拉取行为。。。");
            log.info("拉取行为成功");
        }catch (Exception e){
            log.error("拉取行为失败");
        }

    }
}

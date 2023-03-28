package com.example.hong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hong.entity.Behavior;
import com.example.hong.entity.VO.BehaviorVO;
import com.example.hong.mapper.BehaviorMapper;
import com.example.hong.param.BehaviorAddParam;
import com.example.hong.param.BehaviorQueryParam;
import com.example.hong.service.BehaviorDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;


/**
 * @ClassName BehaviorDataServiceImpl
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/16 14:55
 * @Description:
 */
@Slf4j
@Service
public class BehaviorDataServiceImpl implements BehaviorDataService {

    private final BehaviorMapper behaviorMapper;

    private final LinkedBlockingQueue<BehaviorAddParam> queue = new LinkedBlockingQueue<>();
    private final Thread thread = new Thread(new Consumer());

    public BehaviorDataServiceImpl(BehaviorMapper behaviorMapper) {
        this.behaviorMapper = behaviorMapper;
    }

    @PostConstruct
    private void init() {
        thread.start();
    }

    @Override
    public Page<BehaviorVO> queryBehavior(BehaviorQueryParam param) {
        Page<Behavior> behaviorPage = behaviorMapper.selectPage(new Page<>(param.getPageNumber(), param.getPageSize()),
                new LambdaQueryWrapper<Behavior>()
                        .eq(StringUtils.isNoneBlank(param.getId()), Behavior::getId, param.getId())
                        .eq(StringUtils.isNoneBlank(param.getName()), Behavior::getName, param.getName())
        );
        Page<BehaviorVO> result = new Page<>();
        result.setCurrent(behaviorPage.getCurrent());
        result.setTotal(behaviorPage.getTotal());
        result.setPages(behaviorPage.getPages());
        result.setRecords(behaviorPage.getRecords().stream().map(BehaviorVO::valueOf).collect(Collectors.toList()));
        return  result ;
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // 出错回滚，对应的是重要数据
    public void addBehavior(BehaviorAddParam behaviorAddParam) throws InterruptedException {
        queue.put(behaviorAddParam);
    }

    class Consumer implements Runnable {

        public Consumer() {
        }

        @Override
        public void run() {

            while (true) {
                try {
                    BehaviorAddParam param = queue.take();
                    Behavior build = Behavior.builder()
                            .id(param.getId()).name(param.getName()).parseTimestamp(new Date()).behavior(param.getBehavior())
                            .build();
                    behaviorMapper.insert(build);
                } catch (InterruptedException e) {
                    log.info("InterruptedException is running {}", e.getMessage());
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

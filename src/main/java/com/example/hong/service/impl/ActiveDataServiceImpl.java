package com.example.hong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hong.config.ExecutorDynamicService;
import com.example.hong.entity.DayActive;
import com.example.hong.entity.VO.ActiveVO;
import com.example.hong.mapper.ActiveMapper;
import com.example.hong.param.ActiveAddParam;
import com.example.hong.param.ActiveParam;
import com.example.hong.service.ActiveDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @ClassName ActiveDataServiceImpl
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/27 21:55
 * @Description:
 */
@Slf4j
@Service
public class ActiveDataServiceImpl implements ActiveDataService {
    private final ActiveMapper activeMapper ;

    public ActiveDataServiceImpl(ActiveMapper activeMapper) {
        this.activeMapper = activeMapper;
    }

    @Override
    public Page<ActiveVO> queryActive(ActiveParam param) {
        Page<DayActive> dayActivePage = activeMapper.selectPage(new Page<>(param.getPageNumber(), param.getPageSize()), new LambdaQueryWrapper<DayActive>()
                .eq(DayActive::getId, param.getId()));

        Page<ActiveVO> result = new Page<>();
        result.setCurrent(dayActivePage.getCurrent());
        result.setTotal(dayActivePage.getTotal());
        result.setPages(dayActivePage.getPages());
        result.setRecords(dayActivePage.getRecords().stream().map(ActiveVO::valueOf).collect(Collectors.toList()));
        return  result ;
    }

    @Override
    public void multiThreadAddActive(ActiveAddParam activeParam) {
        ExecutorDynamicService.EXECUTOR_SERVICE.submit(new addActiveTask(activeParam));
    }

    class addActiveTask implements Runnable {

        private final ActiveAddParam activeParam;

        public addActiveTask(ActiveAddParam activeParam) {
            this.activeParam = activeParam;
        }

        @Override
        public void run() {
            try {
                System.out.println(activeParam);
                activeMapper.insert(DayActive.builder()
                        .id(activeParam.getId())
                        .parseTimestamp(new Date())
                        .build());
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                System.out.println("111");
            }
        }
    }

}

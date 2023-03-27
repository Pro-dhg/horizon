package com.example.hong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hong.entity.DayActive;
import com.example.hong.entity.VO.ActiveVO;
import com.example.hong.entity.VO.BehaviorVO;
import com.example.hong.mapper.ActiveMapper;
import com.example.hong.param.ActiveParam;
import com.example.hong.param.PageParam;
import com.example.hong.service.ActiveDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
}

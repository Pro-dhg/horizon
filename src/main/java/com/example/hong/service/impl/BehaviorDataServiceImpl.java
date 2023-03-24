package com.example.hong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hong.entity.Behavior;
import com.example.hong.entity.VO.BehaviorVO;
import com.example.hong.mapper.BehaviorMapper;
import com.example.hong.param.BehaviorQueryParam;
import com.example.hong.service.BehaviorDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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

    public BehaviorDataServiceImpl(BehaviorMapper behaviorMapper) {
        this.behaviorMapper = behaviorMapper;
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
}

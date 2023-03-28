package com.example.hong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hong.entity.VO.BehaviorVO;
import com.example.hong.param.BehaviorAddParam;
import com.example.hong.param.BehaviorQueryParam;

/**
 * @ClassName BehaviorDataService
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/16 14:55
 * @Description:
 */
public interface BehaviorDataService {
    Page<BehaviorVO> queryBehavior(BehaviorQueryParam param);

    void addBehavior(BehaviorAddParam behaviorAddParam) throws InterruptedException;
}

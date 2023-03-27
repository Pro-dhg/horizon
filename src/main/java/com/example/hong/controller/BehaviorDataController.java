package com.example.hong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hong.entity.VO.ActiveVO;
import com.example.hong.entity.VO.BehaviorVO;
import com.example.hong.param.ActiveParam;
import com.example.hong.param.BehaviorQueryParam;
import com.example.hong.param.PageParam;
import com.example.hong.service.ActiveDataService;
import com.example.hong.service.BehaviorDataService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName BehaviorDataController
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/16 17:34
 * @Description:
 */
@RestController
@RequestMapping("/hong")
public class BehaviorDataController {

    private final BehaviorDataService behaviorDataService;
    private final ActiveDataService activeDataService;
    public BehaviorDataController(BehaviorDataService behaviorDataService, ActiveDataService activeDataService){
        this.behaviorDataService = behaviorDataService;
        this.activeDataService = activeDataService;
    }

    @PostMapping("/behavior")
    public Page<BehaviorVO> queryBehavior(@RequestBody @Validated BehaviorQueryParam behaviorQueryParam) {
        return behaviorDataService.queryBehavior(behaviorQueryParam);
    }

    @GetMapping("/active")
    public Page<ActiveVO> queryActive(@Validated ActiveParam param){
        return activeDataService.queryActive(param);
    }

}

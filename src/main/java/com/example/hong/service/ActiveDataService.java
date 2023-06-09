package com.example.hong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hong.entity.VO.ActiveVO;
import com.example.hong.param.ActiveAddParam;
import com.example.hong.param.ActiveParam;

/**
 * @ClassName ActiveDataService
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/27 21:54
 * @Description:
 */
public interface ActiveDataService {
    Page<ActiveVO> queryActive(ActiveParam param);

    void multiThreadAddActive(ActiveAddParam activeParam);
}

package com.example.hong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hong.entity.DayActive;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName ActiveMapper
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/27 21:51
 * @Description:
 */
@Mapper
public interface ActiveMapper extends BaseMapper<DayActive> {
}

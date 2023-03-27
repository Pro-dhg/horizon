package com.example.hong.entity.VO;

import com.example.hong.entity.DayActive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

/**
 * @ClassName BehaviorVO
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/18 22:13
 * @Description:
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ActiveVO {
    private String id;

    public static ActiveVO valueOf(DayActive behavior){
        if (Objects.isNull(behavior)) return  null ;
        return ActiveVO.builder().id(behavior.getId()).build();
    }

}

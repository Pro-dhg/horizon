package com.example.hong.entity.VO;

import com.example.hong.entity.Behavior;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
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
public class BehaviorVO {
    private String id;

    private String name;

    private Date parseTimestamp;

    private String behavior;

    public static BehaviorVO valueOf(Behavior behavior){
        if (Objects.isNull(behavior)) return  null ;
        return BehaviorVO.builder().id(behavior.getId()).name(behavior.getName()).parseTimestamp(behavior.getParseTimestamp()).behavior(behavior.getBehavior()).build();
    }

}

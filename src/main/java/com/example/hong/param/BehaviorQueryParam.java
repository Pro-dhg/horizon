package com.example.hong.param;

import lombok.Data;

/**
 * @ClassName BehaviorQueryParam
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/18 22:52
 * @Description:
 */
@Data
public class BehaviorQueryParam extends PageParam {
    private String id;
    private String name;
}

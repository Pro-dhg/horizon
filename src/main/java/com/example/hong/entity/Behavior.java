package com.example.hong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Behavior
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/16 15:31
 * @Description:
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "ods.ods_behavior_data_local", autoResultMap = true)
public class Behavior implements Serializable {
    private static final long serialVersionUID = -3899032938951190148L;
    @TableId(value = "id")
    private String id;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 解析时间
     */
    @TableField(value = "parse_timestamp")
    private Date parseTimestamp;

    /**
     * 行为
     */
    @TableField(value = "behavior")
    private String behavior;
}

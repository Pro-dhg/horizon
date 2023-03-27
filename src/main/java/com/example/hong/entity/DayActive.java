package com.example.hong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @ClassName DayActive
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/27 21:41
 * @Description:
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "ods.ods_day_active_local", autoResultMap = true)
public class DayActive implements Serializable {
    @TableId(value = "id")
    private String id;

    /**
     * 时间
     */
    @TableField(value = "parse_timestamp")
    private String parseTimestamp;

}

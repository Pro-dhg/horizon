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
 * @ClassName CreateZoneInfo
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

    @TableField(value = "姓名")
    private String name;

    @TableField(value = "解析时间")
    private Date parseTimestamp;

    @TableField(value = "行为")
    private String behavior;
}

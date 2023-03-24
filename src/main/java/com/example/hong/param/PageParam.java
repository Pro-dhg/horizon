package com.example.hong.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @ClassName PageParam
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/24 17:35
 * @Description:
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {

    @Min(value = 1, message = "当前页码必须大于0")
    @NotNull(message = "请输入当前页码")
    private Integer pageNumber;

    @Min(value = 1, message = "分页最小为1")
    @Max(value = 10000, message = "分页最大为10000")
    @NotNull(message = "请输入当前分页大小")
    private Integer pageSize;
}

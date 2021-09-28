package com.last.edu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 22514
 */
@Data
@ApiModel(value = "异常类",description = "自定义异常处理")
@AllArgsConstructor
@NoArgsConstructor
public class LastException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "消息")
    private String msg;

}

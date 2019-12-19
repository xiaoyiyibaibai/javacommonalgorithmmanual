package com.algorithm.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author renhao
 * @Date 2019/12/19 13:43
 **/
@Data
@ApiModel("示例类")
public class Demo {
    @ApiModelProperty("唯一标识")
    private String id;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("密码")
    private String password;
}

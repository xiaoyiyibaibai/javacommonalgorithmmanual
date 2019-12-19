package com.algorithm.vo;

import lombok.Data;

/**
 * @ClassName ResponseModel
 * @Description TODO
 * @Author renhao
 * @Date 2019/12/19 13:09
 **/
@Data
public class ResponseModel<T> {
    private int status= 200;
    private T data;
    private String message;




}

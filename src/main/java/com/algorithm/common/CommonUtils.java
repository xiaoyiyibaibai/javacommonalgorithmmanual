package com.algorithm.common;

import java.util.UUID;

/**
 * @ClassName CommonUtils
 * @Description 通用工具类
 * @Author renhao
 * @Date 2020/1/3 9:03
 **/
public class CommonUtils {
    /**
     * @Author xiaodongohong
     * @Description 生成uuid的信息
     * @Date 9:04 2020/1/3
     * @Param []
     * @return java.lang.String
     **/
    public static String get32UUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return  uuid;
    }
}

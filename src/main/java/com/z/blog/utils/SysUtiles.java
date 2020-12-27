package com.z.blog.utils;

import lombok.extern.log4j.Log4j;

/**
 * 系统工具类
 * @author Mr zhang
 * @date 2020/12/18
 */
@Log4j
public class SysUtiles {


    public static Boolean isImage(String name) {
        final String f1 = "jpg";
        final String f2 = "png";

        String fix = null;
        try {
            fix = name.split("\\.")[name.split("\\.").length - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            log.info("图片格式错误");
            return false;
        }
        boolean judge = false;
        if (f1.equals(fix)) {
            judge = true;
        } else if (f2.equals(fix)) {
            judge = true;
        }

        return judge;
    }

}

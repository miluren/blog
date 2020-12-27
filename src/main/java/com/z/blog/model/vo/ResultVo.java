package com.z.blog.model.vo;


import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果集
 * @author Mr zhang
 * @date 2020/12/17
 */
public class ResultVo extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public ResultVo() {
        put("code", 0);
        put("msg", "success");
        put("data", null);
    }

    public static ResultVo error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static ResultVo error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static ResultVo error(int code, String msg) {
        ResultVo ResultVo = new ResultVo();
        ResultVo.put("code", code);
        ResultVo.put("msg", msg);
        return ResultVo;
    }

    public static ResultVo ok(Object data) {
        ResultVo ResultVo = new ResultVo();
        ResultVo.put("code", HttpStatus.SC_OK);
        ResultVo.put("msg", "success");
        ResultVo.put("data", data);
        return ResultVo;
    }

    public static ResultVo ok(String msg, Object data) {
        ResultVo ResultVo = new ResultVo();
        ResultVo.put("code", HttpStatus.SC_OK);
        ResultVo.put("msg", msg);
        ResultVo.put("data", data);
        return ResultVo;
    }

    public static ResultVo ok(Map<String, Object> data) {
        ResultVo ResultVo = new ResultVo();
        ResultVo.put("code", HttpStatus.SC_OK);
        ResultVo.put("data", data);
        return ResultVo;
    }

    public static ResultVo ok() {
        return new ResultVo().put("code", HttpStatus.SC_OK);
    }

    @Override
    public ResultVo put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

package com.z.blog.handler;

import com.z.blog.model.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理
 * @author Mr zhang
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVo handler(MethodArgumentNotValidException e){
        log.error("实体校验异常");
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return ResultVo.error(HttpStatus.BAD_REQUEST.value(), objectError.getDefaultMessage());
    }

//    @ExceptionHandler(value = RuntimeException.class)
//    public ResultVo tokenErr(MethodArgumentNotValidException e){
//        return ResultVo.error(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
//    }


}

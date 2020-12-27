package com.z.blog.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * 日志切面
 * @author Mr zhang
 * @date 2020/12/15
 */

@Component
@Aspect
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Pointcut是植入Advice的触发条件
     */
    @Pointcut("execution(* com.z.blog.controller.*.*(..))")
    public void log() {
    }


    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求接口的url
        String url = request.getRequestURL().toString();
        // 获取请求的ip
        String ip = request.getRemoteAddr();
        // 获取请求的方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName();
        // 获取入参
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("----方法前----");
        logger.info("Request: " + requestLog);
    }

    @After("log()")
    public void doAfter() {
        logger.info("----方法后-----");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("----返回时-----");
        // 展示返回的参数
        logger.info("Result : {"+ result+ "}");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }

}

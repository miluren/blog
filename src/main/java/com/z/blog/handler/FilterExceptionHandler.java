package com.z.blog.handler;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.z.blog.model.vo.ResultVo;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 对controller错误进行处理
 * @author Mr zhang
 * @date 2020/12/15
 */

@RestController
public class FilterExceptionHandler extends BasicErrorController {

    private static final String BAD_REQUEST = "BAD_REQUEST";

    private static final String tErr = "token异常";

    public FilterExceptionHandler() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }


    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        final String e1 = "exception";
        final String e2 = "error";

        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        // 处理token异常
        if (!StringUtils.isBlank((String) body.get(e1)) && body.get(e1).equals(AuthenticationException.class.getName())) {
            body.put("status", HttpStatus.FORBIDDEN.value());
            status = HttpStatus.FORBIDDEN;
            ResultVo resultVo = ResultVo.error(HttpStatus.UNAUTHORIZED.value(), "token异常");
            return new ResponseEntity<>(resultVo, status);
        }

        return super.error(request);
    }

    @Override
    public String getErrorPath() {
        return "error/error";
    }

}

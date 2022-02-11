package com.last.edu.config;

import com.last.edu.entity.LastException;
import com.last.utils.ExceptionUtil;
import com.last.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 22514
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * this is test！conflict
     */

    @ExceptionHandler(LastException.class)
    @ResponseBody
    public Response error(LastException e){
        log.error(ExceptionUtil.getMessage(e));
        return Response.error().message(e.getMsg()).code(e.getCode());
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Response error(ArithmeticException e){
        e.printStackTrace();
        return Response.error().message("执行了自定义异常").code(20001);
    }
}

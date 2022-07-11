package com.pccg.pccguser.infrastructure.common;

import com.pccg.pccguser.infrastructure.common.exception.PcgException;
import com.pccg.pccguser.interfaces.api.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        StringBuilder msg = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            msg.append(error.getDefaultMessage()).append("</br>");
        });
        return Response.buildFailure(ErrorCode.IllegalArgument.getCode(), msg.toString());
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(PcgException.class)
    public Response handleRRException(PcgException e){
        log.error(e.getMessage(), e);
        return Response.buildFailure(e.getCode(), e.getMsg());
    }

    //其他异常返回给前端处理
    @ExceptionHandler(Exception.class)
    public Response otherException(Exception e){
        log.error("system unknown error", e);
        return Response.buildFailure(ErrorCode.SysError.getCode(), "");
    }
}

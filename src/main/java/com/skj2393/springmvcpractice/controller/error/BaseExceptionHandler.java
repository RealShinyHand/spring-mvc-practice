package com.skj2393.springmvcpractice.controller.error;

import com.skj2393.springmvcpractice.controller.constant.ErrorCode;
import com.skj2393.springmvcpractice.dto.APIErrorResponse;
import com.skj2393.springmvcpractice.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler
    public ModelAndView errorHtml(GeneralException e,HttpServletResponse res){
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError()? HttpStatus.BAD_REQUEST:HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String,String > map = new HashMap<>() ;
        map.put("statusCode",String.valueOf(status.value()));
        map.put("errorCode",errorCode.toString());
        map.put("message",errorCode.getMessage(status.getReasonPhrase()));

        return new ModelAndView("/error", map);

    }

    @ExceptionHandler
    public ModelAndView exception (Exception e){
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status =HttpStatus.INTERNAL_SERVER_ERROR;
        Map<String,String > map = new HashMap<>() ;
        map.put("statusCode",String.valueOf(status.value()));
        map.put("errorCode",errorCode.toString());
        map.put("message",errorCode.getMessage(status.getReasonPhrase()));

        return new ModelAndView("/error", map);
    }

}

package com.skj2393.springmvcpractice.controller.error;

import com.skj2393.springmvcpractice.controller.constant.ErrorCode;
import com.skj2393.springmvcpractice.dto.APIErrorResponse;
import com.skj2393.springmvcpractice.exception.GeneralException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
//extends ResponseEntityExceptionHandler 스프링 내부 익셉션 핸들링하는 기능들을 만들어 놓음
    @ExceptionHandler
    public ResponseEntity<APIErrorResponse> errorHtml(GeneralException e){
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError()? HttpStatus.BAD_REQUEST:HttpStatus.INTERNAL_SERVER_ERROR;

       return ResponseEntity.status(status).body(APIErrorResponse.of(false,errorCode,errorCode.getMessage()));

    }

    @ExceptionHandler
    public ResponseEntity<APIErrorResponse>  exception (Exception e){
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status =HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status).body(APIErrorResponse.of(false,errorCode,errorCode.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request){
        ErrorCode ec = status.is4xxClientError() ?
                ErrorCode.SPRING_BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

        return super.handleExceptionInternal(ex,

                APIErrorResponse.of(false,ec.getCode(),ec.getMessage(ex)),

                headers,status,request);
    }

}

package com.skj2393.springmvcpractice.controller.error;

import com.skj2393.springmvcpractice.controller.constant.ErrorCode;
import com.skj2393.springmvcpractice.dto.APIErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseErrorController implements ErrorController {
    //ErrorController 인터페이스 구현해줘야 error 페이지가 오류없이 작동 ,
    //엡 프로퍼티 파일에서도 설정 추가해야함 .
    @RequestMapping(value = "/error",produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletResponse res){
        HttpStatus status = HttpStatus.valueOf(res.getStatus());
        ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

        Map<String,String >map = new HashMap<>() ;
        map.put("statusCode",String.valueOf(status.value()));
        map.put("errorCode",errorCode.toString());
        map.put("message",errorCode.getMessage(status.getReasonPhrase()));

    return new ModelAndView("/error", map);

    }

    @RequestMapping("/error")
    public ResponseEntity<APIErrorResponse> error(HttpServletResponse res){
        HttpStatus status = HttpStatus.valueOf(res.getStatus());
        ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

        return ResponseEntity.status(status).body(APIErrorResponse.of(
                false,
                errorCode
        ));

    }
}

package com.example.airbnbpractice.common;

import com.example.airbnbpractice.common.dto.ErrorMessage;
import com.example.airbnbpractice.common.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDto exception(Exception e, HttpServletRequest request) {
        log.warn("Exception 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        return ErrorResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto runtimeException(RuntimeException e, HttpServletRequest request) {
        log.warn("runtimeException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        return ErrorResponseDto.of(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto illegalStateException(IllegalArgumentException e, HttpServletRequest request) {
        log.warn("illegalStateException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        return ErrorResponseDto.of(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = CustomClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto customException(CustomClientException e, HttpServletRequest request){
        log.warn("CustomClientException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        return ErrorResponseDto.of(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto aaaException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.warn("MethodArgumentNotValidException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        return ErrorResponseDto.of(HttpStatus.BAD_REQUEST.value(), makeErrorMessage(e.getBindingResult()));
    }

    private String makeErrorMessage(BindingResult bindingResult){

        String message = "";

        //에러가 있다면
        if(bindingResult.hasErrors()){
            String bindResultCode = bindingResult.getFieldError().getCode();

            switch (bindResultCode){
                case "NotNull":
                    message = ErrorMessage.NOT_NULL.getDescription();
                    break;
                case "Pattern":
                    message = ErrorMessage.PATTERN.getDescription();
                    break;
            }
        }

        return message;
    }
}
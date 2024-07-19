package com.mijiu.exception.handle;


import com.mijiu.commom.exception.*;
import com.mijiu.commom.result.Result;
import com.mijiu.commom.enumerate.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * @author mijiupro
 */
@RestControllerAdvice(basePackages = "com.mijiu.controller")
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    // 验证码错误异常
    @ExceptionHandler(CaptchaErrorException.class)
    public Result<String> handleVerifyCodeErrorException(CaptchaErrorException ex) {
        return Result.error(ex.getResultEnum());
    }

    // 账号不存在异常
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleAccountNotFoundException(AccountNotFoundException ex) {
        return Result.error(ex.getResultEnum());
    }

    // 密码错误异常
    @ExceptionHandler(PasswordErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handlePasswordErrorException(PasswordErrorException ex) {
        return Result.error(ex.getResultEnum());
    }

    // 账号封禁异常
    @ExceptionHandler(AccountForbiddenException.class)
    public Result<String> handleAccountForbiddenException(AccountForbiddenException ex) {
        return Result.error(ex.getResultEnum());
    }

    // 登录状态过期异常
    @ExceptionHandler(TokenOverdueException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<?> handleTokenOverdueException(TokenOverdueException ex) {
        return Result.error(ex.getResultEnum());
    }


    // 参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleValidationExceptions(Exception ex) {
        log.error(ex.getMessage());
        // 从异常中获取字段错误信息
        FieldError fieldError = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldError();
        if (fieldError != null) {
            // 获取错误提示信息
            String errorMessage = fieldError.getDefaultMessage();
            log.error(errorMessage);
            return Result.error(errorMessage);
        } else {
            // 如果没有字段错误，返回默认错误信息
            log.error(ex.getMessage());
            return Result.error("请求参数验证失败");
        }
    }
    // 限流异常处理
    @ExceptionHandler(RateLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleRateLimitException(RateLimitException ex) {
        return Result.error(ex.getResultEnum());
    }


    //通用业务异常处理
    @ExceptionHandler(GeneralBusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> generalBusinessExceptionHandler(GeneralBusinessException ex) {
        return Result.error(ex.getMessage());
    }
}
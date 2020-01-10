package com.yvant.advice;

import com.yvant.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author yunfeng
 * @since 2019-07-05
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    /**
     * 自定义异常处理
     *
     * @param ex
     * @return
     */
    //@ExceptionHandler(value = AdmissionException.class)
    //public JsonResponse handlerApplyException(AdmissionException ex) {
    //    return JsonResponse.fail(ex.getMessage());
    //}

    /**
     * 请求参数校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public CommonResult handlerMethodArgumentNotValidException(BindException ex) {
        ObjectError error = ex.getBindingResult().getAllErrors().get(0);
        log.error(error.getDefaultMessage());
        return CommonResult.failed(error.getDefaultMessage());
    }


    /**
     * 通用异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult handlerException(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return CommonResult.failed();
    }

}

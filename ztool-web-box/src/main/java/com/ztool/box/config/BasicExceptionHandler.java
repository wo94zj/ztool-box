package com.ztool.box.config;

import com.ztool.box.exception.RootException;
import com.ztool.box.resp.BaseUnifyCode;
import com.ztool.box.resp.UnifyResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@ControllerAdvice
public class BasicExceptionHandler {

    /**
     * 参数验证失败
     */
    @ExceptionHandler(value = ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UnifyResp<Serializable> validationException(ValidationException ex) {
        log.error("validationException :", ex);
        return UnifyResp.newBuilder().withUnifyCode(BaseUnifyCode.ERROR_400).withMsg(ex.getMessage()).build();
    }

    /**
     * 参数绑定失败
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UnifyResp<Serializable> bindException(BindException ex) {
        log.error("bindException :", ex);
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        String msg = CollectionUtils.isEmpty(allErrors) ? BaseUnifyCode.ERROR_400.msg() : allErrors.get(0).getDefaultMessage();
        return UnifyResp.newBuilder().withUnifyCode(BaseUnifyCode.ERROR_400).withMsg(msg).build();
    }

    /**
     * 参数解析异常
     */
    @ExceptionHandler(value = HttpMessageConversionException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UnifyResp<Serializable> httpMessageConversionException(HttpMessageConversionException ex) {
        log.error("httpMessageConversionException :", ex);
        return UnifyResp.newBuilder().withUnifyCode(BaseUnifyCode.ERROR_400).withMsg("参数解析异常").build();
    }

    /**
     * 参数异常
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UnifyResp<Serializable> illegalArgumentException(IllegalArgumentException ex) {
        log.error("illegalArgumentException :", ex);
        return UnifyResp.newBuilder().withUnifyCode(BaseUnifyCode.ERROR_400).withMsg("参数异常").build();
    }

    /**
     * 404
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UnifyResp<Serializable> noHandlerFoundException(NoHandlerFoundException ex) {
        log.error("noHandlerFoundException :", ex);
        return UnifyResp.newBuilder().withUnifyCode(BaseUnifyCode.ERROR_404).withMsg(ex.getMessage()).build();
    }

    /**
     * 自定义通用异常处理
     */
    @ExceptionHandler(value = RootException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UnifyResp<Serializable> rootException(RootException ex) {
        log.error("rootException :", ex);
        return UnifyResp.newBuilder().withCode(ex.getCode()).withMsg(ex.getMessage()).build();
    }

    /**
     * SQL异常处理
     */
    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UnifyResp<Serializable> sqlException(SQLException ex) {
        log.error("sqlException :", ex);
        return UnifyResp.newBuilder().withUnifyCode(BaseUnifyCode.ERROR_500).withMsg("数据执行异常").build();
    }

    /**
     * 暂时未处理的异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<UnifyResp<Serializable>> exception(Exception ex, HttpServletResponse response) {
        log.error("Response :{}; Exception :{}", response.getStatus(), ex);

        ResponseEntity<UnifyResp<Serializable>> resp = null;
        if (ex instanceof ServletRequestBindingException) {// MissingRequestHeaderException
            // 参数映射失败
            resp = new ResponseEntity<UnifyResp<Serializable>>(UnifyResp.newBuilder().withUnifyCode(BaseUnifyCode.ERROR_400).withMsg(ex.getMessage()).build(), HttpStatus.OK);
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            // 请求方式不支持
            resp = new ResponseEntity<UnifyResp<Serializable>>(UnifyResp.newBuilder().withUnifyCode(BaseUnifyCode.ERROR_405).withMsg(ex.getMessage()).build(), HttpStatus.OK);
        } else if (ex instanceof NoHandlerFoundException) {
            // 没找到处理方法
            resp = new ResponseEntity<UnifyResp<Serializable>>(UnifyResp.newBuilder().withUnifyCode(BaseUnifyCode.ERROR_404).withMsg(ex.getMessage()).build(), HttpStatus.OK);
        } else if (ex instanceof HttpMediaTypeException) {
            // 参数提交格式不正确
            resp = new ResponseEntity<UnifyResp<Serializable>>(UnifyResp.newBuilder().withUnifyCode(BaseUnifyCode.ERROR_415).withMsg(ex.getMessage()).build(), HttpStatus.OK);
        } else {
            resp = new ResponseEntity<UnifyResp<Serializable>>(UnifyResp.newBuilder().withUnifyCode(BaseUnifyCode.ERROR_500).withMsg(ex.getMessage()).build(), HttpStatus.OK);
        }

        return resp;
    }
}

package com.ruoyi.project.business.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author ZhangKeChen  🤙🤙🏼🤙🏽
 * @Date 2022/11/28
 * @Description 打印请求和响应信息
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    @Pointcut("execution(public * com.ruoyi.project.business.controller.*.*(..))")
    public void controllerWebLog() {

    }


    @Before("controllerWebLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info("");
        log.info("");
        log.info("《================================================================请求开始================================================================》");
        log.info("URL :             " + request.getRequestURL().toString());
        log.info("HTTP_METHOD :     " + request.getMethod());
        log.info("IP :              " + request.getRemoteAddr());
        log.info("CLASS_METHOD :    " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        log.info("REQUEST :         " + Arrays.toString(joinPoint.getArgs()));
        log.info("REQUEST HEADERS: TOKEN:  " + "token:[{}]", request.getHeader("token"));
        log.info("REQUEST HEADERS: USERID: " + "userId:[{}]", request.getHeader("userId"));
    }

    @AfterReturning(returning = "res", pointcut = "controllerWebLog()")
    public void doAfterReturning(Object res) throws JsonProcessingException {
        log.info("RESPONSE :        " + new ObjectMapper().writeValueAsString(res));
        log.info("《================================================================请求结束================================================================》");

    }
}

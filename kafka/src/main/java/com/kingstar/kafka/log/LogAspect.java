package com.kingstar.kafka.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * log日志收集
 * @author zyl520
 */
@Aspect
@Component
@Slf4j
public class LogAspect {


    @Pointcut("execution(public * com.kingstar.kafka.controller.LogController.query())")
    public void log() {

    }

    @Before("log()")
    public void doBefore() {
        log.info("前置通知");
    }

    @Around(value = "log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("请求地址URL为: {} ,请求方法HTTP_METHOD为: {}, 请求IP地址为: {} ,请求body为:{}", request.getRequestURL().toString(), request.getMethod(), request.getLocalAddr(), proceedingJoinPoint.getArgs());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            log.info("请求name:{},value:{}", name, request.getParameter(name));
        }
        Object obj = proceedingJoinPoint.proceed();
        log.info("响应结果 : {} ", obj);
        long endTime = System.currentTimeMillis();
        long differentTime = endTime - startTime;
        log.info("请求地址URL为: {} ,操作耗时: {}ms,请求方法HTTP_METHOD为: {}, 请求IP地址为: {} ,请求body为:{}", request.getRequestURL().toString(),differentTime, request.getMethod(), request.getLocalAddr(), proceedingJoinPoint.getArgs());
        return obj;
    }

}

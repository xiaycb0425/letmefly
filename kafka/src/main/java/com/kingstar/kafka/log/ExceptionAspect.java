package com.kingstar.kafka.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xiayc
 * @date 2020/8/27 13:56
 */
@Aspect
@Slf4j
@Component
public class ExceptionAspect {

    @Pointcut
    public void exception() {

    }

    @AfterThrowing(throwing = "myException", pointcut = "execution(* com.kingstar.kafka.controller.*.*(..))")
    public void doAfterThrowing(Throwable myException) {
        log.info("捕获到异常{}", myException.getMessage());
    }
}

package com.kingstar.kafka.spring.annotation;

import java.lang.annotation.*;

/**
 * @author xiayc
 * @date 2020/9/8 9:57
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
public @interface Mapper {
}

package com.kingstar.kafka.spring.configuration;

import com.kingstar.kafka.spring.MapperImportBeanDefinitionRegistar;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xiayc
 * @date 2020/9/8 9:44
 */
@Configuration
@ComponentScan("com.kingstar.kafka.mapper")
@Import(MapperImportBeanDefinitionRegistar.class)
public class MapperConfiguration {

}
package com.kingstar.kafka.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Map;

/**
 * @author xiayc
 * @date 2020/9/8 9:43
 */
public class HelloImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * @return void
     * @Author haien
     * @Description AnnotationMetadata:当前类的注解信息；
     * BeanDefinitionRegistry：注册类，其registerBeanDefinition()可以注册bean
     * @Date 2019/6/12
     * @Param [importingClassMetadata, registry]
     **/
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {

        //扫描注解
        Map<String, Object> annotationAttributes = importingClassMetadata
                .getAnnotationAttributes(ComponentScan.class.getName());
        String[] basePackages = (String[]) annotationAttributes.get("basePackages");

        //扫描类
        ClassPathBeanDefinitionScanner scanner =
                new ClassPathBeanDefinitionScanner(registry, false);
        TypeFilter helloServiceFilter = new AssignableTypeFilter(HelloService.class);

        scanner.addIncludeFilter(helloServiceFilter);
        scanner.scan(basePackages);
    }
}